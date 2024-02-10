import styled from "styled-components";

export const SearchContainer = styled.div`
  width: 232px;
  height: 38px;
  position: relative;
`;

export const SearchBarOutline = styled.div`
  width: 232px;
  height: 38px;
  left: 0;
  position: absolute;
  background-color: white;
  border: 2px solid #252525;
  border-radius: 20px;
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
  padding-left: 12px;
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
  border: 2px solid #252525;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const SearchButtonImage = styled.img`
  z-index: 10;
  cursor: pointer;
`;