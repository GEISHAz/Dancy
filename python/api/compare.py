import time
import cv2
import mediapipe as mp
import numpy as np
import json
import os
import metric
import config
import moviepy.editor as mpa
from flask import jsonify

small_parts = {
    "left thigh": [1, 2],
    "left calf": [2, 3],
    "right thigh": [6, 7],
    "right calf": [7, 8],
    "left arm": [14, 15],
    "left forearm": [15, 16],
    "right arm": [19, 20],
    "right forearm": [20, 21],
    "body1": [6, 14],
    "body2": [1, 19],
    "total": [1, 1]}

small_name = list(small_parts.keys())


def compare_video(gt_url, prac_url, sync_frame):
    # compare_video 동작 체크
    print("compare_video 진입 ===")

    gt_url_arr = gt_url.split('/')
    prac_url_arr = prac_url.split('/')
    gt_name = gt_url_arr[len(gt_url_arr) - 1]
    prac_name = prac_url_arr[len(prac_url_arr) - 1]
    gt_name_arr = gt_name.split('_')        # len = 2 or 3
    prac_name_arr = prac_name.split('_')    # len = 4

    average_accuracy = 0
    pTime = 0

    mp_pose = mp.solutions.pose

    # 경로 설정
    key_path = "./dataset/json/"
    key_path_image = "./dataset/image"
    gt_path = f"{gt_name}"
    target_video = f"{prac_name}"

    # video 정보 저장 파일 생성
    os.makedirs(os.path.join(key_path, target_video), exist_ok=True)

    os.makedirs(os.path.join("./dataset/audio/", gt_name), exist_ok=True)
    video_clip = mpa.VideoFileClip(f'./dataset/{gt_url}')
    audio_clip = video_clip.audio
    audio_clip.write_audiofile(f'./dataset/audio/{gt_name}/{gt_name}', codec='mp3')


    # cap = cv2.VideoCapture(os.path.join(video_path, target_video))
    cap = cv2.VideoCapture(f"./dataset/{prac_url}")  # 비디오 한장 캡쳐
    cap_gt = cv2.VideoCapture(f"./dataset/{gt_url}")
    audio = cv2.VideoCapture(f"./dataset/audio/{gt_name}.mp3")


    fourcc = cv2.VideoWriter_fourcc(*'mp4v')  # 비디오 형식 정하기
    fps = 30
    cap_gt.set(cv2.CAP_PROP_FPS, fps)
    cap.set(cv2.CAP_PROP_FPS, fps)


    # 비디오 정보
    video_inform = {
        'frame_width': int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
        'frame_height': int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)),
        'video_fps': fps,
        'total_frame': int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    }
    # prac 영상의 info.json 을 쓰는 코드
    with open(os.path.join(key_path, target_video, f'_info.json'), "w") as f:
        json.dump(video_inform, f, indent='\t')

    # gt information 가져오기
    with open(os.path.join(key_path, gt_path, f'_info.json')) as json_file:
        gt_inform = json.load(json_file)  # frame_width, frame_height, video_fps, total_frame, #gt_bbox

    prac_resize = (int(video_inform['frame_width'] * gt_inform['frame_height'] / video_inform['frame_height']),
                   gt_inform['frame_height'])
    gt_resize = (int(gt_inform['frame_width'] * gt_inform['frame_height'] / gt_inform['frame_height']),
                   gt_inform['frame_height'])

    gt_video = metric.VideoMetric(gt_inform['frame_width'], gt_inform['frame_height'])
    prac_video = metric.VideoMetric(prac_resize[0], prac_resize[1])

    # ==========================================분석
    # gt와 비교할 Frame 수 선정
    compare_frame = 10  # (중요) 정확도 계산하는 프레임 단위
    before_frame = 5
    match_frame = gt_inform['video_fps'] / video_inform['video_fps']  # 비디오 두 프레임이 다를 경우에 Sync를 맞춰줌
    threshold = 0.2
    threshold_cs = 0.8  # 변위 vector Cosine Similarity 평가 기준
    accept_frame = 5  # OK 로 평가하는 Frame 수
    sync_frame = sync_frame  # Sync를 위한 frame
    prac_temp = []
    eval_metric = ["normal"] * 10  # 시작 후 compare_frame+before_frame 동안 평가 진행 X
    eval_graph_y = [[] for _ in range(11)]
    eval_graph_x = []

    #아직 소리 없음
    out = cv2.VideoWriter(f"dataset/result/{gt_name_arr[0]}_analyzed_{prac_name_arr[2]}_{prac_name_arr[3]}", fourcc, 30,
                          (gt_resize[0]+prac_resize[0],max(gt_resize[1],prac_resize[1])))


    frame_count = 0
    total_accuracy = 0
    FRAME = 30

    #정확도 배열
    accuracy_frame_list = []
    accuracy_second_list = []

    with mp_pose.Pose(min_detection_confidence=0.5, min_tracking_confidence=0.5) as pose:  # 미디어파이프 신뢰도 설정
        i = 0
        # 비디오 저장할 곳, 형식, 프레임,크기 설정
        print("정확도 계산 시작")
        while cap.isOpened():
            ret_gt, frame_gt = cap_gt.read()  # GT 비디오에서 1프레임 읽기
            ret, frame = cap.read()



            if not ret or not ret_gt: break
            if i >= gt_inform['total_frame'] - 1: break

            # get frame time and FPS
            # frame_time = cap.get(cv2.CAP_PROP_POS_MSEC)
            resize_frame = cv2.resize(frame, dsize=prac_resize, fx=1, fy=1, interpolation=cv2.INTER_LINEAR)
            gt_frame_resized = cv2.resize(frame_gt, dsize=gt_resize, fx=1, fy=1, interpolation=cv2.INTER_LINEAR)

            # Make detection
            resize_frame = cv2.cvtColor(resize_frame, cv2.COLOR_BGR2RGB)
            gt_frame_resized = cv2.cvtColor(gt_frame_resized, cv2.COLOR_BGR2RGB)
            results = pose.process(resize_frame)

            # Recolor back to BGR
            resize_frame.flags.writeable = True

            # Extract landmarks
            try:
                landmarks = results.pose_landmarks.landmark
            except:
                i = i + 1
                continue
            # Get coordinate

            # save keypoints
            keypoints = config.make_keypoints(landmarks, mp_pose, video_inform)
            if len(prac_temp) > before_frame:
                prac_temp = prac_temp[1:]
            prac_temp.append(keypoints)

            with open(os.path.join(key_path, gt_path,f'{max(int(i * match_frame + sync_frame), 0):0>4}.json')) as json_file:
                gt_json = json.load(json_file)

            if i % (compare_frame) == 0:
                s_p = max(int(i * match_frame + sync_frame) - compare_frame, before_frame)  # start point
                e_p = min(int(i * match_frame + sync_frame) + compare_frame,
                          gt_inform['total_frame'] - 1)  # end point

                # body part별로(왼다리, 오른다리, 왼팔, 오른팔, 몸통) normalize된 값 vector 추출
                prac = prac_video.extract_vec_norm_by_small_part(keypoints)
                prac_displace_prac = prac_video.extract_vec_norm_by_small_part_diff(prac_temp[0], keypoints)

                total_eval = [[] for _ in range(len(prac) + 1)]
                total_eval_diff = [[] for _ in range(len(prac))]

                for j in range(s_p, e_p, 1):
                    try:
                        with open(os.path.join(key_path, gt_path, f'{j:0>4}.json')) as json_file:
                            gt_temp = json.load(json_file)
                        with open(os.path.join(key_path, gt_path, f'{j - before_frame:0>4}.json')) as json_file:
                            displace_gt_temp = json.load(json_file)
                    except FileNotFoundError:
                        print(f"Warning: JSON file not found for frame {j}. Skipping...")
                        continue
                    except Exception as e:
                        print(f"Error loading JSON file for frame {j}: {e}")
                        break

                    gt = gt_video.extract_vec_norm_by_small_part(gt_temp)
                    gt_displace_prac = prac_video.extract_vec_norm_by_small_part_diff(displace_gt_temp, gt_temp)

                    s = 0
                    for part in range(len(prac)):
                        eval = metric.coco_oks(gt[part], prac[part], part) * (
                                metric.cosine_similar(gt[part], prac[part]) / 2 + 0.5)
                        total_eval[part].append(eval)
                        total_eval_diff[part].append((gt_displace_prac[part], prac_displace_prac[
                            part]))  # metric.cosine_similar(gt_displace_prac[part], prac_displace_prac[part])/2+1)
                        s += eval
                    total_eval[-1].append(s / len(prac))  # 평균 계산!

                eval_graph_y[-1].append(total_eval[-1][np.argmax(total_eval[-1])])  # 평균 계산
                eval_graph_x.append(cap.get(cv2.CAP_PROP_POS_MSEC) / 1000)
                eval_metric = []
                for part in range(len(prac)):
                    eval_graph_y[part].append(total_eval[part][np.argmax(total_eval[part])])
                    best_point = np.argmax(total_eval[part])
                    worst_point = np.argmin(total_eval[part])
                    if total_eval[part][best_point] < threshold:  # threshold
                        eval_metric.append("NG")
                    else:
                        if np.linalg.norm(total_eval_diff[part][best_point][0]) < threshold or np.linalg.norm(
                                total_eval_diff[part][best_point][1]) < threshold:
                            if best_point - compare_frame - accept_frame > 0:
                                eval_metric.append(best_point)  # .append("fast")
                            elif best_point - compare_frame + accept_frame < 0:
                                eval_metric.append(best_point)  # .append("slow")
                            else:
                                eval_metric.append(best_point)  # .append("good")
                        else:
                            if metric.cosine_similar(total_eval_diff[part][best_point][0],
                                                     total_eval_diff[part][best_point][1]) > 0:
                                if best_point - compare_frame - accept_frame > 0:
                                    eval_metric.append(best_point)  # .append("fast")
                                elif best_point - compare_frame + accept_frame < 0:
                                    eval_metric.append(best_point)  # .append("slow")
                                else:
                                    eval_metric.append(best_point)  # .append("good")
                            else:
                                eval_metric.append("NG")

            prac_image = prac_video.visual_back_color(frame, keypoints, eval_metric)
            gt_image = gt_video.visual_back_color(frame_gt, gt_json, eval_metric)#

            # 두개의 이미지 하나는 스켈레톤, 하나는 연습영상에 스켈레톤 씌워진것을 가로로 병합하는 코드
            preimage = cv2.hconcat([gt_image, prac_image])
            image = cv2.cvtColor(preimage, cv2.IMREAD_COLOR)

            if i == 60:
                key_path_image = "./dataset/image"
                os.makedirs(os.path.join(key_path_image), exist_ok=True)
                cv2.imwrite(f"{key_path_image}/{target_video}.jpg",image)


            cTime = time.time()
            fps = 1 / (cTime - pTime)
            pTime = cTime


            frames_to_average = 10  # 평균을 내기 위한 프레임 수
            last_frames_accuracy = eval_graph_y[-1][-frames_to_average:]  # 마지막 30프레임 동안의 정확도 값들
            average_accuracy = np.mean(last_frames_accuracy)  # 마지막 30프레임 동안의 평균 정확도 계산

            # 정확도 텍스트 추가 (FPS 텍스트 위치에)
            cv2.putText(image, f'Accuracy: {average_accuracy:.4f}', (70, 50), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 0), 2)

            total_accuracy += average_accuracy

            # print("프레임 : ", frame_count, "정확도 : ", average_accuracy)
            if(frame_count%compare_frame == 0) :
                accuracy_frame_list.append([frame_count, round(average_accuracy, 4)])
            frame_count = frame_count+1

            # 시간이지남에 따라 이미지 frame+1 하는 코드
            i = i + 1


            # FRAME_WINDOW.image(image) #streamlit에서 보여주는 코드
            out.write(image)  # out을 위에 지정한 위치에 저장하는 코드

            # 저장 디버그 체크

        # 혹시 마지막 프레임이 10의 배수가 아니라 accuracy_frame_list에 저장이 안 되었을 경우 대비
        if((frame_count-1)%compare_frame != 0):
            accuracy_frame_list.append([frame_count, round(average_accuracy, 4)])

        # 정확도 계산
        sec = 0
        first_acc = 0
        second_acc = 0
        third_acc = 0
        idx = 0
        for frame_accuracy in accuracy_frame_list:
            if(idx%3==0) :
                first_acc = frame_accuracy[1]
            elif(idx%3==1) :
                second_acc = frame_accuracy[1]
            elif(idx%3==2) :
                third_acc = frame_accuracy[1]
                #일정 정확도 미만인 경우, 틀린 구간에 추가
                standard = 0.93
                if(first_acc<standard or second_acc<standard or third_acc<standard) :
                    accuracy_second_list.append([sec, min(first_acc, second_acc, third_acc)])
                sec = sec+1
            idx = idx+1
        cap.release()
    print("저장완료 되었습니다.")
    print("프레임 리스트 : ",accuracy_frame_list)
    print("초 리스트 : ",accuracy_second_list)
    print("======================================")

    accuracy_interval_list = calculate_accuracy(accuracy_second_list, sec)
    out.release()

    # 오디오 쓰기 및 저장
    analyzed_video_clip = mpa.VideoFileClip(f"dataset/result/{gt_name_arr[0]}_analyzed_{prac_name_arr[2]}_{prac_name_arr[3]}")
    analyzed_video_clip = analyzed_video_clip.set_audio(audio_clip)
    analyzed_video_clip.write_videofile(f"dataset/result/{gt_name_arr[0]}_result_{prac_name_arr[2]}_{prac_name_arr[3]}")


    return accuracy_interval_list, round((total_accuracy/gt_inform['total_frame'])*100,2);

