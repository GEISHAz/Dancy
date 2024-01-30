import { styled } from "styled-components";
import GoogleImg from "../../assets/join/googleSquare.png";
import NaverImg from "../../assets/join/naverSquare.png";
import KakaoImg from "../../assets/join/kakaoSquare.png";
import DancyImg from "../../assets/join/BigLogo.png";

// Dancy 로고
export const JoinLogo = styled.div`
  width: 180px;
  height: 150.28px;
  background-image: url(${DancyImg});
  background-size: contain;
`;

// Dancy 제목
export const WelcomeTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic B-Regular";
  font-weight: 400;
  font-size: 32px;
  margin-top: 36px;
`;

// dancy 회원가입 선택
export const JoinChoiceTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic L-Regular";
  font-weight: 400;
  font-size: 24px;
`;

// 회원가입 버튼
// 버튼 테두리 컨테이너
export const JoinBtnContainer = styled.button`
  width: 724px;
  height: 98px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  margin: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 24px;
  padding-right: 24px;
`;
// 버튼 로고 -> 백그라운드에서 배치
export const LogoBox = styled.div`
  width: 46px;
  height: 46px;
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
  font-family: "NYJ Gothic L-Regular";
  font-weight: 400;
  font-size: 28px;
  line-height: normal;
`;

// 회원가입 방법 textinfo
export const JoinMethodInfoText = styled.div`
  color: #252525;
  font-family: "NYJ Gothic L-Regular";
  font-weight: 400;
  font-size: 20px;
  line-height: normal;
`;
