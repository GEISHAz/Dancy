import styled from "styled-components";
import BtnIcon from '../../assets/Footer/FooterIcon.png'

export const GoBtn = styled.button`
  position: fixed;
  right: 15px;
  bottom: 15px;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: #FFE2E2;
  box-shadow: 4px 4px 4px rgba(58, 56, 56, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
`

export const BtnImg = styled.img.attrs({
  src: 'src/assets/Footer/FooterIcon.png'
})`
  width: 60px;
  height: 60px;
`