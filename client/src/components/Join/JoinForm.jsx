import DancyImg from "../../assets/join/BigLogo.png";
import { useRecoilValue } from "recoil";
import { joinState } from "../../recoil/JoinState";
import { useState } from "react";
import * as JF from "./JoinForm.style";
import Form from "./Form";
import FormHeader from "./FormHeader";
import { BrowserRouter as Router, Route, Link, useNavigate } from "react-router-dom";
import { signUp } from "../../api/join";
import { httpStatusCode } from "../../util/http-status";
import { selectedFileState } from "../../recoil/JoinState";

export default function JoinForm() {
  const joinData = useRecoilValue(joinState);
  console.log(joinData);

  const [formData, setFormData] = useState(new FormData());
  // 사진 파일 가져오기
  const selectedFile = useRecoilValue(selectedFileState);
  const navigate = useNavigate();

  const handleSubmit = async () => {
    // FormData 객체에 데이터 추가
    formData.set("email", joinData.email);
    formData.set("password", joinData.password);
    formData.set("birthDate", joinData.birthdate); // Date를 ISO 문자열로 변환하여 추가
    formData.set("gender", joinData.gender);
    formData.set("nickname", joinData.nickname);
    formData.set("authType", `DANCY`);
    if (selectedFile !== null) {
      formData.set("profileImage", selectedFile);
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
    <JF.JoinArea>
      <JF.AlignArea>
        <JF.LogoArea>
          <JF.CenterContainer>
            <JF.JoinLogo>
              <img src={DancyImg}></img>
            </JF.JoinLogo>
            <JF.FormTitle>회원정보를</JF.FormTitle>
            <JF.FormTitle>입력해주세요.</JF.FormTitle>
          </JF.CenterContainer>
        </JF.LogoArea>
        <JF.ContextArea>
          <JF.CenterContainer>
            <FormHeader />
            <Form />
          </JF.CenterContainer>
        </JF.ContextArea>
      </JF.AlignArea>
      <JF.SubmitArea>
        <JF.RegisterBtn onClick={handleSubmit}>가입하기</JF.RegisterBtn>
      </JF.SubmitArea>
    </JF.JoinArea>
  );
}
