import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import SearchBar from './SearchBar';
import Notification from './Notification';
import * as N from './NavigationBar.style';
import { loginState, userState } from "../../recoil/LoginState.js";
import { useRecoilValue, useRecoilState, useSetRecoilState } from 'recoil'
import { logout } from '../../api/auth.js';
import { userDetails } from '../../api/user.js';


export default function Navbar() {
  const [activeButton, setActiveButton] = useState('');
  const [userInfo, setUserInfo] = useRecoilState(userState)
  const navigate = useNavigate();

	const goProfileHandler = () => {
		userDetails()
		.then((res) => {
			setUserInfo(res.userInfo)
			setActiveButton('Profile')
		})
		.catch((err)=>{
			if (err.response.status === 401 ) {
				alert('로그인이 필요한 서비스입니다.')
				navigate("/login");
			}
		})
	}


	const setLogin = useSetRecoilState(loginState)
  const isLogin = useRecoilValue(loginState)

  const logoutHandler = () => {
    logout()
    setLogin(false);
  }

  return (
    <N.NavArea>
      <N.NavRed />
      <N.NavTextArea>
        <N.NavLeft>
        <N.NavLeftContainer>
          <N.NavHome onClick={() => setActiveButton('Home')} $active={activeButton === 'Home'}>
            <Link to="/">Home</Link>
          </N.NavHome>
          <N.Square>Home</N.Square>
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          <N.NavPractice onClick={() => setActiveButton('Create')} $active={activeButton === 'Create'}>
          <Link to="/create">Practice</Link>
          </N.NavPractice>
          <N.Square />
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          <N.NavStage onClick={() => setActiveButton('Stage')} $active={activeButton === 'Stage'}>
            <Link to="/stage">Stage</Link>
          </N.NavStage>
          <N.Square />
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          {/* <N.NavProfile onClick={() => setActiveButton('Profile')} $active={activeButton === 'Profile'}> */}
          <N.NavProfile onClick={goProfileHandler} $active={activeButton === 'Profile'}>
            <Link to={`/profile/${userInfo.nickname}`}>Profile</Link>
          </N.NavProfile>
          <N.Square />
        </N.NavLeftContainer>
        </N.NavLeft>
        <N.NavRight>
          <SearchBar />
          <N.AlertButton>
            <Notification />
          </N.AlertButton>
          <>
            <N.NavSignUp>
              <Link to="/signup">Join</Link>
            </N.NavSignUp>
            <N.NavLogin>
  						{isLogin ? <div onClick={logoutHandler}>Logout</div> : <Link to="/login">Login</Link> }
            {/* <Link to="/login">Login</Link> */}
            </N.NavLogin>
          </>
        </N.NavRight>
      </N.NavTextArea>
    </N.NavArea>
  );
}
