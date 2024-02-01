import styled from "styled-components";

export const Wrap = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`

export const Container = styled.div`
	width: 570px;
	height: 505px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`

export const Title = styled.div`
	font-family: 'NYJ Gothic B';
	font-size: 22px;
`

export const VideoBox = styled.div`
	height: 65%;
	padding-top: 10px;
	padding-left: 10px;
	/* padding-right: 5px; */
	background-color: #FFFFFF;
  border: 1px solid black;
	border-bottom: none;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	display: flex;
	align-items: center;
	flex-wrap: wrap;
	gap: 5px;

	overflow-y: auto;
	&::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: #ff8791;
  }
`

export const VideoThumb = styled.img`
	height: 49%;
	width: 32% ;
	object-fit:cover;
	background-size: cover;
	border-radius: 10px;
`

export const UploadBox = styled.button`
	width: 100%;
	height: 35%;
	background-color: white;
  border: 1px solid black;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	border-top: none;
	cursor: pointer;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	row-gap: 5px;
`

export const UploadBtn = styled.img`
	width: 45px;
	height: 45px;
`

export const UploadTxt = styled.div`
	font-family: 'NanumSquareRound';
	font-size: 16px;
`
