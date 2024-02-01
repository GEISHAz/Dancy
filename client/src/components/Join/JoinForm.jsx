import { styled } from "styled-components";
import DancyImg from "../../assets/join/BigLogo.png";
import DefaultImg from "../../assets/join/picture.png";
import * as JF from "./JoinForm.style";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export const JoinArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  flex-direction: column;
`;

export const AlignArea = styled.div`
  display: flex;
  margin-top: 60px;
  width: 100%;
`;

export const FormHeader = styled.div`
  display: flex;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
  text-align: center;
  justify-content: center;
  align-items: center;
`;

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

// 2
export const LogoArea = styled.div`
  flex: 2;
  display: flex;
  margin-left: 20px;
  justify-content: end;
`;

export const ContextArea = styled.div`
  flex: 8;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const CenterContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: start;
`;

export const NoticeArea = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  margin-bottom: 12px;
`;

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

// 폼 매 줄 마다 설정
export const FormDetailArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
  margin-top: 1rem;
  gap: 20px;
`;

export const SubmitArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top : 4rem;
  margin-bottom: 4rem;
`;

export default function JoinForm() {
  return (
    <JoinArea>
      <AlignArea>
        <LogoArea>
          <CenterContainer>
            <JF.JoinLogo>
              <img src={DancyImg}></img>
            </JF.JoinLogo>
            <JF.FormTitle>회원정보를</JF.FormTitle>
            <JF.FormTitle>입력해주세요.</JF.FormTitle>
          </CenterContainer>
        </LogoArea>
        <ContextArea>
          <CenterContainer>
            <FormHeader>
              <JF.ProfileLogo margin="35px">
                <img src={DefaultImg}></img>
              </JF.ProfileLogo>
              <EnterArea margin="119px">
                <JF.PhotoNotice>
                  프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.
                </JF.PhotoNotice>
                <JF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</JF.PhotoNotice>
              </EnterArea>
              <JF.FormBtn>사진 변경</JF.FormBtn>
            </FormHeader>
            <JoinFormArea>
              <NoticeArea>
                <JF.MustNoticeText>(&nbsp;</JF.MustNoticeText>
                <JF.MustIcon />
                <JF.MustNoticeText>
                  &nbsp;)는 필수 입력 값입니다.
                </JF.MustNoticeText>
              </NoticeArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="76px">E-mail</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
                <JF.FormBtn>인증하기</JF.FormBtn>
              </FormDetailArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="62px">비밀번호</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
                <EnterArea>
                  <JF.MustNoticeText>
                    영문자, 숫자, 특수문자를 조합하여
                  </JF.MustNoticeText>
                  <JF.MustNoticeText>
                    입력해주세요. (8자 이상)
                  </JF.MustNoticeText>
                </EnterArea>
              </FormDetailArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="19px">비밀번호 확인</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
              </FormDetailArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="62px">생년월일</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
              </FormDetailArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="99px">성별</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
              </FormDetailArea>
              <FormDetailArea>
                <JF.MustIcon />
                <JF.FormCategory margin="80px">닉네임</JF.FormCategory>
                <JF.FormInput></JF.FormInput>
                <JF.FormBtn>중복 확인</JF.FormBtn>
              </FormDetailArea>
            </JoinFormArea>
          </CenterContainer>
        </ContextArea>
      </AlignArea>
      <SubmitArea>
        <JF.RegisterBtn>가입하기</JF.RegisterBtn>
      </SubmitArea>
    </JoinArea>
  );
}
