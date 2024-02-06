import { useState } from 'react';
import { Link } from 'react-router-dom';
import SearchBar from './SearchBar';
import Notification from './Notification';
import * as N from './NavigationBar.style';

export default function Navbar() {
  const [activeButton, setActiveButton] = useState('');
  console.log(activeButton)
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
          <N.NavProfile onClick={() => setActiveButton('Profile')} $active={activeButton === 'Profile'}>
            <Link to="/profile/:username">Profile</Link>
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
              <Link to="/login">Login</Link>
            </N.NavLogin>
          </>
        </N.NavRight>
      </N.NavTextArea>
    </N.NavArea>
  );
}
