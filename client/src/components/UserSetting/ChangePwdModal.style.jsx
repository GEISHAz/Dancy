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
  z-index: 100;
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
  width: 595px;
  height: 458px;
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
  margin-top: 43px;
  margin-bottom: 30px;
`;

// 텍스트 엔터 처리
export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  align-self: flex-start;
  margin-left: 36px;
  margin-bottom: 33px;
  margin-top: 39px;
`;

export const ChangePwdTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 18px;
  color: black;
  margin-left: 74px;
  align-self: flex-start;
`;

// 인풋 커스텀
export const ChangeInput = styled.input`
  width: 448px;
  height: 46px;
  border: 1px solid black;
  border-radius: 3px;
  margin-bottom: 16px;
  padding-left: 10px;

  &:focus {
    outline: 2px solid #e23e59;
    border: none;
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
  width: 595px;
  height: 66px;
  color: white;
  font-family: "NYJ Gothic B";
  font-size: 20px;
`;

export const InfoText = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 16px;
  color: #6c6c6c;
  align-self: flex-start;
  margin-left: 74px;
  margin-bottom: 33px;
  margin-top: 24px;
`;
