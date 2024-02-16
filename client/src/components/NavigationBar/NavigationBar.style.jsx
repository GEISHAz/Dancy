import styled, { css } from "styled-components";
import ProfileImgDefault from "../../assets/profileIntro/ProfileImgDefault.png";

export const PageButton = styled.div`
  border: 1px solid #252525;
  width: 95px;
  height: 32px;
  text-align: center;
  border-radius: 5px;
  position: relative;
  box-shadow: inset 0px 0px rgba(0, 0, 0, 0.2);
  transition: transform 0.2s ease-in-out;

  ${({ $active }) =>
    $active &&
    css`
      transform: translate(3px, 3px);

      &:after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        border-radius: 5px;
        z-index: 1;
        box-shadow: inset 3px 3px rgba(0, 0, 0, 0.2);
        transition: box-shadow 8s ease-in-out;
      }
    `}
`;

export const NavHome = styled(PageButton)`
  background-color: #ffc7be;
`;

export const NavPractice = styled(PageButton)`
  background-color: #ffe774;
`;

export const NavStage = styled(PageButton)`
  background-color: #0fd6b8;
`;

export const NavProfile = styled(PageButton)`
  background-color: #aabbff;
  margin-left: -0.5px;
`;

export const NavArea = styled.div`
  width: 100%;
  height: 60px;
  font-size: 20px;
  font-weight: 550;
  color: #252525;
  font-family: "NanumSquareRound", sans-serif;
  position: relative;
  z-index: 1;
`;

export const NavRed = styled.div`
  position: relative;
  background-color: #e23e59;
  width: 100%;
  height: 5px;
  margin: 0 0 15px;
`;

export const NavTextArea = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 0px;
`;

export const NavLeft = styled.div`
  display: flex;
  align-items: center;
  padding-right: 30px;
  justify-content: flex-start;
  margin: 0 0 0 32px;
  gap: 34px;
`;

export const NavRight = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 20px;
  font-size: 16px;
`;

export const NavLeftContainer = styled.div`
  position: relative;
`;

export const NavLoginWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: auto;
  gap: 20px;
  margin: 0px 30px;
`;

export const NavProfileArea = styled.div`
  display: flex;
  flex-direction: column;
`;

export const NavUserProfileImage = styled.img.attrs(({ src }) => ({
  src: src || ProfileImgDefault,
  alt: "logo",
}))`
  border-radius: 50%;
  width: 38px;
  height: 38px;
  border: none;
  object-fit: cover;
`;

export const NavUserName = styled.div`
  font-size: 16px;
`;

export const NavLogout = styled.div`
  font-size: 14px;
  color: gray;
  cursor: pointer;
  text-align: end;
`;

export const NavLogoutWrapper = styled.div`
  display: flex;
  flex-direction: row;
  width: 150px;
  height: 100%;
  margin: 0px 30px;
  gap: 50px;
`;

export const NavSignUp = styled.div`
  font-size: 16px;
`;

export const NavLogin = styled.div`
  font-size: 16px;
`;

export const Square = styled.div`
  border: 1px solid black;
  width: 95px;
  height: 32px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  margin: 3px;
  z-index: -1;
  text-align: center;
  background-color: white;
`;

export const AlertButton = styled.div`
  cursor: pointer;
  /* margin-right: 48px; */
  margin-left: 26px;

  img {
    color: #252525;
    width: 24px;
    height: 26px;
  }
`;
