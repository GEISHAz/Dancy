import { useRecoilValue, useSetRecoilState } from 'recoil'
import { loginState } from '../recoil/LoginState.js';
import { logout } from '../api/auth.js'
import MainPage from '../components/Home/MainText.jsx'

export default function Home() {

  const setLogin = useSetRecoilState(loginState)
  const isLogin = useRecoilValue(loginState)

  const logoutHandler = () => {
    logout()
    setLogin(false);
  }

  return (
    <MainPage />
  )
};