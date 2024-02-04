import * as F from './Footer.style'

export default function Footer () {
  return (
    <F.Wrap>
      <F.Box>
        <F.Logo />
        <F.CopyTxt>
          <p>본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.</p>
          <p>Copyright © SAMSUNG All Rights Reserved.</p>
        </F.CopyTxt>
      </F.Box>

      <F.Box>
        <F.CopyTxt>
          <p>♬ 팀명: 구미 D210</p>
          <p>♬ 팀장: 구미 D210 남동우</p>
          <p>♬ 팀원: 강정수, 박설연, 이해진, 정민호, 조남현</p>
        </F.CopyTxt>
        <F.CopyTxt>
          <p>♬ 전화번호: 02-XXX-XXXX</p>
          <p>♬ 이메일: ssafy_dancy@ssamail.com</p>
          <p>♬ 주소: 경북 구미시 3공단3로 302 SSAFY 404호</p>
        </F.CopyTxt>
      </F.Box>
    </F.Wrap>
  )
}