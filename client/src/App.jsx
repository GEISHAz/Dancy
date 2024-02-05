import { BrowserRouter as RouterProvider } from "react-router-dom";
import styled, { createGlobalStyle } from "styled-components";
import NavigationBar from "./components/NavigationBar/NavigationBar";
import Router from "./components/Router";
import Footer from "./components/Footer/Footer";
import GoCreate from "./components/Footer/GoCreate";

export const GlobalStyle = createGlobalStyle`
  body {
    margin: 0;
    padding: 0;
    background-color: #fff9f6; /* 원하는 배경색으로 설정 */
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
