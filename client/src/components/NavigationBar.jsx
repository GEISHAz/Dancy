import { Link } from 'react-router-dom';
import styled from "styled-components";

const Nav = styled.div`
    display: flex;
    justify-content: space-between;
`;

const NavLeft = styled.div`
    display: flex;
`;

const NavRight = styled.div`
    display: flex;
`;

const NavItem = styled.div`
    margin-right: 10px;
`;

export default function Navbar() {
    return (
        <Nav>
            <NavLeft>
                <NavItem>
                    <Link to="/">Home</Link>
                </NavItem>
                <NavItem>
                    <Link to="/practice">Practice</Link>
                </NavItem>
                <NavItem>
                    <Link to="/stage">Stage</Link>
                </NavItem>
                <NavItem>
                    <Link to="/profile">Profile</Link>
                </NavItem>
            </NavLeft>
            <NavRight>
                <NavItem>
                    <Link to="/login">Login</Link>
                </NavItem>
                <NavItem>
                    <Link to="/signup">SignUp</Link>
                </NavItem>
            </NavRight>
        </Nav>
    );
}
