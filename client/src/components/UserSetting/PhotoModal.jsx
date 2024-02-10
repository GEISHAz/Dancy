import React, { useState, useRef } from "react";
import styled from "styled-components";
import AddImgLogo from "../../assets/UserSetting/AddImgIcon.png";
import { useRecoilState } from "recoil";
import { joinState, selectedFileState } from "../../recoil/JoinState";

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
  z-index: 2000;
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
  width: 450px;
  height: 300px;
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
  margin-top: 24px;
  margin-bottom: 20px;
`;

// 사진 첨부 공간(점선)
const ImportContainer = styled.div`
  border: 1.5px dashed black;
  border-radius: 15px;
  width: 320px;
  height: 180px;
  display: flex;
  background-color: white;
  justify-content: center;
  align-items: center;
  margin-bottom: 30px;
`;

const ImportLogo = styled.div`
  width: 150px;
  height: 140px;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
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
  width: 450px;
  height: 60px;
  color: white;
  font-family: "NYJ Gothic B";
  font-size: 20px;
`;

const PhotoModal = ({ isOpen, onClose, fileState, setFileState }) => {
  const [imgFile, setImgFile] = useState(null);
  const [selectedFile, setSelectedFile] = useRecoilState(selectedFileState);
  //const [joinData, setJoinData] = useRecoilState(joinState);

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

  // 사진 로고에 바꾼 사진을 보여주지 말고 더 크게 키워서 ...... 보여주게 변경 필요
  return (
    <ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <ModalContainer>
        <ModalContent>
          <ModalTitle>프로필 사진 변경</ModalTitle>
          <ImportContainer
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
            <ImportLogo>
              {imgFile ? (
                <img src={URL.createObjectURL(imgFile)} alt="Selected" />
              ) : (
                <img src={AddImgLogo} alt="Add" />
              )}
            </ImportLogo>
          </ImportContainer>
        </ModalContent>
        <ModalButtonContainer>
          <ModalButton onClick={handleUpload}>변경 완료</ModalButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

export default PhotoModal;
