import styled, { css } from "styled-components";

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
    $active && css`
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
  background-color: #FFC7BE;
`;

export const NavPractice = styled(PageButton)`
  background-color: #FFE774;
`;

export const NavStage = styled(PageButton)`
  background-color: #0FD6B8;
`;

export const NavProfile = styled(PageButton)`
  background-color: #AABBFF;
  margin-left: -0.5px;
`;

export const NavArea = styled.div`
  width: 100%;
  height: 60px;
  font-size: 20px;
  font-weight: 550;
  color: #252525;
  font-family: 'NanumSquareRound', sans-serif;
`;

export const NavRed = styled.div`
  position: relative;
  background-color: #E23E59;
  width: 100%;
  height: 5px;
  margin: 0 0 20px;
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
  margin-right: 46px;
  font-size: 16px;
`;

export const NavLeftContainer = styled.div`
  position: relative;
`;

export const NavSignUp = styled.div`
  margin-right: 43px;
`;

export const NavLogin = styled.div`
  margin-right: 10px;
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
`;
export const AlertButton = styled.div`
  cursor: pointer;
  margin-right: 48px;
  margin-left: 26px;

  img {
    color: #252525;
    width: 32px;
    height: 32px;
  }
`