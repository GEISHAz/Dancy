import { useEffect, useState, useRef } from 'react'
import { getRef, uploadMine } from '../../api/video'
import * as M from './MyDance.style'
import { useRecoilState, useRecoilValue } from 'recoil'
import { myState, originState } from '../../recoil/PracticeState'

export default function MyDance () {
	const refInputRef = useRef(null)
	const originVideo = useRecoilValue(originState)
	const [myVideo, setMyVideo] = useRecoilState(myState)
	const [isUpload, setIsUpload] = useState(false)

	const handleUploadMine = async (e) => {
		console.log('click')
		const newFormData = new FormData();
    newFormData.set('videoFile', e.target.files[0]);
    newFormData.set('referenceVideoId', originVideo.videoId);

		await uploadMine(newFormData)
		.then((res) => {
			console.log(res)
			setMyVideo(res)
			setIsUpload(true)
		})
		.catch((err) => console.error(err))
	}
	
	const handleIsUpload = () => {
		setIsUpload(false)
	}

	return(

	<M.Wrap>
		<M.Title>나의 영상</M.Title>

		<M.UploadBox>
		{ isUpload ?
			<>
				<M.UploadImg src={myVideo.thumbnailImageUrl} />
				<M.Trash onClick={() => handleIsUpload()}>
					<M.TrashImg src='src/assets/Create/delete.png' />
				</M.Trash>
			</>
			:
			<>
				<label for='files'>
					<M.UploadBtn src='src/assets/Create/uploadBtn.png' />
				</label>
				<input 
					type="file" name='file' id='files' 
					style={{display: "none"}}
					ref={refInputRef}
					onChange={(e) => handleUploadMine(e)}
				/>
				<M.UploadTxt>영상을 업로드 해주세요.</M.UploadTxt>
			</>
			}
		</M.UploadBox>
	</M.Wrap>

	)
}