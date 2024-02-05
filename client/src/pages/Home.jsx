import { useRecoilValue, useSetRecoilState } from 'recoil'
import { loginState } from '../recoil/LoginState.js';
import { logout } from '../api/auth.js'

export default function Home() {

  const setLogin = useSetRecoilState(loginState)
  const isLogin = useRecoilValue(loginState)

  const logoutHandler = () => {
    logout()
    setLogin(false);
  }

  return (
    <div>
      {isLogin ? <button onClick={logoutHandler}>로그아웃</button> : <h1>Go Login</h1>}
    </div>
  )
}