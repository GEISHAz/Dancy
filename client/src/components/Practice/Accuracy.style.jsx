import styled from "styled-components";
import Note from '../../assets/Practice/accuracyBgImg.png'

export const BgImg = styled.div`
	width: 295px;
	height: 551px;
	background-image: url(${Note});
	background-size: cover;
	position: relative;
`

export const SectionInfo = styled.div`
	position: absolute;
	top: 124px;
	left: 22px;
`

export const ErrorIdx = styled.div`
	border: 1px solid black;
	background-color: #000000;
	width: 10px;
	height: 10px;
`

export const ErrorTxt = styled.div`
	font-family: 'NanumSquareRound';
	font-size: 16px;
`