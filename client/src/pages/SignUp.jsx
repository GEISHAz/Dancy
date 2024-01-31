import styled from "styled-components";
import UserTerm from "/src/components/Join/TermOfUse";
import SelectJoinMethod from "../../src/components/Join/SelectJoinMethod";

export const Container = styled.div`
  width: 100%;
  padding: 4rem 24rem 4rem 24rem;
  text-align: center;
`;

export default function SignUp() {
  return <SelectJoinMethod></SelectJoinMethod>;
}
