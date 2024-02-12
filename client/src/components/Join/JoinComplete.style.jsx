import { styled } from "styled-components";

export const SelectJoinArea = styled.div`
  width: 100%;
  height: 680px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

export const LogoArea = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const GoLoginBtn = styled.button`
  width: 420px;
  height: 52px;
  border: 1px solid;
  border-color: black;
  border-radius: 50px;
  background-color: #e23e59;
  color: #ffffff;
  font-family: "NYJ Gothic B";
  font-size: 20px;
  margin-top: 40px;
  cursor: pointer;

  &:hover {
    background-color: #c0354c;
  }
`;
