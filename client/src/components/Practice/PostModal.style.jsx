import styled from "styled-components";

export const Modal = styled.div`
  /*스크롤이 되도 모달창이 고정 되도록 position:fixed*/
	position: fixed;
	left: 0;
  bottom: 0;
  /* 자식 컴포넌트인 모달창을 가운데 오게 하기 위해 flex설정*/
  display: flex;
  justify-content: center;
  align-items: center;
	z-index: 1;
`

export const ModalBackdrop = styled.div`
	background-color: rgba(0, 0, 0, 0.4);
	width: 100vw;
  height: 100vh;
`

export const ModalWrap = styled.div`
  position: fixed;
`

export const ModalView = styled.div.attrs((props) => ({role: "dialog"}))`
  width: 505px;
  height:400px;
	border: 1px solid black;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
	border-bottom: none;
  background-color: #FFFDFB;
	padding: 33px;
  display: flex;
  flex-direction: column;
	row-gap: 10px;
`

export const Gap = styled.div`
	display: flex;
	flex-direction: column;
	row-gap: 28px;
`

export const GapRadio = styled(Gap)`
	row-gap: 20px;
`

export const Txt = styled(Gap)`
	row-gap: 20px;
`

export const InputWrap = styled.div`
	display: flex;
	column-gap: 6px;
`

export const RadioWrap = styled.div`
	display: flex;
	column-gap: 32px;
	padding-right: 16px;
	justify-content: end;
`

export const RadioBox = styled.div`
	display: flex;
	column-gap: 12px;
	align-items: center;
`

export const Title = styled.div`
	width: 55px;
  font-family: "BM JUA_TTF";
  font-size: 24px;
	text-align: center;
	padding: 6px;
`

export const BigTitle = styled(Title)`
  font-size: 40px;
	font-weight: bold;
`

export const HashTitle = styled(Title)`
	font-size: 22px;
	line-height: 22px;
`

export const SmallTitle = styled.div`
  font-family: "BM JUA_TTF";
  font-size: 20px;
`

export const Input = styled.input`
	width: 365px;
	height: 50px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 5px;
	word-break: break-all;
	font-family: 'NYJ Gothic B';

	&:focus {
    outline: 2px solid #ff3d5d;
    border: none;
  }
`

export const DetailInput = styled.textarea`
	width: 365px;
	height: 171px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 5px;
	word-break: break-all;
	resize: none;
	font-family: 'NYJ Gothic B';

	&:focus {
    outline: 2px solid #ff3d5d;
    border: none;
  }

	overflow: auto;
	&::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: #ff8791;
  }
`

export const Radio = styled.input.attrs({ 
  type: 'checkbox' })`
	accent-color: #ff919a;
	transform: scale(1.4);
	outline-color: black;
`

export const ModalPost = styled.button`
  background-color: #f03c5a;
	width: 505px;
  height: 69px;
  border: 1px solid black;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  /* align-self: flex-end; */
  text-align: center;
  color: #FFFFFF;
  font-family: "NYJ Gothic B";
  font-size: 24px;
  cursor: pointer;

  &:hover {
    background-color: #c9324b;
  }
`

