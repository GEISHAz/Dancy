import { useNavigate } from "react-router-dom";
import * as SJM from "./SelectJoinMethod.style";
import EmailImg from "../../assets/join/email.png";
import DancyImg from "../../assets/join/BigLogo.png";

export default function SelectJoinMethod() {
  const navigate = useNavigate();

  const handleEmailJoinClick = () => {
    // Email로 회원가입 버튼이 클릭되면 /signup/joinform 경로로 이동
    navigate("/signup/joinform");
  };

  return (
    <SJM.SelectJoinArea>
      <SJM.LogoArea>
        <SJM.JoinLogo>
          <img src={DancyImg}></img>
        </SJM.JoinLogo>
      </SJM.LogoArea>
      <SJM.TextArea>
        <SJM.WelcomeTitle>Dancy에 오신 것을 환영합니다!</SJM.WelcomeTitle>
        <SJM.JoinChoiceTitle>Dancy 회원가입 방식을 선택해주세요.</SJM.JoinChoiceTitle>
      </SJM.TextArea>
      <SJM.JoinArea>
        <SJM.JoinBtnContainer onClick={handleEmailJoinClick}>
          <SJM.EmailLogo>
            <img src={EmailImg}></img>
          </SJM.EmailLogo>
          <SJM.JoinMethodText>Email로 회원가입</SJM.JoinMethodText>
          <SJM.JoinMethodInfoText>이메일ID와 비밀번호로 Dancy 회원가입</SJM.JoinMethodInfoText>
        </SJM.JoinBtnContainer>
        <SJM.JoinBtnContainer>
          <SJM.GoogleLogo />
          <SJM.JoinMethodText>Google로 회원가입</SJM.JoinMethodText>
          <SJM.JoinMethodInfoText>구글로 인증하여 Dancy 회원가입</SJM.JoinMethodInfoText>
        </SJM.JoinBtnContainer>
        <SJM.JoinBtnContainer>
          <SJM.NaverLogo />
          <SJM.JoinMethodText>Naver로 회원가입</SJM.JoinMethodText>
          <SJM.JoinMethodInfoText>네이버로 인증하여 Dancy 회원가입</SJM.JoinMethodInfoText>
        </SJM.JoinBtnContainer>
        <SJM.JoinBtnContainer>
          <SJM.KakaoLogo />
          <SJM.JoinMethodText>Kakao로 회원가입</SJM.JoinMethodText>
          <SJM.JoinMethodInfoText>카카오로 인증하여 Dancy 회원가입</SJM.JoinMethodInfoText>
        </SJM.JoinBtnContainer>
      </SJM.JoinArea>
    </SJM.SelectJoinArea>
  );
}
