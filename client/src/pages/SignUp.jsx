import styled from "styled-components";
import UserTerm from "/src/components/Join/TermOfUse";
import SelectJoinMethod from "../../src/components/Join/SelectJoinMethod";
import JoinComplete from "../components/Join/JoinComplete";
import JoinForm from "../components/Join/JoinForm";

export const Container = styled.div`
  width: 100%;
  padding: 4rem 24rem 4rem 24rem;
  text-align: center;
`;

export default function SignUp() {
  return <JoinForm />;
  {
    /*<JoinComplete></JoinComplete>;*/
  }
  {
    /*<SelectJoinMethod></SelectJoinMethod>*/
  }
}
