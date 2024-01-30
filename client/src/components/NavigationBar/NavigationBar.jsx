import { useState } from 'react';
import { Link } from 'react-router-dom';
import SearchBar from './SearchBar';
import Notification from './Notification';
import { PageButton, NavHome, NavPractice, NavStage, NavProfile, NavArea, NavRed, NavTextArea, NavLeft, NavRight, NavLeftContainer, NavSignUp, NavLogin, Square, AlertButton } from './NavigationBar.styeld'




export default function Navbar() {
  const [activeButton, setActiveButton] = useState('');

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
          <NavPractice onClick={() => setActiveButton('Practice')} $active={activeButton === 'Practice'}>
          <Link to="/practice">Practice</Link>
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
          <NavProfile onClick={() => setActiveButton('Profile')} $active={activeButton === 'Profile'}>
          <Link to="/profile/:username">Profile</Link>
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
            <Link to="/login">Login</Link>
          </NavLogin>
        </NavRight>
      </NavTextArea>
    </NavArea>
  );
}
