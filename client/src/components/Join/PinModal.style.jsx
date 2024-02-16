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

export const ModalContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const ModalContent = styled.div`
  background-color: #fffdfb;
  border: 1px solid black;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  width: 549px;
  height: 249px;
  text-align: center;
`;

export const ModalTitle = styled.div`
  font-family: "NYJ Gothic";
  font-weight: 300;
  font-size: 32px;
  color: black;
  margin-top: 40px;
  margin-bottom: 36px;
`;

export const PinInputContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 56px;
`;

export const PinInput = styled.div`
  width: 60px;
  height: 70px;
  margin: 0 5px;
  text-align: center;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 40px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  // 포커스 효과 제거
  outline: none;
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
  width: 549px;
  height: 66px;
  color: white;
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 20px;
`;
