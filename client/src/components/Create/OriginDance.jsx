import * as O from './OriginDance.style'

const imgList = [
	{key: 1, src: 'src/assets/profileFeed/archiveThumb.png'},
	{key: 2, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 3, src: 'src/assets/profileFeed/archiveThumb.png'},
	{key: 4, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 5, src: 'src/assets/profileFeed/archiveThumb.png'},
	{key: 6, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 7, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 8, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 9, src: 'src/assets/profileFeed/uploadThumb.png'},
	{key: 10, src: 'src/assets/profileFeed/uploadThumb.png'},
]
// const imgList = [
// 	{key: 1, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 2, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 3, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 4, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 5, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 6, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 7, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 8, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 9, src: 'src/assets/ProfileImgDefault.png'},
// 	{key: 10, src: 'src/assets/ProfileImgDefault.png'},
// ]

export default function OriginDance() {
	return (
		<O.Wrap>
			<O.Title>안무 영상</O.Title>

			<O.Container>
				<O.VideoBox>
					{imgList.map((imgItem) => (
						<O.VideoThumb key={imgItem.key} src={imgItem.src} />
					))}
				</O.VideoBox>

				<O.UploadBox> 
					<input type="file" />
					<O.UploadBtn src='src/assets/Create/uploadBtn.png' />
					{/* <O.UploadBtn src='src/assets/Practice/uploadBtn.png' /> */}
					<O.UploadTxt>영상을 업로드 해주세요.</O.UploadTxt>
				</O.UploadBox>
			</O.Container>
		</O.Wrap>
	)
}