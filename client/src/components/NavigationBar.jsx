import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled, { css } from "styled-components";

const Button = styled.div`
    border: 1px solid #252525;
    width: 95px;
    height: 32px;
    text-align: center;
    border-radius: 5px;
    position: relative;
    box-shadow: inset 0px 0px rgba(0, 0, 0, 0.2);
    transition: transform 0.2s ease-in-out;


    ${({ $active }) =>
        $active && css`
        transform: translate(3px, 3px);
        
        &:after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            border-radius: 5px;
            z-index: 1;
            box-shadow: inset 2px 2px rgba(0, 0, 0, 0.2);
            transition: box-shadow 8s ease-in-out;
        }
    `}
`;



const NavHome = styled(Button)`
    background-color: #FFC7BE;
`;
const NavPractice = styled(Button)`
    background-color: #FFE774;
`;
const NavStage = styled(Button)`
    background-color: #0FD6B8;
`;
const NavProfile = styled(Button)`
    background-color: #AABBFF;
`;

const NavArea = styled.div`
    width: 100%;
    height: 60px;
    font-size: 20px;
    color: #252525;
    font-family: 'NanumSquareRound', sans-serif;
`;

const NavRed = styled.div`
    position: relative;
    background-color: #E23E59;
    width: 100%;
    height: 5px;
    margin: 0 0 20px;
`;

const NavTextArea = styled.div`
    display: flex;
    justify-content: space-between;
    margin-bottom: 0px;
`;

const NavLeft = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin: 0 0 0 32px;
    gap: 34px;
`;

const NavRight = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-end;
    margin-right: 46px;
    gap: 43px;
`;

const NavLeftContainer = styled.div`
    position: relative;
`;

const NavSignUp = styled.div`
    font-size: 16px;
    margin-right: 10px;
`;

const NavLogin = styled.div`
    margin-right: 10px;
    font-size: 16px;
`;

const Square = styled.div`
    border: 1px solid black;
    width: 95px;
    height: 32px;
    border-radius: 5px;
    position: absolute;
    top: 0;
    left: 0;
    margin: 3px;
    z-index: -1;
    text-align: center;
`;

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
                    <Link to="/profile">Profile</Link>
                    </NavProfile>
                    <Square />
                </NavLeftContainer>
                </NavLeft>
                <NavRight>
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
