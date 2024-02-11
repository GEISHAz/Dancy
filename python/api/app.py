from flask import Flask, request, jsonify

import os
import s3connect as sc
import json
from compare import compare_video
from savevideo import process_video_and_save_keypoints

s3 = sc.s3_connection()

app = Flask(__name__)

@app.route('/uploadVideo', methods=['POST'])
def upload_video():
    '''
    gt_url_arr = [video/gt/asap_gt.mp4]
    gt_url_arr = [video/gt/asap_gt_nickname.mp4]
    prac_url_arr = [video/prac/asap_prac_nickname_uuid.mp4]
    '''

    data = request.get_json()
    gt_url = data.get('gtUrl')
    prac_url = data.get('pracUrl')
    print("초기 세팅 ", gt_url)
    print("초기 세팅 ", prac_url)

    gt_url_arr = gt_url.split('/')
    prac_url_arr = prac_url.split('/')
    gt_name = gt_url_arr[len(gt_url_arr) - 1]
    prac_name = prac_url_arr[len(prac_url_arr) - 1]
    gt_name_arr = gt_name.split('_')        # len = 2 or 3
    prac_name_arr = prac_name.split('_')    # len = 4

    #싱크조절
    sync_frame = 0

    #파일 경로
    file_path_prac = f"./{prac_url}"

    # 기존에 있는 파일이 아니라면 s3에서 파일 가져와서 저장
    if(len(gt_name_arr)==3):
        ret = sc.s3_get_object(s3, "gumid210bucket", f"{gt_url}", f"dataset/{gt_url}")
        if ret: print("gt 저장 성공")
        else: print("gt 저장 실패")
    # prac s3에서 가져와서 저장
    ret = sc.s3_get_object(s3, "gumid210bucket", f"{prac_url}", f"dataset/{prac_url}")
    if ret: print("prac 저장 성공")
    else: print("prac 저장 실패")

    print("영상 준비 중")
    # time.sleep(4)
    #
    try:
        with open(f"dataset/{gt_url}", "rb") as file:
            file_content_gt = file.read()
        with open(f"dataset/{prac_url}", "rb") as file:
            file_content_prac = file.read()
        # file_content 변수에는 파일의 내용이 바이트 스트림으로 저장됨
        # 이 변수를 활용하여 원하는 작업 수행
    except FileNotFoundError: print(f"파일을 찾을 수 없습니다.")
    except Exception as e: print(f"파일 읽기 중 오류 발생: {e}")

    # gt 파일을 json 분석해서 저장
    # gt 파일 json으로 저장할 dir 생성
    # json폴더는 gt/prac 따로 안 나눔
    os.makedirs(os.path.join("./dataset/json/", f"{gt_name}"), exist_ok=True)
    print("gt 분석 후 json 저장중....")
    process_video_and_save_keypoints("./dataset/video/gt", f"{gt_name}", "./dataset/json")

    if file_path_prac is not None:
        # # 여기서 g 파일이 업로드된 파일 -> prac 파일이다.
        # g = io.BytesIO(file_content_prac)
        #z
        # # 파일 확장자 추가 및 prac 원본 저장
        # video_extension = "mp4"
        # temporary_location = f"./dataset/target/{music_name}_prac.{video_extension}"
        # with open(temporary_location, 'wb') as out:
        #     out.write(g.read())

            # 싱크와 음악 이름을 받고 비교시작
        print("비교시작....")
        accuracy_result,total_accuracy = compare_video(gt_url, prac_url, sync_frame)
        imageurl = f"thumbnailimage/{gt_name_arr[0]}_image_{prac_name_arr[2]}_{prac_name_arr[3]}"

        print(total_accuracy)
        ret = sc.s3_put_object(s3, "gumid210bucket",
                               f"dataset/result/{gt_name_arr[0]}_result_{prac_name_arr[2]}_{prac_name_arr[3]}",
                               f"video/result/{gt_name_arr[0]}_result_{prac_name_arr[2]}_{prac_name_arr[3]}")

        sc.s3_put_object(s3, "gumid210bucket",
                               f"dataset/image/{gt_name_arr[0]}_prac_{prac_name_arr[2]}_{prac_name_arr[3]}.jpg",
                               imageurl)

        if ret:
            print("파일 저장 성공")
            # 결과를 원하는 형식으로 변환
            converted_result = []
            for item in accuracy_result:
                converted_item = {
                    "start": item[0],
                    "end": item[1],
                    "accuracy": round(item[2]*100,2)
                }
                converted_result.append(converted_item)

            # 새로 생성한 video의 링크와 정확도 계산 결과
            s3url = f"video/result/{gt_name_arr[0]}_result_{prac_name_arr[2]}_{prac_name_arr[3]}"
            result = {
                "list": converted_result,
                "totalUrl": s3url,
                "thumbnailImageUrl": imageurl,
                "total_accuracy": total_accuracy
            }
            print(result)
            return jsonify(result)
        else:
            print("파일 저장 실패")
            return "ERROR"

@app.route('/sendData', methods=['POST'])
def send_data():
    testData = {
        "key1": "value1",
        "key2": "value2"
    }
    return jsonify(testData)

@app.route('/test/<param>')
def testfunc(param):
    return param

if __name__ == "__main__":
    app.run('0.0.0.0', port=5000, debug=True)
