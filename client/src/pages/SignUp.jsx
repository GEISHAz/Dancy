import styled from "styled-components";
import UserTerm from "/src/components/Join/TermOfUse";

export const Container = styled.div`
  width: 100%;
  padding: 4rem 24rem 4rem 24rem;
  text-align: center;
`;

export default function SignUp() {
  return (
    <div>
      <Container>
        <UserTerm />
      </Container>
    </div>
  );
}
