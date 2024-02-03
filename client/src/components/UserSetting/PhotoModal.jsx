import React, { useState, useRef, useEffect } from "react";
import styled from "styled-components";
import AddImgLogo from "../../assets/UserSetting/AddImgIcon.png";

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
  width: 590px;
  height: 455px;
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
  margin-bottom: 45px;
`;

// 사진 첨부 공간(점선)
const ImportContainer = styled.div`
  border: 1.5px dashed black;
  border-radius: 15px;
  width: 460px;
  height: 280px;
  display: flex;
  background-color: white;
  justify-content: center;
  align-items: center;
  margin-bottom: 45px;
`;

const ImportLogo = styled.div`
  width: 144px;
  height: 132px;
  object-fit: cover;
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
  width: 590px;
  height: 66px;
  color: white;
  font-family: "NYJ Gothic B";
  font-size: 20px;
`;

const PhotoModal = ({ isOpen, onClose }) => {
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
          <ModalTitle>프로필 사진 변경</ModalTitle>
          <ImportContainer>
            <ImportLogo>
              <img src={AddImgLogo}></img>
            </ImportLogo>
          </ImportContainer>
        </ModalContent>
        <ModalButtonContainer>
          <ModalButton>변경 완료</ModalButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

export default PhotoModal;
