import * as M from './MyDance.style'

export default function MyDance () {
	return(

	<M.Wrap>
		<M.Title>나의 영상</M.Title>

		<M.UploadBox> 
			<input type="file" />
			<M.UploadBtn src='src/assets/Create/uploadBtn.png' />
			<M.UploadTxt>영상을 업로드 해주세요.</M.UploadTxt>
		</M.UploadBox>
	</M.Wrap>

	)
}