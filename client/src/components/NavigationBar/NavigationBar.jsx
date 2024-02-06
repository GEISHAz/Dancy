import { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import SearchBar from './SearchBar';
import Notification from './Notification';
import * as N from './NavigationBar.style';
import { loginState, userState } from "../../recoil/LoginState.js";
import { useRecoilValue, useRecoilState, useSetRecoilState } from 'recoil'
import { logout } from '../../api/auth.js';
import { userDetails } from '../../api/user.js';


export default function Navbar() {
  const [activeButton, setActiveButton] = useState('');
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const [userDetailsInfo, setUserDetailsInfo] = useState(null);
  const location = useLocation();

	const setLogin = useSetRecoilState(loginState)
  const isLogin = useRecoilValue(loginState)

  const logoutHandler = () => {
    logout()
    setLogin(false);
		navigate('/')
  }

  // userDetails는 비동기처리하여서, 호출된 값이 없음.
  // 따라서, 컴포넌트가 마운트될 때 userDetails 호출 후 결과를 표출.
  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const { userInfo } = await userDetails();
        setUserDetailsInfo(userInfo);
      } catch (error) {
        console.error(error);
      }
    };

    fetchUserDetails();
  }, []);

  return (
    <N.NavArea>
      <N.NavRed />
      <N.NavTextArea>
        <N.NavLeft>
        <N.NavLeftContainer>
        <Link to="/">
          <N.NavHome onClick={() => setActiveButton('Home')} $active={activeButton === 'Home'}>
            Home
          </N.NavHome>
        </Link>
        <N.Square />
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          <Link to="/create">
            <N.NavPractice onClick={() => setActiveButton('Create')} $active={activeButton === 'Create'}>
              Practice
            </N.NavPractice>
          </Link>
          <N.Square />
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          <Link to="/stage">
            <N.NavStage onClick={() => setActiveButton('Stage')} $active={activeButton === 'Stage'}>
              Stage
            </N.NavStage>
            <N.Square />
          </Link>
        </N.NavLeftContainer>
        <N.NavLeftContainer>
          <Link to={`/profile/${userInfo.nickname}`}>
              <N.NavProfile onClick={() => setActiveButton('Profile')} $active={activeButton === 'Profile'}>Profile</N.NavProfile>
          </Link>
          <N.Square />
        </N.NavLeftContainer>
        </N.NavLeft>
        <N.NavRight>
          <SearchBar />
          <N.AlertButton>
            <Notification />
          </N.AlertButton>
          <N.NavLogin>
          {isLogin ? (
            <N.NavLoginWrapper>
              <Link to='/setting'>
                <N.NavUserProfileImage src={userDetails.profileImageUrl}/>
              </Link>
              <N.NavProfileArea>
                <Link to='/setting'>
                  <N.NavUserName>{userDetailsInfo?.nickname} 님</N.NavUserName>
                </Link>
              <N.NavLogout onClick={logoutHandler}>Logout</N.NavLogout>
              </N.NavProfileArea>
            </N.NavLoginWrapper>
          ) : (
            <N.NavLogoutWrapper>
              <N.NavSignUp>
                <Link to="/signup">Join</Link>
              </N.NavSignUp>
              <N.NavLogin>
                <Link to="/login">Login</Link>
              </N.NavLogin>
            </N.NavLogoutWrapper>
          )}
          </N.NavLogin>
        </N.NavRight>
      </N.NavTextArea>
    </N.NavArea>
  );
}
