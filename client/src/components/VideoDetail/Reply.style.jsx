import styled from "styled-components";

export const ReplyWrapper = styled.div`
  position: relative;
  width: 100%;
  margin: 10px 0px;
`

export const ReplyInput = styled.input`
  font-size: 15px;
  color: #222222;
  width: 100%;
  border: none;
  border-bottom: solid #aaaaaa 1px;
  padding-bottom: 10px;
  padding-left: 10px;
  position: relative;
  background: none;
  z-index: 5;

  ::placeholder {
    color: #aaaaaa;
  }

  &:focus {
    outline: none;
  }
`;

export const ReplyButton = styled.button`
  display: block;
  font-size: 12px;
  margin-top: 10px;
  color: #252525;
  padding: 5px 10px;
  cursor: pointer;

  &:hover {
    border-radius: 20px;
    color: white;
    background-color: #e4b1c9;
  }
`;

export const CancelButton = styled.button`
  display: block;
  font-size: 12px;
  margin-top: 10px;
  color: #252525;
  padding: 5px 10px;
  cursor: pointer;
  
  &:hover {
    border-radius: 20px;
    color: white;
    background-color: #e4b1c9;
  }
`;

export const ReplyBtns = styled.div`
  display: flex;
  flex-direction: row-reverse;
  gap: 10px;
`