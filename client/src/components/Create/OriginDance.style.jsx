import styled from "styled-components";
import UploadBtnImg from "../../assets/Create/uploadBtn.png";

export const Wrap = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Container = styled.div`
  width: 570px;
  height: 505px;
  display: flex;
  background-color: #ffffff;
  border: 1px solid black;
  border-radius: 10px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

export const Title = styled.div`
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 22px;
`;

export const VideoBox = styled.div`
  height: 65%;
  padding-top: 10px;
  padding-left: 10px;
  /* padding-right: 5px; */
  background-color: #ffffff;
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
`;

export const VideoThumb = styled.img`
  height: 49%;
  width: 32%;
  object-fit: cover;
  background-size: cover;
  border-radius: 10px;
`;

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
`;

export const UploadBtn = styled.img`
  width: 45px;
  height: 45px;
  /* background-image: url(UploadBtnImg); */
`;

export const UploadTxt = styled.div`
  font-family: "NanumSquareRound";
  font-size: 16px;
`;

export const UploadImg = styled.img`
  width: 570px;
  height: 505px;
  object-fit: cover;
  border-radius: 10px;
`;

export const Trash = styled.button`
  width: 100px;
  height: 100px;
  border-radius: 50%;
  position: absolute;
  z-index: 1;
  background-color: #dedede;
  opacity: 0.8;
  right: 15px;
  bottom: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const TrashImg = styled.img`
  width: 50px;
  height: 50px;
`;
