import { BrowserRouter as RouterProvider } from "react-router-dom";
import styled, { createGlobalStyle } from "styled-components";
import NavigationBar from "./components/NavigationBar/NavigationBar";
import Router from "./components/Router";
import Footer from "./components/Footer/Footer";
import GoCreate from "./components/Footer/GoCreate";

import bmJUA from "./fonts/BM JUA_TTF.ttf";
import nsB from "./fonts/NanumSquareRoundB.ttf";
import nsEB from "./fonts/NanumSquareRoundEB.ttf";
import nsL from "./fonts/NanumSquareRoundL.ttf";
import nsR from "./fonts/NanumSquareRoundR.ttf";
import psr from "./fonts/PartialSansKR-Regular.otf";
import nyjB from "./fonts/남양주 고딕 B.otf";
import nyjEB from "./fonts/남양주 고딕 EB.otf";
import nyjL from "./fonts/남양주 고딕 L.otf";

export const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'BM JUA_TTF';
    src: url(${bmJUA}) format('truetype');
    font-weight: normal;
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url(${nsB}) format('truetype');
    font-weight: bold;
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url(${nsEB}) format('truetype');
    font-weight: 800; // Extra Bold
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url(${nsL}) format('truetype');
    font-weight: 300; // Light
  }

  @font-face {
    font-family: 'NanumSquareRound';
    src: url(${nsR}) format('truetype');
    font-weight: normal;
  }

  @font-face {
    font-family: 'PartialSansKR';
    src: url(${psr}) format('opentype');
  }

  @font-face {
    font-family: 'NYJ Gothic';
    src: url(${nyjB}) format('opentype');
    font-weight: bold;
  }

  @font-face {
    font-family: 'NYJ Gothic';
    src: url(${nyjEB}) format('opentype');
    font-weight: 800; // Extra Bold
  }

  @font-face {
    font-family: 'NYJ Gothic';
    src: url(${nyjL}) format('opentype');
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
`;

const Content = styled.div`
  flex: 1;
  margin-bottom: 7%;
`;

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
