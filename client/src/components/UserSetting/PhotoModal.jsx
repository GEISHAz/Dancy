import React, { useState, useRef, useEffect } from "react";
import styled from "styled-components";

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

const ModalContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ModalContent = styled.div`
  background-color: #fffdfb;
  border: 1px solid black;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  width: 595px;
  height: 524px;
  text-align: center;
`;

const ModalTitle = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 32px;
  color: black;
  margin-top: 40px;
  margin-bottom: 36px;
`;

const PinInputContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 56px;
`;

const PinInput = styled.div`
  width: 60px;
  height: 70px;
  margin: 0 5px;
  text-align: center;
  font-family: "NYJ Gothic B";
  font-size: 40px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  // 포커스 효과 제거
  outline: none;
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
  width: 549px;
  height: 66px;
  color: white;
  font-family: "NYJ Gothic B";
  font-size: 20px;
`;

const CustomModal = ({ isOpen, onClose, onSubmit }) => {
  const [pin, setPin] = useState(["", "", "", "", "", ""]);
  const pinRefs = useRef(pin.map(() => React.createRef()));

  const handlePinChange = (index, value) => {
    const newPin = [...pin];
    newPin[index] = value;
    setPin(newPin);

    if (value !== "" && index < pin.length - 1) {
      // 입력이 있고, 마지막 자리가 아니면 다음 ref로 포커스 이동
      pinRefs.current[index + 1].current.focus();
    }

    if (value !== "" && index === pin.length - 1) {
      // 마지막 자리에 도달하면 focus를 해제
      pinRefs.current[index].current.blur();
      return;
    }
  };

  const handleSubmit = () => {
    // 핀 번호가 모두 입력되었는지 확인
    if (pin.every((digit) => digit !== "")) {
      const pinValue = pin.join("");
      onSubmit(pinValue);
      onClose();
    }
  };

  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  useEffect(() => {
    // 모달이 열릴 때 첫 번째 div로 포커스 이동
    if (isOpen) {
      pinRefs.current[0].current.focus();
    }
  }, [isOpen]);

  useEffect(() => {
    // 모달이 닫힐 때 pin 상태 초기화
    if (!isOpen) {
      setPin(["", "", "", "", "", ""]);
    }
  }, [isOpen]);

  return (
    <ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <ModalContainer>
        <ModalContent>
          <ModalTitle>인증번호 입력</ModalTitle>
          <PinInputContainer>
            {pin.map((digit, index) => (
              <PinInput
                key={index}
                contentEditable
                ref={pinRefs.current[index]}
                onInput={(e) =>
                  handlePinChange(index, e.currentTarget.innerText)
                }
              >
                {digit}
              </PinInput>
            ))}
          </PinInputContainer>
        </ModalContent>
        <ModalButtonContainer>
          <ModalButton onClick={handleSubmit}>인증 완료</ModalButton>
        </ModalButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

export default CustomModal;
