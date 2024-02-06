import React, { useState, useRef, useEffect } from "react";
import styled from "styled-components";
import axios from "axios";
import { publicApi } from "../../util/http-commons";

const ModalOverlay = styled.div`
  display: ${(props) => (props.isOpen ? "flex" : "none")};
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  justify-content: center;
  align-items: center;
  z-index: 100;
`;

// 모달 전체의 정렬 설정
const ModalContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

// 모달 전체
const ModalContent = styled.div`
  background-color: #fffdfb;
  border: 1px solid black;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  width: 595px;
  height: 458px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

// 모달 제목
const ModalTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 28px;
  color: black;
  margin-top: 43px;
  margin-bottom: 30px;
`;

// 텍스트 엔터 처리
export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  align-self: flex-start;
  margin-left: 36px;
  margin-bottom: 33px;
  margin-top: 39px;
`;

const ChangePwdTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 18px;
  color: black;
  margin-left: 74px;
  align-self: flex-start;
`;

// 인풋 커스텀
const ChangeInput = styled.input`
  width: 448px;
  height: 46px;
  border: 1px solid black;
  border-radius: 3px;
  margin-bottom: 16px;
  padding-left: 10px;


  &:focus {
    outline: 2px solid #e23e59;
    border: none;
  }
`;

const ModalButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

const ModalButton = styled.button`
  border: 1px solid black;
  border-top: none;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  background-color: #f9405e;
  width: 595px;
  height: 66px;
  color: white;
  font-family: "NYJ Gothic B";
  font-size: 20px;
`;

const InfoText = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 16px;
  color: #6c6c6c;
  align-self: flex-start;
  margin-left: 74px;
  margin-bottom: 33px;
  margin-top: 24px;
`;

const NewPwdModal = ({ isOpen, onClose }) => {
  const newPassword = () => {
    publicApi.post('/auth/password/')
  }
  
  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };



  return (
    <ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <ModalContainer>
        <ModalContent>
          <ModalTitle>비밀번호 변경</ModalTitle>
          <ChangePwdTitle>새 비밀번호</ChangePwdTitle>
          <ChangeInput />
          <ChangePwdTitle>비밀번호 확인</ChangePwdTitle>
          <ChangeInput />
          <InfoText>영문자, 숫자, 특수문자를 조합하여 입력해주세요. (8자 이상)</InfoText>
        </ModalContent>
        <ModalButtonContainer>
          <ModalButton>변경 완료</ModalButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

export default NewPwdModal;
