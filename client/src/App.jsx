import { BrowserRouter as RouterProvider } from 'react-router-dom';
import NavigationBar from './components/NavigationBar/NavigationBar';
import Router from './components/Router'

export default function App() {
	return (
		<RouterProvider>
			<NavigationBar />
			<Router />
		</RouterProvider>
	);
}
