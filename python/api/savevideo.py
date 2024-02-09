import cv2
import mediapipe as mp
import json
import os
import config

def process_video_and_save_keypoints(video_path, target_video, key_path):

    x0, y0, x1, y1 = 1e+9, 1e+9, -1, -1

    # video 정보 저장 파일 생성
    os.makedirs(os.path.join(key_path, target_video), exist_ok=True)

    # video 불러오기 및 video 설정 저장
    cap = cv2.VideoCapture(os.path.join(video_path, target_video))
    target_fps = 10  # 초당 30프레임을 유지하도록 설정
    cap.set(cv2.CAP_PROP_FPS, target_fps)  # 초당 프레임 설정
    video_inform = {
        'frame_width': int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
        'frame_height': int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
        'video_fps': cap.get(cv2.CAP_PROP_FPS),
        'total_frame': int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    }
    with open(os.path.join(key_path, target_video, f'_info.json'), "w") as f:
        json.dump(video_inform, f, indent='\t')

    # Setup mediapipe instance
    with mp.solutions.pose.Pose(min_detection_confidence=0.5, min_tracking_confidence=0.5) as pose:
        i = 0
        while cap.isOpened():
            ret, frame = cap.read()
            if ret is False:
                break

            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            results = pose.process(frame)

            try:
                landmarks = results.pose_landmarks.landmark
            except:
                continue

            # Get coordinate
            # save keypoints
            keypoints = config.make_keypoints(landmarks, mp.solutions.pose, video_inform)

            with open(os.path.join(key_path, target_video, f'{i:0>4}.json'), "w") as f:
                json.dump(keypoints, f, indent='\t')

            for x, y, z, k in list(keypoints.values())[:-1]:
                if x0 > x: x0 = x
                if y0 > y: y0 = y
                if x1 < x: x1 = x
                if y1 < y: y1 = y
            if x0 < 0: x0 = 0
            if y0 < 0: y0 = 0
            if x1 > 1: x1 = 1
            if y1 > 1: y1 = 1

            video_inform['gt_bbox'] = [x0, y0, x1, y1]
            with open(os.path.join(key_path, target_video, f'_info.json'), "w") as f:
                json.dump(video_inform, f, indent='\t')

            i = i + 1

            if i % 1000 == 0:
                print(i)
            # 'q'누르면 캠 꺼짐
            if cv2.waitKey(10) & 0xFF == ord("q"):
                break
        print("gt 저장 최종 프레임수", i);
    cap.release()
    cv2.destroyAllWindows()

# 사용 예시

