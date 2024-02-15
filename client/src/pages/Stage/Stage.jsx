import Card from '../../components/Stage/Card'
import * as S from './Stage.style'

export default function Stage() {
    return (
      <S.StageContainer>
        {/* <Search /> */}
        <S.CardThumbnail>
          <Card />
        </S.CardThumbnail>
      </S.StageContainer>
    )
}
