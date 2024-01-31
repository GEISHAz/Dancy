import styled, { css} from "styled-components";

export const FeedContainer = styled.div`

`

export const FeedHeader = styled.div`
	width: 760px;
	height: 88px;
	border: 1px solid black;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom: none;
	background-color: #FFC7BE;
`

export const Archive = styled.div`
	font-family: 'NYJ Gothic B';
	color: #434343;
	display: flex;
`

export const BtnContainer = styled.div`
	position: relative;
`

export const ArchiveBtn = styled.button`
	border: 1px solid #000000;
	width: 88px;
  height: 22px;
  text-align: center;
  border-radius: 5px;
  position: relative;
	z-index: 1;
	background-color: #898989;
  /* transition: transform 0.2s ease-in-out; */
	font-family: 'NYJ Gothic B';
	font-size: 12px;
	color: #FFFFFF;

  ${({ $active }) =>
    $active && css`
    transform: translate(1px, 1px);
		box-shadow: inset 3px 3px rgba(0, 0, 0, 0.2);
		background-color: #F9405E;
		transition: transform 0.2s ease-in-out;

    &:after {
      /* content: ""; */
      position: absolute;
			/* margin: 1px; */
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
  width: 88px;
  height: 22px;
  border-radius: 5px;
  position: absolute;
  top: 3px;
  left: -1px;
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
	width: 760px;
	height: 750px;
	border: 1px solid black;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	background-color: #FFFFFF;
`