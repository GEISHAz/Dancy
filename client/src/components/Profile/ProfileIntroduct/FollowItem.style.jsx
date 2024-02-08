import styled, { css } from "styled-components";
import DefaultImg from '../../../assets/profileIntro/ProfileImgDefault.png'

export const Profile = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`

export const ProfileImg = styled.img.attrs(({src}) => ({
  src: src || DefaultImg
}))`
  width: 44px;
  height: 44px;
  border-radius: 50%;
`

export const NickName = styled.div`
  font-family: 'NanumSquareRound';
  font-size: 16px;
  color: #454545;
`

export const FollowBtn = styled.div`
  width: 96px;
  height: 41px;
  border: 1px solid black;
  border-radius: 20px;
  background-color: #858585;
  color: #FFFFFF;
  position: relative;
  font-family: 'NanumSquareRound';
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;

  ::before {
    content: ${props => (props.isFollow ? '팔로잉' : '팔로우')};
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  ${props => props.isFollow && css`
    background-color: #AABBFF;
  `}
`