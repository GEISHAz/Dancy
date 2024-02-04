import styled from "styled-components";

const hashTagColors = {
  "#FFF4B5": "#FFF4B5",
  "#C0FFF4": "#C0FFF4",
  "#D4DCFF": "#D4DCFF"
};


export const VideoDetailContainer = styled.div`
  margin: 30px;
  width: 900px;
  height: 127px;
  font-family: 'NanumSquareRound', sans-serif;
`;

export const VideoDetailArea = styled.div``;

export const VideoContentArea = styled.div`
  display: flex;
  gap: 52px;
  margin-top: 14px;
  margin-left: 13px;
`;

export const VideoTitle = styled.div`
  font-size: 28px;
  letter-spacing: 1.5;
  font-weight: 600;
`;

export const VideoUserDetailArea = styled.div`
  display: flex;
  flex-direction: row;
  gap: 15px;
  align-items: center;
`;

export const VideoUserName = styled.div`
  font-size: 20px;
  font-weight: 550;
`;

export const VideoFollowArea = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  margin-top: 10px;
`;

export const VideoFollower = styled.div`
  font-size: 16px;
`;

export const VideoUserProfileImage = styled.img`
  border: none;
  background-color: black;
  width: 60px;
  height: 60px;
  border-radius: 100%;
`;

export const VideoFollowBtn = styled.button`
  background-color: ${props => props.$follow ? 'lightgray' : '#aabbff'};
  border: 1px solid black;
  border-radius: 50px;
  width: 70px;
  height: 28px;
  color: white;
  font-size: 14px;
`;

export const VideoAccuracyArea = styled.div``;

export const VideoUpperContainer = styled.div`
  display: flex;
  width: 600px;
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

  padding: 5px 10px;
  width: 600px;
  height: 68px;
  border: 1px solid #252525;
  border-bottom-left-radius: 7px;
  border-bottom-right-radius: 7px;
  border-top: none;
`;

export const VideoUserDetail = styled.div`
	
`

export const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

export const HashTagArea = styled.div`
  display: flex;
  gap: 6px;
`;

export const AccuracyBtn = styled.button`
  width: 70px;
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