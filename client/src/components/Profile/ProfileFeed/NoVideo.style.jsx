import styled from "styled-components";
import noVideoImg from "../../../assets/profileFeed/noVideo.png";

export const Wrap = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  row-gap: 28px;
`;

export const NoImg = styled.div`
  width: 60px;
  height: 60px;
  background-image: url(${noVideoImg});
  background-size: contain;
`;

export const NoTxt = styled.div`
  font-family: "NYJ Gothic";
  font-weight: 300;
  font-size: 24px;
`;
