import styled from "styled-components";

export const Context = styled.img`
	position: absolute;
  opacity: 0;
	top: 100px;
	left: 100px;
  transition: 2s;
  transform: translateX(-150px);

  &.show {
    opacity: 1;
    transform: translateX(0);
  }
`;

export const DancyLogo = styled.img`
  position: absolute;
	top: 250px;
  left: 150px;
  opacity: 0;
  transition: 3s;
  transform: translateX(-200px);
	width: 40%;

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`;

export const Text1 = styled.img`
  position: absolute;
	top: 380px;
  left: 775px;
  opacity: 0;
  transition: 3s;
  transform: translateX(-200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`
