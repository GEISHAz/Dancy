import React, { useState } from "react";
import * as JF from "./JoinForm.style";
import * as FH from "./FormHeader.style";
import DefaultImg from "../../assets/join/picture.png";
import PhotoModal from "../UserSetting/PhotoModal";
import { useRecoilState } from "recoil";
import { joinState } from "../../recoil/JoinState";

export default function FormHeader() {
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달의 열림/닫힘 상태를 관리
  const [joinData, setJoinData] = useRecoilState(joinState);

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
      <JF.ProfileLogo margin="35px">
        <img src={DefaultImg}></img>
      </JF.ProfileLogo>
      <FH.EnterArea margin="119px">
        <JF.PhotoNotice>프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.</JF.PhotoNotice>
        <JF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</JF.PhotoNotice>
      </FH.EnterArea>
      <JF.FormBtn onClick={openModal}>사진 변경</JF.FormBtn>
      {/* PhotoModal 컴포넌트를 렌더링하고 isOpen, onClose을 props로 전달 */}
      <PhotoModal
        isOpen={isModalOpen}
        onClose={closeModal}
      />
    </FH.Header>
  );
}
