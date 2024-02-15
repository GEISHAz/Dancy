import React, { useState, useRef, useEffect } from "react";
import * as M from "./NewPwdModal.style"
import axios from "axios";
import { useNavigate } from "react-router-dom"
import { privateApi } from "../../util/http-commons";


const NewPwdModal = ({ isOpen, onClose }) => {
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const navigate = useNavigate();

  const setPassword = () => {
    privateApi.post('/auth/password/find', { "newPassword" : newPassword })
    .then(response => {
      alert("비밀번호가 성공적으로 변경되었습니다.");
      navigate('/login');
    })
    .catch(error => {
      const errorType = error.response?.data[0]?.errorType;
      console.log(errorType)
      if (errorType === 'Password') {
        alert("비밀번호는 영문, 숫자, 특수문자 포함 8자리 이상이어야 합니다.")
      } else if (errorType === 'TokenInvalidException') {
        alert("응답시간이 지났습니다. 잠시 후 다시 시도해주세요.");
        navigate('/login');
      }
      console.log(error.response);
    })
  }
  
  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };



  return (
    <M.ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <M.ModalContainer>
        <M.ModalContent>
          <M.ModalTitle>비밀번호 변경</M.ModalTitle>
          <M.ChangePwdTitle>새 비밀번호</M.ChangePwdTitle>
          <M.ChangeInput type="password" onChange={e => setNewPassword(e.target.value)} />
          <M.ChangePwdTitle>비밀번호 확인</M.ChangePwdTitle>
          <M.ChangeInput type="password" onChange={e => confirmPassword(e.target.value)} />
          <M.InfoText>영문자, 숫자, 특수문자를 조합하여 입력해주세요. (8자 이상)</M.InfoText>
        </M.ModalContent>
        <M.ModalButtonContainer>
          <M.ModalButton onClick={setPassword}>변경 완료</M.ModalButton>
        </M.ModalButtonContainer>
      </M.ModalContainer>
    </M.ModalOverlay>
  );
};

export default NewPwdModal;
