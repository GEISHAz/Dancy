import styled from "styled-components";
import DefaultProfileImg from '../../assets/profileIntro/ProfileImgDefault.png'
import EditImg from '../../assets/editimage.png'
import DeleteImg from '../../assets/deleteimage.png'

const hashTagColors = {
  "#FFF4B5": "#FFF4B5",
  "#C0FFF4": "#C0FFF4",
  "#D4DCFF": "#D4DCFF"
};

export const VideoPlayer = styled.div`
  background-color: black;
  width: 100%;
  height: 552px;
  display: flex;
  justify-content: center;
  border-radius: 20px;
  object-fit: contain;
  /* align-items: center; */
`

export const VideoDetailContainer = styled.div`
  margin-top: 20px;
  /* width: 900px; */
  /* height: 127px; */
  font-family: 'NanumSquareRound', sans-serif;
`;

export const VideoDetailArea = styled.div`
  display: flex;
  flex-direction: column;
  row-gap: 10px;
`;

export const VideoContentArea = styled.div`
  display: flex;
  /* gap: 52px; */
  margin-top: 14px;
  margin-left: 13px;
  justify-content: space-between;
  align-items: start;
`;

export const VideoTitle = styled.div`
  margin-left: 10px;
  font-size: 28px;
  letter-spacing: 1.5;
  font-weight: 600;
`;

export const VideoUserDetailArea = styled.div`
  display: flex;
  flex-direction: row;
  gap: 15px;
  align-items: center;
  margin-top: 30px;
`;

export const VideoUserName = styled.div`
  font-size: 20px;
  font-weight: 550;
`;

export const VideoFollowArea = styled.div`
  display: flex;
  align-items: center;
  gap: 25px;
  margin-top: 10px;
`;

export const VideoFollower = styled.div`
  font-size: 16px;
`;

export const VideoUserProfileImage = styled.img.attrs(props => ({ 
  src: props.src || DefaultProfileImg }))`
  border: none;
  width: 60px;
  height: 60px;
  border-radius: 100%;
`;

export const VideoFollowBtn = styled.button`
  background-color: ${props => props.$isFollow ? '#898989' : '#aabbff'};
  border: 1px solid black;
  border-radius: 50px;
  width: 70px;
  height: 28px;
  color: white;
  font-size: 14px;
`;

export const VideoAccuracyArea = styled.div`
  width: 700px;
`;

export const VideoUpperContainer = styled.div`
  display: flex;
  /* width: 600px; */
  height: 28px;
  border: 1px solid #252525;
  border-top-left-radius: 7px;
  border-top-right-radius: 7px;
  background-color: #fffceb;
  text-align: left;

  div {
    display: flex;
    align-items: center;
    font-size: 14px;
    font-weight: 550;
    margin-left: 10px;
  }
`;

export const VideoLowerContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #FFFFFF;
  padding: 5px 10px;
  /* width: 600px; */
  /* height: 68px; */
  border: 1px solid #252525;
  border-bottom-left-radius: 7px;
  border-bottom-right-radius: 7px;
  border-top: none;
`;

export const VideoContent = styled.div`
  padding: 5px;
  padding-bottom: 15px;
`

export const VideoUserDetail = styled.div`
	
`

export const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: end;
  align-items: center;
  margin-bottom: 5px;
`;

export const HashTagArea = styled.div`
  display: flex;
  gap: 6px;
`;

export const AccuracyBtn = styled.button`
  width: 75px;
  height: 28px;
  border: 1px solid #252525;
  border-radius: 7px;
  color: black;
  font-size: 14px;
  background-color: #e4b1c9;
  font-weight: 600;
`;

export const HashTagBtn = styled.button`
  width: 70px;
  height: 28px;
  border: 1px solid #252525;
  border-radius: 7px;
  color: black;
  font-size: 14px;
  font-weight: 600;
  background-color: ${props => hashTagColors[props.color]};
`;

export const HashTagContainer = styled.div``;



export const FunctionWrapper = styled.div.attrs(({ isMyArticle}) => {
	isMyArticle: isMyArticle
})`
  display: flex;
	padding-left: 6px;
	padding-right: 6px;
  justify-content: ${(props) => (props.isMyArticle ? 'space-between' : 'end')};
  gap: 15px;
  margin-bottom: 4px;
`
export const BtnWrap = styled.div`
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 4px;

  img {
    cursor: pointer;
  }
  
  div {
    display: flex;
    font-size: 20px;
  }
`

export const SaveBtn = styled.img`
  width: 32px;
  height: 36px;
  padding-bottom: 1px;
`

export const WithBtn = styled.img`
  border: 1.5px solid black;
  border-radius: 50%;
  width: 40px;
  height: 40px;
`

export const WithArea = styled.div`
  width: 40px;
  height: 40px;
`

export const LikeBtn = styled.img`
  width: 40px;
  height: 40px;
  margin-left: -3px;
	font-size: 16px;
`

export const LikeRate = styled.div`
	
`

export const DropdownToggle = styled.div`
  position: relative;
  cursor: pointer;
`

export const LikeUserList = styled.div`
  position: absolute;
  width: 200px;
  height: 230px;
  top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 20px;
  z-index: 1000;
`;

export const LikeUserInfo = styled.div`
  width: 160px;
  height: 50px;
  display: flex;
  align-items: center;
  column-gap: 30px;
  /* border-bottom: 1px solid #ddd; */
`
export const LikeUserImg = styled.img.attrs(({ src }) => ({ src: src || DefaultProfileImg }))`
  width: 28px;
  height: 28px;
  border-radius: 50%;
`
export const LikeUserNickName = styled.div`
  font-family: 'NYJ Gothic L';
  font-size: 10px;
`

export const EditWrap = styled.div.attrs(({ isMyArticle }) => ({
	isMyArticle: isMyArticle
}))`
	display: ${(props) => (props.isMyArticle ? 'flex' : 'none')};
  align-items: center;
  gap: 15px;
  margin-bottom: 4px;

  img {
    cursor: pointer;
  }
`

export const EditBtn = styled.button`
	width: 30px;
	height: 30px;
	background-image: url(${EditImg});
	background-size: contain;
`

export const DeleteBtn = styled.button`
	width: 25px;
	height: 30px;
	background-image: url(${DeleteImg});
	background-size: contain;
`