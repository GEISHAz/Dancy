import styled, { css } from "styled-components";

// 전체 화면 구성
export const SearchResultArea = styled.div`
  width: 100%;
  height: 1000px;
  display: flex;
  margin-top: 78px;
  align-items: center;
  flex-direction: column;
`;

export const SearchContainer = styled.div`
  width: 935px;
`;

export const SearchHeader = styled.div`
  height: 85px;
  border: 1px solid black;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom: none;
  background-color: #ffc7be;
  display: flex;
  flex-direction: column;
  padding-left: 30px;
  justify-content: center;
  row-gap: 2px;
`;

export const Archive = styled.div`
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 16px;
  color: #434343;
  display: flex;
  margin-left: 2px;
`;

export const BtnBox = styled.div`
  display: flex;
  gap: 11px;
`;

export const BtnContainer = styled.div`
  position: relative;
  scale: 0.85;
`;

export const TitleBtn = styled.button`
  border: 1px solid #000000;
  width: 120px;
  height: 34px;
  text-align: center;
  border-radius: 5px;
  position: relative;
  z-index: 1;
  background-color: #898989;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 16px;
  color: #ffffff;

  ${({ $active }) =>
    $active &&
    css`
      transform: translate(1px, 1px);
      box-shadow: inset 3px 3px rgba(0, 0, 0, 0.3);
      background-color: #f9405e;
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
`;

export const ArchiveBtn = styled.button`
  border: 1px solid #000000;
  width: 120px;
  height: 34px;
  text-align: center;
  border-radius: 5px;
  position: relative;
  z-index: 1;
  background-color: #898989;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 16px;
  color: #ffffff;

  ${({ $active }) =>
    $active &&
    css`
      transform: translate(1px, 1px);
      box-shadow: inset 3px 3px rgba(0, 0, 0, 0.3);
      background-color: #f9405e;
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
`;

export const ArchiveBtnBg = styled.div`
  border: 1px solid black;
  width: 120px;
  height: 34px;
  border-radius: 5px;
  position: absolute;
  top: 0.5px;
  margin: 3px;
  text-align: center;
  background-color: #ffffff;

  ${({ $active }) =>
    $active &&
    css`
      transform: translate(3px, 3px);
      display: none;
    `}
`;

export const FeedBody = styled.div`
  border: 1px solid black;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #ffffff;
  padding: 40px;

  overflow-y: scroll;
  &::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: #ff8791;
  }
`;
