import styled from "styled-components"


export const SearchContainer = styled.div`
	display: flex;
	flex-direction: row;
	text-align: right;
	font-family: 'NanumSquareRound';
	width: 232px;
	height: 38px;
	position: relative;
`

export const LeftSearchOutline = styled.div`
	width: 188px;
	height: 38px;
	background-color: white;
	border: 1px solid #252525;
	border-top-left-radius: 20px;
	border-bottom-left-radius: 20px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
`

export const SearchInput = styled.input`
	width: 188px;
	height: 38px;
	position: absolute;
	background-color: transparent;
	left: 0;
	top: 0;
	outline: none;
	padding: 0px 14px;
	letter-spacing: 2px;

	&::placeholder {
    letter-spacing: 2px;
		color: gray;
		font-weight: 500;
  }
`

export const RightSearchOutline = styled.div`
	display: flex;
	align-items: center;
	justify-content: center;
	width: 44px;
	height: 38px;
	border: 1px solid #252525;
	border-left: none;
	border-top-right-radius: 20px;
	border-bottom-right-radius: 20px;
	background-color: #0FD6B8;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
`

export const SearchButtonImage = styled.img`

`