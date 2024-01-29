import { styled } from "styled-components";
import GoogleImg from '../assets/socialLogin/socialGoogle.png'
import KakaoImg from '../assets/socialLogin/socialKakao.png'
import NaverImg from '../assets/socialLogin/socialNaver.png'
import DancyImg from '../assets/DancyLogo.png'

export const Container = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
  padding-top: 3.5rem;
  padding-bottom: 3.5rem;
`

export const DancyLogo = styled.div`
  width: 350px;
  height: 120px;
  background-image: url(${DancyImg});
  background-size: contain;
  align-self: center;
`

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
  font-family: "NYJ Gothic B";
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
`;

export const InputBox = styled.input`
  border: 1px solid black;
  border-radius: 5px;
  width: 412px;
  height: 46px;
  padding: 10px;
  font-family: "NYJ Gothic L";
  font-size: 16px;

  &:focus {
    outline: 2px solid #E23E59;
    border: none;
  }
`;

export const AutoLogin = styled(InputTitle)`
  font-size: 14px;
`

export const LoginButton = styled.button`
  background-color: #E23E59;
  height: 43px;
  border-radius: 5px;
  color: #FFFFFF;
  font-family: "NYJ Gothic B";
  font-size: 20px;

  &:hover {
    background-color: #C0354C;
  }
`

export const ExplainJoinFindPw = styled.div`
  color: #6C6C6C;
  font-family: "NYJ Gothic L";
  font-size: 12px;
`

export const GoJoinFindPw = styled.div`
  color: #6C6C6C;
  font-family: "NYJ Gothic B";
  font-size: 16px;

  &:hover {
    color: #E23E59;
  }
`