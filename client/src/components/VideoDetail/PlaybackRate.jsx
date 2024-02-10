import React, { useState } from "react";
import styled, { keyframes } from "styled-components";

const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
`;

const PlaybackRateContainer = styled.div`
  position: absolute;
  top: -240px;
  left: -20px;
  background-color: #d3d3d34c;
	border-radius: 20px;
  color: white;
  padding: 10px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  display: ${props => props.isOpen ? 'block' : 'none'};
  animation: ${fadeIn} 0.5s ease-in-out;
  flex-direction: column;
  align-items: flex-end;
`;

const RateButton = styled.button`
  display: block;
  background-color: transparent;
  color: white;
  border: none;
  cursor: pointer;
  margin: 5px 0;
	text-align: center;
	width: 100%;
  &:hover {
    color: #ff0000;
  }
`;



const PlaybackRateButton = styled.div`
  position: relative;
  cursor: pointer;
`;

export default function PlaybackRate({ onChange }) {
  const [isOpen, setIsOpen] = useState(false);
  const rates = [0.5, 0.75, 1, 1.25, 1.5, 1.75, 2];

  const handleRateChange = (rate) => {
    onChange(rate);
    setIsOpen(false);
  };

  const handleButtonClick = (e) => {
    e.stopPropagation();
    setIsOpen(!isOpen);
  };

  return (
    <PlaybackRateButton onClick={handleButtonClick}>
      <img src="/src/assets/playspeed.png" alt="재생속도" />
      <PlaybackRateContainer isOpen={isOpen}>
        {rates.map((rate, index) => (
          <RateButton key={index} onClick={() => handleRateChange(rate)}>
            {rate}x
          </RateButton>
        ))}
      </PlaybackRateContainer>
    </PlaybackRateButton>
  );
}
