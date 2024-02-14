import styled from "styled-components";

export const Container = styled.div`

`;

export const Context = styled.img`
  position: absolute;
  opacity: 0;
  top: 150px;
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
  left: 190px;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);
  width: 40%;

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`;

export const Text1 = styled.img`
  position: absolute;
  top: 370px;
  left: 800px;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`;

export const Text2 = styled.img`
  position: absolute;
  top: 500px;
  left: 850px;
  width: 25%;
  /* opacity: 0; */
  transition: 4s;
  transform: translateX(200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`

export const Wrapper = styled.div`

`

export const Text3 = styled.img`
  position: absolute;
  top: 750px;
  left: 500px;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);
  scale: 1.2;

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`