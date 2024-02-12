import { styled } from "styled-components";

export const ModalOverlay = styled.div`
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
export const ModalContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

// 모달 전체
export const ModalContent = styled.div`
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
export const ModalTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 28px;
  color: black;
  margin-top: 24px;
  margin-bottom: 20px;
`;

// 사진 첨부 공간(점선)
export const ImportContainer = styled.div`
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

export const ImportLogo = styled.div`
  width: 150px;
  height: 140px;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

export const ModalButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

export const ModalButton = styled.button`
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
