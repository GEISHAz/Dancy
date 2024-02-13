import { useEffect, useState, useRef } from 'react'
import { getRef, uploadRef } from '../../api/video'
import * as O from './OriginDance.style'
import { useRecoilState, useRecoilValue } from 'recoil'
import { originState } from '../../recoil/PracticeState'

export default function OriginDance() {
	const [refVideoList, setRefVideoList] = useState([])
	const [refVideo, setRefVideo] = useRecoilState(originState)
	const refInputRef = useRef(null)
	const [isUpload, setIsUpload] = useState(false)
	const refThumb = useRecoilValue(originState)

	useEffect(() => {
		getRef()
		.then((res) => {
			console.log(res)
			setRefVideoList(res)
		})
		.catch((err) => console.error(err))
	}, [])

	const handleUploadRef = async (e) => {
		const newFormData = new FormData();
    newFormData.set('videoFile', e.target.files[0]);

		await uploadRef(newFormData)
		.then((res) => {
			setRefVideo(res)
			setIsUpload(true)
		})
		.catch((err) => console.error(err))
	}

	const handleSelectRef = (item) => {
		setRefVideo({
			'thumbnailImageUrl': item.thumbnailImageUrl,
			'videoId': item.videoId,
			'resultVideoUrl': item.videoUrl,
		})
		setIsUpload(true)
	}

	const handleIsUpload = () => {
		setIsUpload(false)
	}

	return (
		<O.Wrap>
			<O.Title>안무 영상</O.Title>

			<O.Container>
					{isUpload ? 
					<>
						<O.UploadImg src={refThumb.thumbnailImageUrl} />
						<O.Trash onClick={() => handleIsUpload()}>
							<O.TrashImg src='src/assets/Create/delete.png' />
						</O.Trash>
					</>
					: 
					<>
						<O.VideoBox>
							{refVideoList.map((item, index) => (
								<O.VideoThumb 
									key={index} 
									src={item.thumbnailImageUrl}
									onClick={() => handleSelectRef(item)}
								/>
							))}
						</O.VideoBox>
						<O.UploadBox>
							<label for='file'>
								<O.UploadBtn src='src/assets/Create/uploadBtn.png' />
							</label>
							<input 
								type="file" name='file' id='file' 
								style={{display: "none"}} 
								ref={refInputRef}
								onChange={(e) => handleUploadRef(e)}
							/>
							<O.UploadTxt>영상을 업로드 해주세요.</O.UploadTxt>
						</O.UploadBox>
					</>
					}
			</O.Container>
		</O.Wrap>
	)
}