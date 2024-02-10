import { styled } from "styled-components";
import { useState } from "react";
import JoinForm from "./JoinForm";
import { useNavigate } from "react-router-dom";
import {
  WelcomeTitle,
  JoinChoiceTitle,
  JoinBtnContainer,
  LogoBox,
  JoinMethodText,
  JoinMethodInfoText,
  JoinLogo,
  EmailLogo,
  GoogleLogo,
  NaverLogo,
  KakaoLogo,
} from "./SelectJoinMethod.style";
import EmailImg from "../../assets/join/email.png";
import DancyImg from "../../assets/join/BigLogo.png";

export const SelectJoinArea = styled.div`
  width: 100%;
  padding: 3.5rem;
`;

export const LogoArea = styled.div`
  width: 100%;
  height: 10%;
  display: flex;
  justify-content: center;
`;

export const TextArea = styled.div`
  width: 100%;
  height: 10%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  text-align: center;
`;

export const JoinArea = styled.div`
  width: 100%;
  height: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 24px;
`;

export default function SelectJoinMethod() {
  const navigate = useNavigate();

  const handleEmailJoinClick = () => {
    // Email로 회원가입 버튼이 클릭되면 /signup/joinform 경로로 이동
    navigate("/signup/joinform");
  };

  return (
    <SelectJoinArea>
      <LogoArea>
        <JoinLogo>
          <img src={DancyImg}></img>
        </JoinLogo>
      </LogoArea>
      <TextArea>
        <WelcomeTitle>Dancy에 오신 것을 환영합니다!</WelcomeTitle>
        <JoinChoiceTitle>Dancy 회원가입 방식을 선택해주세요.</JoinChoiceTitle>
      </TextArea>
      <JoinArea>
        <JoinBtnContainer onClick={handleEmailJoinClick}>
          <EmailLogo>
            <img src={EmailImg}></img>
          </EmailLogo>
          <JoinMethodText>Email로 회원가입</JoinMethodText>
          <JoinMethodInfoText>이메일ID와 비밀번호로 Dancy 회원가입</JoinMethodInfoText>
        </JoinBtnContainer>
        <JoinBtnContainer>
          <GoogleLogo />
          <JoinMethodText>Google로 회원가입</JoinMethodText>
          <JoinMethodInfoText>구글로 인증하여 Dancy 회원가입</JoinMethodInfoText>
        </JoinBtnContainer>
        <JoinBtnContainer>
          <NaverLogo />
          <JoinMethodText>Naver로 회원가입</JoinMethodText>
          <JoinMethodInfoText>네이버로 인증하여 Dancy 회원가입</JoinMethodInfoText>
        </JoinBtnContainer>
        <JoinBtnContainer>
          <KakaoLogo />
          <JoinMethodText>Kakao로 회원가입</JoinMethodText>
          <JoinMethodInfoText>카카오로 인증하여 Dancy 회원가입</JoinMethodInfoText>
        </JoinBtnContainer>
      </JoinArea>
    </SelectJoinArea>
  );
}
