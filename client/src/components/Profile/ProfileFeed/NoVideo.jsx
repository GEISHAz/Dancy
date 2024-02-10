import * as N from './NoVideo.style'

const NoVideo = () => {
  return (
    <N.Wrap>
      <N.Container>
          <N.NoImg />
          <N.NoTxt>게시물이 존재하지 않습니다.</N.NoTxt>
      </N.Container>
    </N.Wrap>
  )
}

export default NoVideo;