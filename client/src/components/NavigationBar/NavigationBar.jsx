import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import SearchBar from './SearchBar';
import Notification from './Notification';
import { PageButton, NavHome, NavPractice, NavStage, NavProfile, NavArea, NavRed, NavTextArea, NavLeft, NavRight, NavLeftContainer, NavSignUp, NavLogin, Square, AlertButton } from './NavigationBar.style'
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
		navigate('/')
  }

  return (
    <NavArea>
      <NavRed />
      <NavTextArea>
        <NavLeft>
        <NavLeftContainer>
          <NavHome onClick={() => setActiveButton('Home')} $active={activeButton === 'Home'}>
          <Link to="/">Home</Link>
          </NavHome>
          <Square>Home</Square>
        </NavLeftContainer>
        <NavLeftContainer>
          <NavPractice onClick={() => setActiveButton('Create')} $active={activeButton === 'Create'}>
          <Link to="/create">Practice</Link>
          </NavPractice>
          <Square />
        </NavLeftContainer>
        <NavLeftContainer>
          <NavStage onClick={() => setActiveButton('Stage')} $active={activeButton === 'Stage'}>
          <Link to="/stage">Stage</Link>
          </NavStage>
          <Square />
        </NavLeftContainer>
        <NavLeftContainer>
          {/* <NavProfile onClick={() => setActiveButton('Profile')} $active={activeButton === 'Profile'}> */}
          <NavProfile onClick={goProfileHandler} $active={activeButton === 'Profile'}>
          <Link to={`/profile/${userInfo.nickname}`}>Profile</Link>
          </NavProfile>
          <Square />
        </NavLeftContainer>
        </NavLeft>
        <NavRight>
          <SearchBar />
          {/* onClick method 만들것! */}
          <AlertButton>
            <Notification />
          </AlertButton>
          <NavSignUp>
            <Link to="/signup">Join</Link>
          </NavSignUp>
          <NavLogin>
						{isLogin ? <div onClick={logoutHandler}>Logout</div> : <Link to="/login">Login</Link> }
            {/* <Link to="/login">Login</Link> */}
          </NavLogin>
        </NavRight>
      </NavTextArea>
    </NavArea>
  );
}
