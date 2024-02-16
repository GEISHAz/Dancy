import React, { useState } from "react";
import * as PR from "./PlaybackRate.style"


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
    <PR.PlaybackRateButton onClick={handleButtonClick}>
      <img src="/src/assets/playspeed.png" alt="재생속도" />
      <PR.PlaybackRateContainer isOpen={isOpen}>
        {rates.map((rate, index) => (
          <PR.RateButton key={index} onClick={() => handleRateChange(rate)}>
            {rate}x
          </PR.RateButton>
        ))}
      </PR.PlaybackRateContainer>
    </PR.PlaybackRateButton>
  );
}
