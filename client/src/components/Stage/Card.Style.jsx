import styled from "styled-components";
import DefaultImg from '../../assets/profileIntro/ProfileImgDefault.png'

export const CardContainer = styled.div`
  border: none;
  border-radius: 10px;
  box-shadow: 0px 6px 6px rgba(0, 0, 0, 0.1);
  transition: scale 0.1s ease-in-out;

  &:hover,
  &:focus {
    scale: 1.05;
  }
`;

export const CardUpperContainer = styled.img`
  width: 300px;
  height: 200px;
  border: 1px solid #252525;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  margin-top: 20px;
`;

export const CardLowerContainer = styled.div`
  display: flex;
  width: 100%;
  height: 72px;
  border: 1px solid #252525;
  border-top: none;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: ${(props) => props.color || "#FFFCEB"};
`;

export const CardDetailContainer = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 10px;
  margin: 8px 15px;
`;

export const CardDetailArea = styled.div`
  display: flex;
  flex-direction: column;
`;

export const CardProfileImage = styled.img.attrs(({src}) => ({
  src: src || DefaultImg
}))`
  border-radius: 50%;
  margin-top: 5px;
  width: 32px;
	height: 32px; 
  object-fit: cover;
`;

export const CardTitleDiv = styled.div`
  width : 190px;
  text-align: left;
  overflow: hidden; // 내용이 넘칠 경우 숨김 처리
  white-space: nowrap; // 텍스트를 한 줄로 표시
  text-overflow: ellipsis; // 넘치는 텍스트를 "..."로 표시
`;

export const CardTitle = styled.span`
  font-family: "남양주 고딕 B", sans-serif;
  font-size: 16px;
  color: #252525;
  font-weight: 550;
`;

export const CardUserName = styled.span`
  font-family: "남양주 고딕 L", sans-serif;
  font-size: 12px;
  text-align: left;
`;

export const CardViewAndDate = styled.div`
  font-size: 8px;
  text-align: left;
`;