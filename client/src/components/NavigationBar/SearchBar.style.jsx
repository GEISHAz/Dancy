import styled from "styled-components";

export const SearchContainer = styled.div`
  width: 232px;
  height: 38px;
  position: relative;
`;

export const SearchBarOutline = styled.div`
  width: 188px;
  height: 38px;
  left: 0;
  position: absolute;
  background-color: white;
  border: 1px solid #252525;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
`;

export const SearchBarInput = styled.input`
  width: 188px;
  height: 38px;
  left: 4px;
  top: 0;
  position: absolute;
  background-color: transparent;
  border: 0;
  outline: none;
  padding: 0px 10px;
  letter-spacing: 0;
  
  &::placeholder {
    letter-spacing: 2px;
		color: gray;
		font-weight: 500;
  }
`;

export const SearchButtonContainer = styled.div`
  width: 44px;
  height: 38px;
  left: 188px;
  top: 0;
  position: absolute;
  background-color: #F28193;
  border: 1px solid #252525;
  border-left: none;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
`;

export const SearchButtonImage = styled.img`
  z-index: 10;
  cursor: pointer;
`;