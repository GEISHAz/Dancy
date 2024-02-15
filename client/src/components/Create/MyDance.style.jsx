import styled from "styled-components";

export const Wrap = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.div`
  font-family: "NYJ Gothic";
  font-size: 22px;
  font-weight: bold;
`;

export const UploadBox = styled.button`
  width: 570px;
  height: 505px;
  background-color: white;
  border: 1px solid black;
  border-radius: 10px;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  row-gap: 5px;
  position: relative;
`;

export const UploadBtn = styled.img`
  width: 45px;
  height: 45px;
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
