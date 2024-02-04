import styled from 'styled-components'

export const Wrap = styled.div`
  width: 100%;
  height: 160px;
  background-color: #F18C9D;
  display: flex;
  align-items: center;
  padding: 30px;
  column-gap: 80px;
`

export const Logo = styled.img.attrs({
  src: 'src/assets/DancyLogo.png', alt: 'logo'
})`
  height: 100px;
`

export const CopyTxt = styled.div`
  font-family: 'NanumSquareRound';
  font-size: 10px;
  color: white;
`

export const Box = styled.div`
  display: flex;
  align-items: center;
  column-gap: 40px;
`