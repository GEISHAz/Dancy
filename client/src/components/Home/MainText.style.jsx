import styled from "styled-components";

export const Wrapper = styled.div`
  margin-top: 80px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  row-gap: 200px;
`

export const Container = styled.div`
  /* width: 100%; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  row-gap: 50px;
  padding-left: 200px;
  padding-right: 200px;
`;

export const Context = styled.img`
  width: 500px;
  display: flex;
  justify-items: start;
  opacity: 0;
  transition: 2s;
  transform: translateX(-150px);

  &.show {
    opacity: 1;
    transform: translateX(0);
  }
`;

export const LogoWrap = styled.div`
  display: flex;
  align-items: end;
  justify-content: center;
  /* width: 100%; */
  /* height: 100%; */
`

export const DancyLogo = styled.img`
  /* width: 1000px; */
  width: 60%;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`;

export const Text1 = styled.img`
  width: 200px;
  height: 50px;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`;

export const Text2 = styled.img`
  width: 350px;
  display: flex;
  align-self: end;
  opacity: 0;
  transition: 4s;
  transform: translateX(200px);

  &.show {
    opacity: 2;
    transform: translateX(0);
  }
`

export const Text3 = styled.img`
  width: 580px;
  height: 300px;
  margin-left: 200px;
  /* position: absolute;
  top: 750px;
  left: 500px;
  opacity: 0;
  transition: 3s;
  transform: translateX(200px);
  scale: 1.2;
  
  &.show {
    opacity: 2;
    transform: translateX(0);
  } */
  `

  
  export const ScrollWrapper = styled.div`
    position: absolute;
  
  `