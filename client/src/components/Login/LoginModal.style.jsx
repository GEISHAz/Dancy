import { styled } from "styled-components";

export const ModalBackdrop = styled.div`
	background-color: rgba(0, 0, 0, 0.4);
	width: 100vw;
  height: 100vh;
  /*스크롤이 되도 모달창이 고정 되도록 position:fixed*/
  position: fixed;
  bottom: 0;
  /* 자식 컴포넌트인 모달창을 가운데 오게 하기 위해 flex설정*/
  display: flex;
  justify-content: center;
  align-items: center;
`

export const ModalView = styled.div.attrs((props) => ({role: "dialog"}))`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  width: 200px;
  height: 100px;
  border-radius: 1rem;
  background-color: white;
  
	> .close-btn {
  // X 버튼을 화면 정중앙으로 정렬
    position: absolute;
    // 중앙에서 위로 10px 이동
    top: 10px;
    cursor: pointer;
  }
`