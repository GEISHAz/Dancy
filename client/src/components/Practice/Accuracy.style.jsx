import styled from "styled-components";
import Note from '../../assets/Practice/accuracyBgImg.png'

export const BgImg = styled.div`
	width: 295px;
	height: 551px;
	background-image: url(${Note});
	background-size: cover;
	position: relative;
`

export const SectionInfo = styled.button`
	position: absolute;
	top: 124px;
	left: 25px;
  display: flex;
  flex-direction: column;
  row-gap: 10px;
`

export const ErrorIdx = styled.div`
	width: 28px;
	height: 28px;
	border: 1px solid black;
  border-radius: 7px;
	background-color: #C0FFF4;
  text-align: center;
  font-family: 'NYJ Gothic B';
  font-size: 16px;
`

export const ErrorSec = styled.div`
  width: 125px;
	font-family: 'NanumSquareRound';
	font-size: 18px;
  font-weight: bold;
  text-align:center;
`

export const ErrorAccu = styled.div`
  width: 35px;
	font-family: 'NanumSquareRound';
	font-size: 18px;
  font-weight: bold;
  text-align:center;
`