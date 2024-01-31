
import io
import os
import time
import s3connect as sc
from awscli.compat import raw_input
from compare import compare_video
from savevideo import process_video_and_save_keypoints

s3 = sc.s3_connection()
UPLOAD = 'upload video'
SIDEBAR_OPTIONS = [UPLOAD]

def upload_video(music_name):
    #싱크조절
    sync_frame = 0

    #파일 경로
    file_path_gt = f"./video/{music_name}_gt.mp4"
    file_path_prac = f"./video/{music_name}_prac.mp4"

    #위에 파일경로에 S3 에서 불러와 저장
    ret = sc.s3_get_object(s3, "gumid210bucket", f"video/{music_name}_gt.mp4", f"dataset/video/{music_name}_gt.mp4")
    if ret: print("gt 저장 성공")
    else: print("gt 저장 실패")
    ret = sc.s3_get_object(s3, "gumid210bucket", f"video/{music_name}_gt.mp4", f"dataset/video/{music_name}_prac.mp4")
    if ret: print("prac 저장 성공")
    else: print("prac 저장 실패")

    print("저장중....")
    time.sleep(4)
    #
    try:
        with open(f"dataset/video/{music_name}_gt.mp4", "rb") as file:
            file_content_gt = file.read()
        with open(f"dataset/video/{music_name}_prac.mp4", "rb") as file:
            file_content_prac = file.read()
        # file_content 변수에는 파일의 내용이 바이트 스트림으로 저장됨
        # 이 변수를 활용하여 원하는 작업 수행
    except FileNotFoundError: print(f"{music_name} 파일을 찾을 수 없습니다.")
    except Exception as e: print(f"파일 읽기 중 오류 발생: {e}")

    # gt 파일을 json 분석해서 저장
    # gt 파일 json으로 저장할 dir 생성
    os.makedirs(os.path.join("./dataset/json/", f"{music_name}_gt.mp4"), exist_ok=True)
    print("gt 분석 후 json 저장중....")
    process_video_and_save_keypoints("./dataset/video", f"{music_name}_gt.mp4", "./dataset/json")

    if file_path_prac is not None:
        # # 여기서 g 파일이 업로드된 파일 -> prac 파일이다.
        # g = io.BytesIO(file_content_prac)
        #
        # # 파일 확장자 추가 및 prac 원본 저장
        # video_extension = "mp4"
        # temporary_location = f"./dataset/target/{music_name}_prac.{video_extension}"
        # with open(temporary_location, 'wb') as out:
        #     out.write(g.read())

            # 싱크와 음악 이름을 받고 비교시작
        print("비교시작....")
        compare_video(music_name, sync_frame)

        ret = sc.s3_put_object(s3, "gumid210bucket", f"dataset/result/{music_name}_prac_analyzed.mp4", f"video/{music_name}_prac_analyzed.mp4")

        if ret:
            print("파일 저장 성공")
        else:
            print("파일 저장 실패")


def main():
    music_name = raw_input("what is music name")
    if music_name is not None: upload_video(music_name)
    else : print("error : music_name 이 일치하지 않음")


if __name__ == "__main__":
    main()
