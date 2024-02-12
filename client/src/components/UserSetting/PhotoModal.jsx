import React, { useState, useRef } from "react";
import AddImgLogo from "../../assets/UserSetting/AddImgIcon.png";
import { useRecoilState } from "recoil";
import { selectedFileState } from "../../recoil/JoinState";
import * as PM from "./PhotoModal.style";

const PhotoModal = ({ isOpen, onClose, fileState, setFileState }) => {
  const [imgFile, setImgFile] = useState(null);
  const [selectedFile, setSelectedFile] = useRecoilState(selectedFileState);

  const fileInputRef = useRef();

  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const handleFileChange = (e) => {
    // 이미 여기서 파일이 변경될때마다 저장해줍니다...
    const file = e.target.files[0];
    setImgFile(file);
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const file = e.dataTransfer.files[0];
    setImgFile(file);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleUpload = (e) => {
    // 선택된 파일 처리 로직 작성
    console.log("Selected File:", imgFile);
    // 여기에 선택된 파일을 업로드 -> 여기는 임시용 공간임.. 나중에 완전히 넣을때 넣어줘요
    setSelectedFile(imgFile);

    // 모달 닫기
    onClose();
  };

  // 사진 로고에 바꾼 사진을 보여주지 말고 더 크게 키워서 변경했다.
  return (
    <PM.ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <PM.ModalContainer>
        <PM.ModalContent>
          <PM.ModalTitle>프로필 사진 변경</PM.ModalTitle>
          <PM.ImportContainer
            onDrop={handleDrop}
            onDragOver={handleDragOver}
            onClick={() => fileInputRef.current.click()}
          >
            <input
              type="file"
              accept="image/*"
              ref={fileInputRef}
              style={{ display: "none" }}
              onChange={handleFileChange}
            />
            <PM.ImportLogo>
              {imgFile ? (
                <img src={URL.createObjectURL(imgFile)} alt="Selected" />
              ) : (
                <img src={AddImgLogo} alt="Add" />
              )}
            </PM.ImportLogo>
          </PM.ImportContainer>
        </PM.ModalContent>
        <PM.ModalButtonContainer>
          <PM.ModalButton onClick={handleUpload}>변경 완료</PM.ModalButton>
        </PM.ModalButtonContainer>
      </PM.ModalContainer>
    </PM.ModalOverlay>
  );
};

export default PhotoModal;
