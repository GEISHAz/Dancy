import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import * as P from './ProfileIntroduct.style';
import FollowModal from './FollowModal';
import { useRecoilValue } from 'recoil';
import { userState } from "../../../recoil/LoginState.js";


export default function ProfileIntroduct({Profile}) {
  const user = useRecoilValue(userState)

	const followBtnStyle = {
		backgroundColor: Profile.followed ? '#AABBFF' : '#898989',
	}

	const [isOpen, setIsOpen] = useState(false);
	const [isFollower, setFollower] = useState(true);
	const getData = childData => {
		setIsOpen(childData);
	};

  const modalHandler = (what) => {
    if (what === 'follower') {
      setFollower(true)
    } else if (what === 'following'){
      setFollower(false)
    }
    setIsOpen(true)
  }

  const followerData = Profile.follower
  const followingData = Profile.following

	return (
    <>
      {isOpen && (<FollowModal isFollower={isFollower} getData={getData} Profile={isFollower ? followerData : followingData} />)}
      
      <P.ProfileIntroBox>
        <P.ProfileRound>
          <P.ProfileImg src={user.profileImageUrl} />
          {/* <P.ProfileImg onClick={testHandle} /> */}
        </P.ProfileRound>
        
        <P.UserWrap>
          <P.UserName>{user.nickname}</P.UserName>
          <Link to='/setting'><P.SettingBtn /></Link>
        </P.UserWrap>

        
        <P.FollowBox>
          <div onClick={() => modalHandler('follower')}>
            <P.FollowTitle>팔로워</P.FollowTitle>
            <P.FollowNum>{Profile.follower.length}</P.FollowNum>
          </div>

          <div onClick={() => modalHandler('following')}>
            <P.FollowTitle>팔로잉</P.FollowTitle>
            <P.FollowNum>{Profile.following.length}</P.FollowNum>
          </div>
        </P.FollowBox>

        <P.FollowBtn style={followBtnStyle}>
          {Profile.followed ? '팔로우' : '팔로잉'}
        </P.FollowBtn>

        <P.Hr />

        <P.IntroTxtBox>
          <P.IntroTitle>소개글</P.IntroTitle>
          <P.IntroTxt>{user.introduceText}</P.IntroTxt>
        </P.IntroTxtBox>
      </P.ProfileIntroBox>
    </>
	)
}