def calculate_accuracy(lst, sec) :
    st = 0
    end = sec


    # lst = [[3, 0.89], [4, 0.95], [8, 0.88], [15, 0.98], [17, 0.85], [18, 0.87], [30, 0.94]]
    interval_lst = []
    if (len(lst) != 0):

        # 앞뒤로 +-2초 붙여주기 (각 초가 아닌 처음과 끝 부분만)
        # if (lst[0][0] <= 2):
        #     lst.insert(0, [0, lst[0][1]])
        # else:
        #     lst.insert(0, [lst[0][0] - 2, lst[0][1]])
        # if (end - 2 <= lst[len(lst) - 1][0] < end):
        #     lst.append([end, lst[len(lst) - 1][1]])
        # elif (lst[len(lst) - 1][0] < end - 2):
        #     lst.append([lst[len(lst) - 1][0] + 2, lst[len(lst) - 1][1]])

        # print(lst)

        start_sec = 0
        end_sec = 0
        cur_min = 1
        for i in range(len(lst)):
            # 맨 마지막 구간까지 왔을 때
            if (i == len(lst) - 1):
                end_sec = lst[i][0]
                if (lst[i][1] < cur_min): cur_min = lst[i][1];
                # print(start_sec, end_sec, cur_min)
                interval_lst.append([start_sec, end_sec, cur_min])
                break

            if (i == 0):
                start_sec = lst[i][0]

            # 연속되는 초를 한 구간으로 표시
            if (lst[i + 1][0] - lst[i][0] <= 1):
                if (lst[i][1] < cur_min): cur_min = lst[i][1];
            else:
                end_sec = lst[i][0]
                if (lst[i][1] < cur_min): cur_min = lst[i][1];
                print(start_sec, end_sec, cur_min)
                interval_lst.append([start_sec, end_sec, cur_min])
                if (i == len(lst) - 1):
                    break
                start_sec = lst[i + 1][0]
                cur_min = 1

    print(interval_lst)
    return interval_lst



