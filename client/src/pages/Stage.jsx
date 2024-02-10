import Card from '../components/Stage/Card'
import styled from "styled-components";

export const StageContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: auto;
`

export const CardThumbnail = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 1000px;
  height: auto;
  margin-top: 50px;
  gap: 50px;
  text-align: center;
`;

export default function Stage() {
    return (
        <StageContainer>
            <CardThumbnail>
                <Card />
            </CardThumbnail>
        </StageContainer>
    )
}
