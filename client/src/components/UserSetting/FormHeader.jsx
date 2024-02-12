import React, { useState } from "react";
import * as SF from "./SettingForm.style";
import * as FH from "./FormHeader.style";
import DefaultImg from "../../assets/join/picture.png";
import PhotoModal from "./PhotoModal";
import { useRecoilState } from "recoil";
import { userState } from "../../recoil/LoginState";

export default function FormHeader() {
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달의 열림/닫힘 상태를 관리
  // 유저 정보 변경 시 파일 상태를 관리하는 Recoil 상태
  const [userData, setUserData] = useRecoilState(userState);

  // 모달을 열기 위한 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달을 닫기 위한 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <FH.Header>
      <SF.ProfileLogo margin="35px">
        <img src={DefaultImg}></img>
      </SF.ProfileLogo>
      <FH.EnterArea margin="114px">
        <SF.PhotoNotice>프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.</SF.PhotoNotice>
        <SF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</SF.PhotoNotice>
      </FH.EnterArea>
      <SF.FormBtn onClick={openModal}>사진 변경</SF.FormBtn>
      {/* PhotoModal 컴포넌트를 렌더링하고 isOpen, onClose을 props로 전달 */}
      <PhotoModal
        isOpen={isModalOpen}
        onClose={closeModal}
        fileState={userData}
        setFileState={setUserData}
      />
    </FH.Header>
  );
}
