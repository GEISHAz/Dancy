import { styled } from "styled-components";
import GoogleImg from "../../assets/join/googleSquare.png";
import NaverImg from "../../assets/join/naverSquare.png";
import KakaoImg from "../../assets/join/kakaoSquare.png";
import DancyImg from "../../assets/join/BigLogo.png";

// Dancy 로고
export const JoinLogo = styled.div`
  width: 200px;
  height: 168px;
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 100%;
    height: 100%;
  }
`;

// Dancy 제목
export const WelcomeTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic B";
  font-weight: 400;
  font-size: 28px;
  margin-top: 12px;
`;

// dancy 회원가입 선택
export const JoinChoiceTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic L";
  font-weight: 400;
  font-size: 16px;
`;

// 회원가입 버튼
// 버튼 테두리 컨테이너
export const JoinBtnContainer = styled.button`
  width: 500px;
  height: 60px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  margin: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 24px;
  padding-right: 24px;
  padding-top : 12px;
  padding-bottom : 12px;
`;
// 버튼 로고 -> 백그라운드에서 배치
export const LogoBox = styled.div`
  width: 40px;
  height: 40px;
  border: 0.5px solid;
  border-color: #cecece;
  border-radius: 5px;
  background-size: contain;
  background-position: center center;
  background-size: 100% 100%;
`;

export const EmailLogo = styled(LogoBox)`
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 60%;
    height: 60%;
  }
`;

export const GoogleLogo = styled(LogoBox)`
  background-image: url(${GoogleImg});
`;

export const NaverLogo = styled(LogoBox)`
  background-image: url(${NaverImg});
`;

// 일단 네이버로 넣어뒀다요.
export const KakaoLogo = styled(LogoBox)`
  background-image: url(${KakaoImg});
`;

// 회원가입 방법 text
export const JoinMethodText = styled.div`
  color: #252525;
  font-family: "NYJ Gothic L";
  font-weight: 400;
  font-size: 18px;
  line-height: normal;
`;

// 회원가입 방법 textinfo
export const JoinMethodInfoText = styled.div`
  color: #252525;
  font-family: "NYJ Gothic L";
  font-weight: 400;
  font-size: 12px;
  line-height: normal;
`;
