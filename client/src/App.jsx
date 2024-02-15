import { BrowserRouter as RouterProvider } from "react-router-dom";
import styled, { createGlobalStyle } from "styled-components";
import NavigationBar from "./components/NavigationBar/NavigationBar";
import Router from "./components/Router";
import Footer from "./components/Footer/Footer";
import GoCreate from "./components/Footer/GoCreate";

export const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'BM JUA_TTF';
    src: url('./fonts/BM JUA_TTF.ttf') format('truetype');
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url('./fonts/NanumSquareRoundB.ttf') format('truetype');
    font-weight: bold;
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url('./fonts/NanumSquareRoundEB.ttf') format('truetype');
    font-weight: 800; // Extra Bold
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url('./fonts/NanumSquareRoundL.ttf') format('truetype');
    font-weight: 300; // Light
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url('./fonts/NanumSquareRoundR.ttf') format('truetype');
    font-weight: normal;
  }

  @font-face {
    font-family: 'PartialSansKR';
    src: url('./fonts/PartialSansKR-Regular.otf') format('opentype');
  }

  @font-face {
    font-family: 'NYJ Gothic B';
    src: url('./fonts/남양주 고딕 B.otf') format('opentype');
    font-weight: bold;
  }

  @font-face {
    font-family: 'NYJ Gothic EB';
    src: url('./fonts/남양주 고딕 EB.otf') format('opentype');
    font-weight: 800; // Extra Bold
  }

  @font-face {
    font-family: 'NYJ Gothic L';
    src: url('./fonts/남양주 고딕 L.otf') format('opentype');
    font-weight: 300; // Light
  }
  body {
    margin: 0;
    padding: 0;
    background-color: #fff9f6;
  }

  /* 다른 전역 스타일 추가 가능. */
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 100vh;
`

const Content = styled.div`
  flex: 1;
  margin-bottom: 7%;
`

export default function App() {
  return (
    <Container>
      <GlobalStyle />
      <RouterProvider>
        <NavigationBar />
        <Content>
          <Router />
        </Content>
        <GoCreate />
        <Footer />
      </RouterProvider>
    </Container>
  );
}
