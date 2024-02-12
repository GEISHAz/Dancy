import Video from "../components/Practice/Video"
import { useEffect, useState } from 'react';
import PostModal from "../components/Practice/PostModal"
import { practiceState } from "../recoil/PracticeState";
import { useRecoilState } from "recoil";

const video = {
  'list': [
    {'start': 6, 'end': 11, 'accuracy': 89.31},
    {'start': 25, 'end': 25, 'accuracy': 92.79},
    {'start': 29, 'end': 30, 'accuracy': 84.25}
  ], 
  'totalUrl': 'https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/video/result/asap_result_cnh2_uuid.mp4',
  'thumbnailImageUrl': 'thumbnailimage/asap_image_cnh2_uuid.mp4',
  'total_accuracy': 94.69
}

export default function Practice() {
  const [videoInfo, setVideoInfo] = useRecoilState(practiceState);

  useEffect(() => {
    setVideoInfo(video)
  }, [])

	const [isOpen, setIsOpen] = useState(false);
	const getData = childData => {
		setIsOpen(childData);
	};
	
  return (
    <div className="flex flex-col justify-center mt-14 gap-x-4">
			{isOpen && (<PostModal videoInfo={videoInfo} getData={getData}/>)}

      <Video videoInfo={videoInfo} getData={getData} />
		</div>
  )
}