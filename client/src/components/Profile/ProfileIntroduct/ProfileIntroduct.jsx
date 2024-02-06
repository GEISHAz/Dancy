import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import * as P from './ProfileIntroduct.style';
import FollowModal from './FollowModal';
import { useRecoilValue } from 'recoil';
import { userState } from "../../../recoil/LoginState.js";
import { userInfo } from '../../../api/myPage.js';
import { followerData, followingData } from '../../../api/follow.js';


export default function ProfileIntroduct() {
	const navigate = useNavigate()
	const { user_id } = useParams();
	const [ userDetail, setUserDetail ] = useState({})
	const [ followerInfo, setFollowerInfo ] = useState([])
	const [ followingInfo, setFollowingInfo ] = useState([])

	useEffect(() => {
		userInfo(user_id)
		.then((res) => {
			setUserDetail(res.userInfo)
			console.log(userDetail)
		})
		.catch((err) => {
			console.error(err)
			if (err.response.status === 404) {
				alert(err.response.data[0].message)
				navigate('/')
			}
		})
	}, [])

	const followBtnStyle = {
		backgroundColor: userDetail.followed ? '#AABBFF' : '#898989',
	}

	const [ isOpen, setIsOpen ] = useState(false);
	const [ isFollow, setIsFollow ] = useState(true);
	const getData = childData => {
		setIsOpen(childData);
	};

  const modalHandler = (what) => {
    if (what === 'follower') {
      setIsFollow(true)

			followerData(userDetail.nickname)
			.then((res) => {
				setFollowerInfo(res.follower)
				console.log(followerInfo)
			})

    } else if (what === 'following') {
      setIsFollow(false)

			followingData(userDetail.nickname)
			.then((res) => {
				setFollowingInfo(res.following)
				console.log(followingInfo)
			})
		}
		setIsOpen(true)
	}

  // const followerData = Profile.follower
  // const followingData = Profile.following

	return (
    <>
      {isOpen && (<FollowModal isFollow={isFollow} getData={getData} info={isFollow ? followerInfo : followingInfo} />)}
      
      <P.ProfileIntroBox>
        <P.ProfileRound>
          <P.ProfileImg src={userDetail.profileImageUrl} />
        </P.ProfileRound>
        
        <P.UserWrap>
          <P.UserName>{userDetail.nickname}</P.UserName>
					{userDetail.isMine ? <Link to='/setting'><P.SettingBtn /></Link> : null}
        </P.UserWrap>

        
        <P.FollowBox>
          <div onClick={() => modalHandler('follower')}>
            <P.FollowTitle>팔로워</P.FollowTitle>
            <P.FollowNum>{userDetail.follower}</P.FollowNum>
          </div>

          <div onClick={() => modalHandler('following')}>
            <P.FollowTitle>팔로잉</P.FollowTitle>
            <P.FollowNum>{userDetail.following}</P.FollowNum>
          </div>
        </P.FollowBox>

				{userDetail.isMine ? null :				
					<P.FollowBtn style={followBtnStyle}>
						{userDetail.followed ? '팔로우' : '팔로잉'}
					</P.FollowBtn>
				}

        <P.Hr />

        <P.IntroTxtBox>
          <P.IntroTitle>소개글</P.IntroTitle>
          <P.IntroTxt>{userDetail.introduceText}</P.IntroTxt>
        </P.IntroTxtBox>
      </P.ProfileIntroBox>
    </>
	)
};