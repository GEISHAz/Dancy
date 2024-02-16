import styled, { keyframes } from "styled-components";

export const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
`;

export const PlaybackRateContainer = styled.div`
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

export const RateButton = styled.button`
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


export const PlaybackRateButton = styled.div`
  position: relative;
  cursor: pointer;
`;