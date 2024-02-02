import Accuracy from "../components/Practice/Accuracy"
import PostBtn from "../components/Practice/PostBtn"
import Video from "../components/Practice/Video"
import VideoBtn from "../components/Practice/VideoBtn"
import { useState } from 'react';
import PostModal from "../components/Practice/PostModal"

export default function Practice() {
  const videoInfo = {
    avgAccuracy : 79,
    errorList : [
      {key: 1, section: '02:22 ~ 03:27', accuracy: 87},
      {key: 2, section: '02:42 ~ 03:27', accuracy: 24},
      {key: 3, section: '02:12 ~ 03:27', accuracy: 90},
      {key: 4, section: '02:43 ~ 03:27', accuracy: 100},
      {key: 5, section: '02:01 ~ 03:27', accuracy: 72},
      {key: 6, section: '02:23 ~ 03:27', accuracy: 36},
    ]
  }

	const [isOpen, setIsOpen] = useState(false);
	const getData = childData => {
		setIsOpen(childData);
	};
	
  return (
    <div className="flex justify-center mt-14 gap-x-4">
			{isOpen && (<PostModal getData={getData}/>)}

			<div className="flex flex-col gap-y-5">
				<Video />
				<VideoBtn avgAccuracy={videoInfo.avgAccuracy} />
			</div>
			
			<div className="flex flex-col gap-y-5 justify-between">
				<Accuracy errorList={videoInfo.errorList} />
				<PostBtn getData={getData} />
			</div>
		</div>
  )
}