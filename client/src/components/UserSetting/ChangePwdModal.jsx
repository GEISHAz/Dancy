import React, { useState } from "react";
import { authtokenApi } from "../../util/http-commons";
import { logout } from "../../api/auth.js";
import { loginState } from "../../recoil/LoginState.js";
import { useRecoilState } from "recoil";
import * as CPM from "./ChangePwdModal.style.jsx";

const ChangePwdModal = ({ isOpen, onClose }) => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [isLoggedIn, setLoginState] = useRecoilState(loginState);

  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const changePassword = () => {
    authtokenApi
      .put("/auth/change", { currentPassword: currentPassword, newPassword: newPassword })
      .then(() => {
        alert("비밀번호가 성공적으로 변경되었습니다");
        onClose();
        logout(setLoginState);
      })

      .then(() => {
        navigate("/login");
      })

      .catch((error) => {
        const errorType = error.response.status;

        if (errorType === 400) {
          alert("비밀번호는 영문, 숫자, 특수문자 포함 8자리 이상이어야 합니다.");
        } else if (errorType === 401) {
          alert("요청 시간이 초과되었습니다. 잠시 후 다시 시도해주세요.");
        } else if (errorType === 403) {
          alert("기존 비밀번호가 일치하지 않습니다.");
        }
      });
  };

  const handleCurrentPassword = (e) => {
    setCurrentPassword(e.target.value);
  };

  const handleNewPassword = (e) => {
    setNewPassword(e.target.value);
  };

  return (
    <CPM.ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <CPM.ModalContainer>
        <CPM.ModalContent>
          <CPM.ModalTitle>비밀번호 변경</CPM.ModalTitle>
          <CPM.ChangePwdTitle>현재 비밀번호</CPM.ChangePwdTitle>
          <CPM.ChangeInput type="password" onChange={handleCurrentPassword} />
          <CPM.ChangePwdTitle>새 비밀번호</CPM.ChangePwdTitle>
          <CPM.ChangeInput type="password" onChange={handleNewPassword} />
          <CPM.ChangePwdTitle>비밀번호 확인</CPM.ChangePwdTitle>
          <CPM.ChangeInput type="password" onChange={handleNewPassword} />
          <CPM.InfoText>영문자, 숫자, 특수문자를 조합하여 입력해주세요. (8자 이상)</CPM.InfoText>
        </CPM.ModalContent>
        <CPM.ModalButtonContainer>
          <CPM.ModalButton onClick={changePassword}>변경 완료</CPM.ModalButton>
        </CPM.ModalButtonContainer>
      </CPM.ModalContainer>
    </CPM.ModalOverlay>
  );
};

export default ChangePwdModal;
