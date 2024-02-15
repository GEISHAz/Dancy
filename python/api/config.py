# import pyautogui

def make_keypoints(landmarks, mp_pose, video_inform):
    left_hip = [
        landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].visibility,
    ]
    left_knee = [
        landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].visibility,
    ]
    left_ankle = [
        landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].visibility,
    ]
    right_hip = [
        landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].visibility,
    ]
    right_knee = [
        landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].visibility,
    ]
    right_ankle = [
        landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].visibility,
    ]
    left_shoulder = [
        landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].visibility,
    ]
    left_elbow = [
        landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].visibility,
    ]
    left_wrist = [
        landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].visibility,
    ]
    right_shoulder = [
        landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].visibility,
    ]
    right_elbow = [
        landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].visibility,
    ]
    right_wrist = [
        landmarks[mp_pose.PoseLandmark.RIGHT_WRIST.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_WRIST.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_WRIST.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_WRIST.value].visibility,
    ]
    nose = [
        landmarks[mp_pose.PoseLandmark.NOSE.value].x,
        landmarks[mp_pose.PoseLandmark.NOSE.value].y,
        landmarks[mp_pose.PoseLandmark.NOSE.value].z,
        landmarks[mp_pose.PoseLandmark.NOSE.value].visibility,
    ]
    left_eye = [
        landmarks[mp_pose.PoseLandmark.LEFT_EYE.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_EYE.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_EYE.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_EYE.value].visibility,
    ]
    right_eye = [
        landmarks[mp_pose.PoseLandmark.RIGHT_EYE.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_EYE.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_EYE.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_EYE.value].visibility,
    ]
    left_ear = [
        landmarks[mp_pose.PoseLandmark.LEFT_EAR.value].x,
        landmarks[mp_pose.PoseLandmark.LEFT_EAR.value].y,
        landmarks[mp_pose.PoseLandmark.LEFT_EAR.value].z,
        landmarks[mp_pose.PoseLandmark.LEFT_EAR.value].visibility,
    ]
    right_ear = [
        landmarks[mp_pose.PoseLandmark.RIGHT_EAR.value].x,
        landmarks[mp_pose.PoseLandmark.RIGHT_EAR.value].y,
        landmarks[mp_pose.PoseLandmark.RIGHT_EAR.value].z,
        landmarks[mp_pose.PoseLandmark.RIGHT_EAR.value].visibility,
    ]

    return {
        "1. left_hip": left_hip,
        "2. left_knee": left_knee,
        "3. left_ankle": left_ankle,
        "6. right_hip": right_hip,
        "7. right_knee": right_knee,
        "8. right_ankle": right_ankle,
        "14. left_shoulder": left_shoulder,
        "15. left_elbow": left_elbow,
        "16. left_wrist": left_wrist,
        "19. right_shoulder": right_shoulder,
        "20. right_elbow": right_elbow,
        "21. right_wrist": right_wrist,
        "24. nose": nose,
        "25. left_eye": left_eye,
        "26. right_eye": right_eye,
        "27. left_ear": left_ear,
        "28. right_ear": right_ear,
        "time stamp": video_inform['video_fps'],
    }
#
# def click_video():
#     pyautogui.keyDown('shift')
#     pyautogui.keyDown('tab')
#     pyautogui.keyDown('tab')
#     pyautogui.keyDown('tab')
#     pyautogui.keyDown('tab')
#     pyautogui.keyDown('tab')
#     pyautogui.keyDown('tab')
#     pyautogui.keyUp('shift')
#     pyautogui.keyUp('tab')
#     pyautogui.keyDown('space')
#     pyautogui.keyUp('space')



def make_skip_keypoints():
    left_hip= [
        0.4773867130279541,
        0.5275121331214905,
        -0.018580865114927292,
        0.9977637529373169
    ]
    left_knee= [
        0.4762217104434967,
        0.6190381050109863,
        0.09212937206029892,
        0.6891821026802063
    ]
    left_ankle= [
        0.4663871228694916,
        0.6989002823829651,
        0.23100191354751587,
        0.6301898956298828
    ]
    right_hip= [
        0.5175842046737671,
        0.5282219648361206,
        0.018686417490243912,
        0.9964374303817749
    ]
    right_knee= [
        0.5050365328788757,
        0.6260753870010376,
        0.11932094395160675,
        0.36396926641464233
    ]
    right_ankle= [
        0.4938918650150299,
        0.7074717879295349,
        0.26148703694343567,
        0.4772743284702301
    ]
    left_shoulder= [
        0.4630883038043976,
        0.3992210030555725,
        -0.031028086319565773,
        0.9993428587913513
    ]
    left_elbow= [
        0.45018380880355835,
        0.4730489253997803,
        0.013843009248375893,
        0.8081209063529968
    ]
    left_wrist= [
        0.46758049726486206,
        0.5117812156677246,
        0.12267656624317169,
        0.06945595890283585
    ]
    right_shoulder= [
        0.5368331074714661,
        0.4034740924835205,
        -0.030829034745693207,
        0.9993906021118164
    ]
    right_elbow= [
        0.5464192628860474,
        0.47097131609916687,
        -0.01789182983338833,
        0.8867865800857544
    ]
    right_wrist= [
        0.543722927570343,
        0.523116946220398,
        0.0378970131278038,
        0.40557193756103516
    ]
    nose= [
        0.501584529876709,
        0.3490138053894043,
        0.10608327388763428,
        0.9984757304191589
    ]
    left_eye= [
        0.499605655670166,
        0.3402940630912781,
        0.08376915007829666,
        0.9979326725006104
    ]
    right_eye= [
        0.5073254704475403,
        0.34022167325019836,
        0.08370494097471237,
        0.9982122182846069
    ]
    left_ear= [
        0.49573850631713867,
        0.3417876958847046,
        0.006689234636723995,
        0.9989138841629028
    ]
    right_ear= [
        0.508619487285614,
        0.342059850692749,
        0.005271540489047766,
        0.9988954067230225
    ]

    return {
        "1. left_hip": left_hip,
        "2. left_knee": left_knee,
        "3. left_ankle": left_ankle,
        "6. right_hip": right_hip,
        "7. right_knee": right_knee,
        "8. right_ankle": right_ankle,
        "14. left_shoulder": left_shoulder,
        "15. left_elbow": left_elbow,
        "16. left_wrist": left_wrist,
        "19. right_shoulder": right_shoulder,
        "20. right_elbow": right_elbow,
        "21. right_wrist": right_wrist,
        "24. nose": nose,
        "25. left_eye": left_eye,
        "26. right_eye": right_eye,
        "27. left_ear": left_ear,
        "28. right_ear": right_ear,
        "time stamp": 30.0,
    }

