import styled from "styled-components";

export const Modal = styled.div`
  /*스크롤이 되도 모달창이 고정 되도록 position:fixed*/
  position: fixed;
  bottom: 0;
  /* 자식 컴포넌트인 모달창을 가운데 오게 하기 위해 flex설정*/
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
`;

export const ModalBackdrop = styled.div`
  background-color: rgba(0, 0, 0, 0.4);
  width: 100vw;
  height: 100vh;
`;

export const ModalWrap = styled.div`
  position: fixed;
`;

export const ModalView = styled.div.attrs((props) => ({ role: "dialog" }))`
  width: 404px;
  height: 500px;
  border: 1px solid black;
  border-radius: 20px;
  background-color: #fbf5f0;
  padding: 33px;
  display: flex;
  flex-direction: column;
  row-gap: 16px;
`;

export const Txt = styled.div`
  font-family: "NYJ Gothic";
  font-weight: bold;
  font-size: 20px;
  text-align: center;
`;

export const FollowList = styled.div`
  display: flex;
  flex-direction: column;
  row-gap: 20px;

  overflow: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;
