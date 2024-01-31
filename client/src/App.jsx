import { BrowserRouter as RouterProvider } from "react-router-dom";
import NavigationBar from "./components/NavigationBar/NavigationBar";
import Router from "./components/Router";

import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`
  body {
    margin: 0;
    padding: 0;
    background-color: #fff9f6; /* 원하는 배경색으로 설정 */
  }

  /* 다른 전역 스타일 추가 가능. */
`;

export default function App() {
  return (
    <>
      <GlobalStyle />
      <RouterProvider>
        <NavigationBar />
        <Router />
      </RouterProvider>
    </>
  );
}
