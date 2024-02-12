import React, { useState, useRef, useEffect } from "react";
import { useSetRecoilState } from "recoil";
import * as Q from "./QuitModal.style"
import { deleteUser } from "../../api/delete"
import { loginState } from '../../recoil/LoginState';
import { useNavigate } from "react-router-dom"


const QuitModal = ({ isOpen, onClose }) => {
  const navigate = useNavigate();
  const [password, setPassword] = useState('');
  const setLoginState = useSetRecoilState(loginState);

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  }
  
  const handleDeleteUser = async () => {
    try {
      await deleteUser(password, setLoginState);
      onClose();
      navigate("/");
    } catch (error) {
      const errorType = error.response.status;
      if (errorType === 401) {
        alert("요청 시간이 초과했습니다. 잠시 후 다시 시도해주세요.")
      } else if (errorType === 403) {
        alert("기존 비밀번호가 일치하지 않습니다.")
      }
      console.error(error)
    }
  }

  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };



  return (
    <Q.ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <Q.ModalContainer>
        <Q.ModalContent>
          <Q.EnterArea>
            <Q.ModalTitle>정말 탈퇴하시겠습니까?</Q.ModalTitle>
            <Q.ModalTitle>회원 탈퇴를 위해 비밀번호를 입력해주십시오.</Q.ModalTitle>
          </Q.EnterArea>
          <Q.QuitTitle>비밀번호</Q.QuitTitle>
          <Q.QuitInput type="password" onChange={handlePasswordChange} />
        </Q.ModalContent>
        <Q.ModalButtonContainer>
          <Q.ModalButton onClick={handleDeleteUser} >탈퇴하기</Q.ModalButton>
        </Q.ModalButtonContainer>
      </Q.ModalContainer>
    </Q.ModalOverlay>
  );
};

export default QuitModal;
