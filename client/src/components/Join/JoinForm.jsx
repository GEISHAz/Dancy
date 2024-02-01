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
  flex-direction: column;
`;

export const FormHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  align-self: flex-start;
  gap: 40px;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
`;

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  align-self: flex-start;
`;

export const NoticeArea = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
`;

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
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
  padding: 3.5rem;
`;

export default function JoinForm() {
  return (
    <JoinArea>
      <AlignArea>
        <FormHeader>
          <JF.JoinLogo>
            <img src={DancyImg}></img>
          </JF.JoinLogo>
          <JF.ProfileLogo>
            <img src={DefaultImg}></img>
          </JF.ProfileLogo>
          <EnterArea>
            <JF.PhotoNotice>프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.</JF.PhotoNotice>
            <JF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</JF.PhotoNotice>
          </EnterArea>
          <JF.FormBtn>사진 변경</JF.FormBtn>
        </FormHeader>
        <JoinFormArea>
          <NoticeArea>
            <JF.MustNoticeText>(&nbsp;</JF.MustNoticeText>
            <JF.MustIcon />
            <JF.MustNoticeText>&nbsp;)는 필수 입력 값입니다.</JF.MustNoticeText>
          </NoticeArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>E-mail</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
            <JF.FormBtn>인증하기</JF.FormBtn>
          </FormDetailArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>비밀번호</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
            <EnterArea>
              <JF.MustNoticeText>영문자, 숫자, 특수문자를 조합하여</JF.MustNoticeText>
              <JF.MustNoticeText>입력해주세요. (8자 이상)</JF.MustNoticeText>
            </EnterArea>
          </FormDetailArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>비밀번호 확인</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
          </FormDetailArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>생년월일</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
          </FormDetailArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>성별</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
            <JF.FormBtn>인증하기</JF.FormBtn>
          </FormDetailArea>
          <FormDetailArea>
            <JF.MustIcon />
            <JF.FormCategory>닉네임</JF.FormCategory>
            <JF.FormInput></JF.FormInput>
            <JF.FormBtn>중복 확인</JF.FormBtn>
          </FormDetailArea>
        </JoinFormArea>
      </AlignArea>

      <SubmitArea>
        <JF.RegisterBtn>가입하기</JF.RegisterBtn>
      </SubmitArea>
    </JoinArea>
  );
}
