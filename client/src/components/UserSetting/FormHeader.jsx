import React, { useState } from "react";
import { styled } from "styled-components";
import * as SF from "./SettingForm.style";
import DefaultImg from "../../assets/join/picture.png";
import PhotoModal from "./PhotoModal";

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const Header = styled.div`
  display: flex;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
  text-align: center;
  justify-content: center;
  align-items: center;
`;

export default function FormHeader() {
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달의 열림/닫힘 상태를 관리

  // 모달을 열기 위한 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달을 닫기 위한 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <Header>
      <SF.ProfileLogo margin="35px">
        <img src={DefaultImg}></img>
      </SF.ProfileLogo>
      <EnterArea margin="114px">
        <SF.PhotoNotice>프로필 사진은 10MB 이하의 파일만 업로드할 수 있습니다.</SF.PhotoNotice>
        <SF.PhotoNotice>(확장자: jpg, jpeg, png, svg)</SF.PhotoNotice>
      </EnterArea>
      <SF.FormBtn onClick={openModal}>사진 변경</SF.FormBtn>
      {/* PhotoModal 컴포넌트를 렌더링하고 isOpen, onClose을 props로 전달 */}
      <PhotoModal isOpen={isModalOpen} onClose={closeModal} />
    </Header>
  );
}
