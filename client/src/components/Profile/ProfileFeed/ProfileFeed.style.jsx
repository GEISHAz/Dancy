import styled, { css } from "styled-components";

export const FeedContainer = styled.div`
	width: 1000px;
`

export const FeedHeader = styled.div`
	height: 120px;
	border: 1px solid black;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom: none;
	background-color: #FFC7BE;
  display: flex;
  flex-direction: column;
  padding-left: 30px;
  justify-content: center;
  row-gap: 2px;
`

export const Archive = styled.div`
	font-family: 'NYJ Gothic B';
  font-size: px;
	color: #434343;
	display: flex;
`

export const BtnBox = styled.div`
	display: flex;
  gap: 11px;
`

export const BtnContainer = styled.div`
	position: relative;
`

export const ArchiveBtn = styled.button`
	border: 1px solid #000000;
	width: 120px;
  height: 34px;
  text-align: center;
  border-radius: 5px;
  position: relative;
	z-index: 1;
	background-color: #898989;
	font-family: 'NYJ Gothic B';
	font-size: 16px;
	color: #FFFFFF;

  ${({ $active }) =>
    $active && css`
    transform: translate(1px, 1px);
		box-shadow: inset 3px 3px rgba(0, 0, 0, 0.3);
		background-color: #F9405E;
		transition: transform 0.2s ease-in-out;

    &:after {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      border-radius: 5px;
      z-index: 1;
    }
  `}
`

export const ArchiveBtnBg = styled.div`
  border: 1px solid black;
  width: 120px;
  height: 34px;
  border-radius: 5px;
  position: absolute;
  top: 0.5px;
  margin: 3px;
  text-align: center;
	background-color: #FFFFFF;

	${({ $active }) =>
    $active && css`
    transform: translate(3px, 3px);
		display: none;
  `}
`

export const FeedBody = styled.div`
	height: 750px;
	border: 1px solid black;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	background-color: #FFFFFF;
  padding: 40px;
  
  overflow: scroll;
  &::-webkit-scrollbar {
    width: 10px;
    
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 20px;
    background: #F9405E;
  }
`
