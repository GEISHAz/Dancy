from flask import Flask, request, jsonify
from time import time

app = Flask(__name__)


@app.route('/uploadVideoTest', methods=['POST'])
def upload_video():
    data = request.get_json()
    gt_url = data.get('gtUrl')
    prac_url = data.get('pracUrl')

    time.sleep(30)

    s3Url = 'video/result/asap_result_cnh2_uuid.mp4'
    imageUrl = 'thumbnailimage/asap_image_cnh2_uuid.jpg'

    accuracy_list = []
    converted_item_one = {
        "start": 6,
        "end": 11,
        "accuracy": 89.31
    }

    converted_item_two = {
        "start": 25,
        "end": 25,
        "accuracy": 92.79
    }

    accuracy_list.append(converted_item_one)
    accuracy_list.append(converted_item_two)

    result = {
        "list": accuracy_list,
        "totalUrl": s3Url,
        "thumbnailImageUrl": imageUrl,
        "total_accuracy": 94.69
    }

    return jsonify(result)


if __name__ == "__main__":
    app.run('0.0.0.0', port=5000, debug=True)
