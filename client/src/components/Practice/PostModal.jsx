import { useNavigate } from 'react-router-dom';
import * as P from './PostModal.style'

export default function PostModal ({ getData }) {
	const data = false;
	const postData = () => {
		getData(data)
	}
	
	const navigate = useNavigate();
	const postHandler = () => {
		navigate(`/stage`)
	};
	
	return (
		<P.Modal>
			<P.ModalBackdrop onClick={postData} />
			{/* </P.ModalBackdrop> */}

			<P.ModalWrap>
				<P.ModalView>
					<P.BigTitle>POST</P.BigTitle>

					<P.GapRadio>
						<P.Gap>
							<P.Txt>
								<P.InputWrap>
									<P.Title>제목</P.Title>
									<P.Input />
								</P.InputWrap>

								<P.InputWrap>
									<P.Title>내용</P.Title>
									<P.DetailInput />
								</P.InputWrap>
							</P.Txt>

							<P.InputWrap>
								<P.HashTitle>해시태그</P.HashTitle>
								<P.Input />
							</P.InputWrap>
						</P.Gap>
						
						<P.RadioWrap>				
							<P.RadioBox>
								<P.SmallTitle>스켈레톤</P.SmallTitle>
								<P.Radio />
							</P.RadioBox>
							
							<P.RadioBox>
								<P.SmallTitle>평균 정확도</P.SmallTitle>
								<P.Radio />
							</P.RadioBox>
						</P.RadioWrap>
					</P.GapRadio>
				</P.ModalView>

				<P.ModalPost onClick={postHandler}>게시하기</P.ModalPost>
			</P.ModalWrap>
		</P.Modal>
	)
}