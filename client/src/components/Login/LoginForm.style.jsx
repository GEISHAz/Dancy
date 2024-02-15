import { styled } from "styled-components";
import GoogleImg from "../../assets/socialLogin/socialGoogle.png";
import KakaoImg from "../../assets/socialLogin/socialKakao.png";
import NaverImg from "../../assets/socialLogin/socialNaver.png";
import DancyImg from "../../assets/DancyLogo.png";

export const Container = styled.div`
  height: 900px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const DancyLogo = styled.div`
  width: 350px;
  height: 120px;
  background-image: url(${DancyImg});
  background-size: contain;
  align-self: center;
`;

export const SocialLoginLogo = styled.div`
  border-radius: 50%;
  width: 35px;
  height: 35px;
  background-size: contain;
`;

export const SocialGoogle = styled(SocialLoginLogo)`
  background-image: url(${GoogleImg});
`;
export const SocialKakao = styled(SocialLoginLogo)`
  background-image: url(${KakaoImg});
`;
export const SocialNaver = styled(SocialLoginLogo)`
  background-image: url(${NaverImg});
`;

export const InputTitle = styled.div`
  color: #000;
  font-family: "NYJ Gothic";
  font-size: 20px;
  font-style: normal;
  font-weight: bold;
  line-height: normal;
`;

export const InputBox = styled.input`
  border: 1px solid black;
  border-radius: 5px;
  width: 412px;
  height: 46px;
  padding: 10px;
  font-family: "NYJ Gothic";
  font-weight: normal;
  font-size: 16px;

  &:focus {
    outline: 2px solid #e23e59;
    border: none;
  }
`;

export const ErrorEmail = styled.div`
  height: 0;
  color: #c00000;
  font-family: "NYJ Gothic";
  font-weight: normal;
  font-size: 12px;
  text-align: end;
`;

export const AutoLoginChkBox = styled.input.attrs({
  type: "checkbox",
})`
  accent-color: #ff919a;
  transform: scale(1.2);
`;

export const AutoLogin = styled.label`
  color: #000;
  font-family: "NYJ Gothic";
  font-size: 16px;
  font-style: normal;
  font-weight: bold;
  line-height: normal;
`;

export const LoginButton = styled.button`
  margin-top: 10px;
  background-color: #e23e59;
  height: 43px;
  border-radius: 5px;
  color: #ffffff;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 20px;

  &:hover {
    background-color: #c0354c;
  }
`;

export const ExplainJoinFindPw = styled.div`
  color: #6c6c6c;
  font-family: "NYJ Gothic";
  font-weight: 300;
  font-size: 14px;
`;

export const GoJoinFindPw = styled.div`
  color: #6c6c6c;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 16px;

  &:hover {
    color: #e23e59;
  }
`;

// Modal
export const ModalBackdrop = styled.div`
  background-color: rgba(0, 0, 0, 0.4);
  width: 100vw;
  height: 100vh;
  /*스크롤이 되도 모달창이 고정 되도록 position:fixed*/
  position: fixed;
  bottom: 0;
  /* 자식 컴포넌트인 모달창을 가운데 오게 하기 위해 flex설정*/
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 5000;
`;

export const ModalView = styled.div.attrs((props) => ({ role: "dialog" }))`
  display: flex;
  flex-direction: column;
  align-content: space-between;
  position: fixed;
  width: 420px;
  height: 200px;
  padding: 40px;
  padding-bottom: 28px;
  padding-right: 32px;
  border-radius: 1rem;
  background-color: #ffffff;
`;

export const ModalTxt = styled.div`
  font-family: "NYJ Gothic";
  font-weight: 300;
  font-size: 16px;
`;

export const ModalBtn = styled.button`
  background-color: #e23e59;
  width: 71px;
  height: 33px;
  border: 1px solid black;
  border-radius: 50px;
  margin-top: 70px;
  align-self: flex-end;
  text-align: center;
  color: #ffffff;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;

  &:hover {
    background-color: #c0354c;
  }
`;

export const FeedContainer = styled.div``;
