import Video from "../components/Practice/Video"
import { useEffect, useState } from 'react';
import PostModal from "../components/Practice/PostModal"
import { practiceState, resultState } from "../recoil/PracticeState";
import { useRecoilState, useRecoilValue } from "recoil";
import { analyzeResult } from "../api/video";

// const video = {
//   "wrongSections": [
//     {"start": 6, "end": 11, "accuracy": 89.31},
//     {"start": 25, "end": 25, "accuracy": 92.79},
//   ],
//   "videoUrl": "https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/video/result/asap_result_cnh2_uuid.mp4",
//   "thumbnailImageUrl": "https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/thumbnailimage/asap_image_cnh2_uuid.jpg",
//   "nickname": "dongw",
//   "videoTitle": "gt_asdf_url.mp4",
//   "score": 94.66
// }

export default function Practice() {
	const transVideo = useRecoilValue(resultState)
  const [videoInfo, setVideoInfo] = useRecoilState(practiceState);

  useEffect(() => {
		analyzeResult(transVideo.videoId)
		.then((res) => {
			setVideoInfo(res)
		})
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