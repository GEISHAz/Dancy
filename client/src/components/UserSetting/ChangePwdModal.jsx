import React, { useState, useRef, useEffect } from "react";
import styled from "styled-components";
import { changePasswordApi } from "../../util/http-commons";
import { logout } from "../../api/auth.js";
import axios from 'axios';
import { loginState } from "../../recoil/LoginState.js";
import { useRecoilState } from "recoil";

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

const ChangePwdModal = ({ isOpen, onClose }) => {  
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [isLoggedIn, setLoginState] = useRecoilState(loginState);

  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const changePassword = () => {
    changePasswordApi.put('/auth/change', {"currentPassword" : currentPassword, "newPassword": newPassword})
    .then(() => {
      alert("비밀번호가 성공적으로 변경되었습니다");
      onClose();
      logout(setLoginState);
    })

    .then(() => {
      navigate('/login');
    })

    .catch(error => {
      const errorType = error.response.status;

      if (errorType === 400) {
        alert("비밀번호는 영문, 숫자, 특수문자 포함 8자리 이상이어야 합니다.")
      } else if (errorType === 401) {
        alert("요청 시간이 초과되었습니다. 잠시 후 다시 시도해주세요.")
      } else if (errorType === 403) {
        alert("기존 비밀번호가 일치하지 않습니다.")
      }
    })
  }

  const handleCurrentPassword = (e) => {
    setCurrentPassword(e.target.value);
  }

  const handleNewPassword = (e) => {
    setNewPassword(e.target.value)
  }

  return (
    <ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <ModalContainer>
        <ModalContent>
          <ModalTitle>비밀번호 변경</ModalTitle>
          <ChangePwdTitle>현재 비밀번호</ChangePwdTitle>
          <ChangeInput type='password' onChange={handleCurrentPassword} />
          <ChangePwdTitle>새 비밀번호</ChangePwdTitle>
          <ChangeInput type='password' onChange={handleNewPassword} />
          <ChangePwdTitle>비밀번호 확인</ChangePwdTitle>
          <ChangeInput type='password' onChange={handleNewPassword} />
          <InfoText>영문자, 숫자, 특수문자를 조합하여 입력해주세요. (8자 이상)</InfoText>
        </ModalContent>
        <ModalButtonContainer>
          <ModalButton onClick={changePassword}>변경 완료</ModalButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

export default ChangePwdModal;
