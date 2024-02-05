import React, { useState } from "react";
import { styled } from "styled-components";
import * as JF from "./JoinForm.style";
import CustomModal from "./PinModal";

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 16px;
`;

// 필수 항목 공지
export const NoticeArea = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  margin-bottom: 12px;
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

// 텍스트 엔터 처리
export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const InputContainer = styled.div`
  position: relative;
`;

// 라디오 버튼 커스텀
export const RadioContainer = styled.div`
  display: flex;
  font-family: "NYJ Gothic B";
  font-size: 16px;
  gap: 12px;

  input {
    accent-color: #f9405e;
  }
`;

export default function FormArea() {
  const [inputValue, setInputValue] = useState("");
  const [showWarning, setShowWarning] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달의 열림/닫힘 상태를 관리
  const [submittedPin, setSubmittedPin] = useState(""); // 모달에서 제출된 PIN을 저장

  // 모달을 열기 위한 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달을 닫기 위한 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  // 모달에서 PIN이 제출됐을 때 실행되는 함수
  const handlePinSubmit = (pin) => {
    console.log(`Submitted PIN: ${pin}`);
    setSubmittedPin(pin); // 제출된 PIN을 상태값에 저장
    closeModal(); // PIN이 제출되면 모달을 닫음
  };

  const inputChangeHandler = (e) => {
    const value = e.target.value;
    setInputValue(value);

    // 유효성 검사 등을 수행하여 유효하지 않은 경우에만 경고를 보이도록 설정
    setShowWarning(value.trim() === "");
  };

  return (
    <JoinFormArea>
      <NoticeArea>
        <JF.MustNoticeText>(&nbsp;</JF.MustNoticeText>
        <JF.MustIcon />
        <JF.MustNoticeText>&nbsp;)는 필수 입력 값입니다.</JF.MustNoticeText>
      </NoticeArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="76px">E-mail</JF.FormCategory>
        <JF.FormInput type="email"></JF.FormInput>
        <JF.FormBtn onClick={openModal}>인증하기</JF.FormBtn>
        {/* CustomModal 컴포넌트를 렌더링하고 isOpen, onClose, onSubmit을 props로 전달 */}
        <CustomModal isOpen={isModalOpen} onClose={closeModal} onSubmit={handlePinSubmit} />
        {/* PIN이 제출되면 해당 내용을 출력 */}
        {submittedPin && <p>인증번호: {submittedPin}</p>}
      </FormDetailArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="62px">비밀번호</JF.FormCategory>
        <InputContainer>
          <JF.FormInput
            type="password"
            value={inputValue}
            onChange={inputChangeHandler}
          ></JF.FormInput>
          <JF.InputNoticeText show={showWarning}>
            형식을 만족하지 않는 비밀번호입니다.
          </JF.InputNoticeText>
        </InputContainer>
        <EnterArea>
          <JF.MustNoticeText>영문자, 숫자, 특수문자를 조합하여</JF.MustNoticeText>
          <JF.MustNoticeText>입력해주세요. (8자 이상)</JF.MustNoticeText>
        </EnterArea>
      </FormDetailArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="19px">비밀번호 확인</JF.FormCategory>
        <InputContainer>
          <JF.FormInput type="password"></JF.FormInput>
        </InputContainer>
      </FormDetailArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="62px">생년월일</JF.FormCategory>
        <JF.FormInput type="date"></JF.FormInput>
      </FormDetailArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="99px">성별</JF.FormCategory>
        <RadioContainer>
          <input type="radio" name="gender" value="male" /> 남성
          <input type="radio" name="gender" value="female" /> 여성
        </RadioContainer>
      </FormDetailArea>
      <FormDetailArea>
        <JF.MustIcon />
        <JF.FormCategory margin="80px">닉네임</JF.FormCategory>
        <JF.FormInput></JF.FormInput>
        <JF.FormBtn>중복 확인</JF.FormBtn>
      </FormDetailArea>
    </JoinFormArea>
  );
}
