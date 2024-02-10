import { styled } from "styled-components";
import DancyImg from "../../assets/join/BigLogo.png";
import DefaultImg from "../../assets/join/picture.png";
import { useRecoilValue } from "recoil";
import { joinState } from "../../recoil/JoinState";
import { useState } from "react";
import * as JF from "./JoinForm.style";
import Form from "./Form";
import FormHeader from "./FormHeader";
import { BrowserRouter as Router, Route, Link, useNavigate } from "react-router-dom";
import { signUp } from "../../api/join";
import { httpStatusCode } from "../../util/http-status";

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

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

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

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const SubmitArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 4rem;
  margin-bottom: 4rem;
`;

export default function JoinForm() {
  const joinData = useRecoilValue(joinState);
  console.log(joinData);

  const [formData, setFormData] = useState(new FormData());
  const navigate = useNavigate();

  const handleSubmit = async () => {
    // FormData 객체에 데이터 추가
    formData.set("email", joinData.email);
    formData.set("password", joinData.password);
    formData.set("birthDate", joinData.birthdate); // Date를 ISO 문자열로 변환하여 추가
    formData.set("gender", joinData.gender);
    formData.set("nickname", joinData.nickname);
    formData.set("authType", `DANCY`);
    if (joinData.profileImageUrl) {
      formData.set("profileImage", joinData.profileImageUrl);
    }

    for (const pair of formData.entries()) {
      console.log(pair[0], pair[1]);
    }

    try {
      // 서버로 FormData 객체 전송
      const response = await signUp(formData);
      console.log("response", response);

      if (response === httpStatusCode.OK) {
        navigate("/signup/joincomplete"); //완료 페이지로 넘어가도록
        return;
      }
      // 처리 결과 확인 등의 작업 수행
    } catch (error) {
      console.log("error", error);
      alert("회원가입을 진행할 수 없습니다.");
      if (error === httpStatusCode.CONFLICT) {
        return;
      }
    }
  };

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
            <FormHeader />
            <Form />
          </CenterContainer>
        </ContextArea>
      </AlignArea>
      <SubmitArea>
        <JF.RegisterBtn onClick={handleSubmit}>가입하기</JF.RegisterBtn>
      </SubmitArea>
    </JoinArea>
  );
}
