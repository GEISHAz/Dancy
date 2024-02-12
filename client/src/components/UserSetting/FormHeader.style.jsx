import { styled } from "styled-components";

export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const Header = styled.div`
  display: flex;
  margin-top: 3.5rem;
  margin-bottom: 3.5rem;
  text-align: center;
  justify-content: center;
  align-items: center;
`;
