import { styled } from "styled-components";
import DancyImg from "../../assets/join/BigLogo.png";
import * as J from "./SelectJoinMethod.style";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export const SelectJoinArea = styled.div`
  width: 100%;
  height: 680px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

export const LogoArea = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const GoLoginBtn = styled.button`
  width: 420px;
  height: 52px;
  border: 1px solid;
  border-color: black;
  border-radius: 50px;
  background-color: #e23e59;
  color: #ffffff;
  font-family: "NYJ Gothic B";
  font-size: 20px;
  margin-top: 40px;
  cursor: pointer;

  &:hover {
    background-color: #c0354c;
  }
`;

export default function JoinComplete() {
  return (
    <SelectJoinArea>
      <LogoArea>
        <J.JoinLogo>
          <img src={DancyImg}></img>
        </J.JoinLogo>
        <J.WelcomeTitle>축하합니다, 가입이 완료되었습니다!</J.WelcomeTitle>
        <GoLoginBtn>
          <Link to="/login">바로 이용해보기</Link>
        </GoLoginBtn>
      </LogoArea>
    </SelectJoinArea>
  );
}
