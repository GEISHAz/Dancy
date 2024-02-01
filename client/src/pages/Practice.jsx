import Accuracy from "../components/Practice/Accuracy"
import PostBtn from "../components/Practice/PostBtn"
import Video from "../components/Practice/Video"
import VideoBtn from "../components/Practice/VideoBtn"

export default function Practice() {
  return (
    <div className="flex justify-center mt-14 gap-x-4">
			<div className="flex flex-col gap-y-5">
				<Video />
				<VideoBtn />
			</div>
			
			<div className="flex flex-col gap-y-5 justify-between">
				<Accuracy />
				<PostBtn />
			</div>
		</div>
  )
}