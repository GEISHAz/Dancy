import styled from "styled-components";
import defaultProfileImg from '../../assets/profileIntro/ProfileImgDefault.png'

export const RecommentContainer = styled.div`
	display: flex;
	flex-direction: column;
	margin: 20px 0px;
	width: 600px;
	height: auto; 
	font-size: 14px;
	font-family: 'NanumSquareRound';
`

export const RecommentArea = styled.div`
  position: relative;
  display: flex;
  flex-direction: row;
  gap: 10px;
`

export const RecommentUserProfileImage = styled.img.attrs(({ src }) => ({ src: src || defaultProfileImg}))`
	width: 35px;
  height: 35px;
  border-radius: 50%;
  background-color: lightgray;
  border: none;
  cursor: pointer;
  object-fit: cover;
`

export const RecommentUserDetail = styled.div`
  margin-bottom: 10px;
	width: 100%;
`

export const RecommentUserNameArea = styled.div`
	display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
	width: 100%;
  margin-bottom: 5px;
`

export const RecommentUserName = styled.div`
	font-weight: 500;
`

export const DropdownToggle = styled.div`
  position: relative;
  cursor: pointer;
`

export const RecommentEditDeleteArea = styled.div`
  position: absolute;
  top: 25px;
  right: -25px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 10px;
  img {
    width: 16px;
    height: 18px;
  }
`



export const RecommentEditImage = styled.img`
  cursor: pointer;
`

export const RecommentDeleteImage = styled.img`
	cursor: pointer;
`

export const RecommentContentArea = styled.div`
	display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 5px;
`

export const RecommentContent = styled.div`
`

export const RecommentLikeImage = styled.img`
	width: 20px;
  height: 20px;
  cursor: pointer;
`

export const RecommentCreateArea = styled.div`
	display: flex;
  justify-content: space-between;
  align-items: center;
  div{
    display: flex;
    flex-direction: row;
    gap: 8px;
    align-content: center;
  }
`

export const RecommentCreatedAt = styled.div`
	font-size: 14px;
  color: gray;
`

export const RecommentCreate = styled.div`
	color: gray;
  font-size: 13px;
  cursor: pointer;
  font-weight: 600;
`

export const RecommentNumberOfLikes = styled.div`
	font-size: 12px;
  color: gray;
`