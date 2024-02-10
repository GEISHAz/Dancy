import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { userInfo } from '../../../api/myPage.js';
import { followRequest, followerData, followingData, unFollowRequest } from '../../../api/follow.js';
import * as P from './ProfileIntroduct.style';
import FollowModal from './FollowModal';
import { useRecoilValue } from 'recoil';
import { userState } from '../../../recoil/LoginState.js';


export default function ProfileIntroduct() {
	const navigate = useNavigate()
	const { user_id } = useParams();
	const [ userDetail, setUserDetail ] = useState({})         // 누구(=특정 유저)의 Profile페이지니?
	const [ followerInfo, setFollowerInfo ] = useState([])     // 특정 유저를 팔로우하는 사람의 목록
	const [ followingInfo, setFollowingInfo ] = useState([])   // 특정 유저가 팔로우하는 사람의 목록
	const user = useRecoilValue(userState)                     // 로그인 한 유저 정보

	// 누구의 Profile 페이지인지 확인
	useEffect(() => {
		userInfo(user_id)
		.then((res) => {
			setUserDetail(res.userInfo)
			console.log(res.userInfo)
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

	const [ isOpen, setIsOpen ] = useState(false);     // Modal On/Off 여부
	const [ isFollow, setIsFollow ] = useState(true);  // 조회하는게 follower(true) 목록인지 following(false) 목록인지?
	const getData = childData => {
		setIsOpen(childData);
	};

  // 팔로워 & 팔로잉 명 수 클릭 시 목록 조회 가능
  const modalHandler = async (what) => {
    if (what === 'follower') {                  // 팔로워 목록 조회
      setIsFollow(true)
      
      await followerData(userDetail.nickname)
      .then((res) => {
        console.log(res.follower)
        setFollowerInfo(res.follower)
        console.log(followerInfo)
      })

    } else if (what === 'following') {          // 팔로잉 목록 조회
      setIsFollow(false)

      await followingData(userDetail.nickname)
      .then((res) => {
        setFollowingInfo(res.following)
        console.log(followingInfo)
      })
      .catch((err) => {
        console.error(err)
      })
    }
		setIsOpen(true)
	}

	const followHandler = () => {
		if (userDetail.followed) {
			console.log(userDetail.nickname)
			unFollowRequest(userDetail.nickname)
			.then ((res) => {
				setUserDetail({
					...userDetail, 
					"follower" : res.follower.follower, 
					"following" : res.follower.following, 
					"followed" : res.follower.followed
				})
			})
			.catch((err) => {
				console.error(err)
			})
		} else {
			followRequest(userDetail.nickname)
			.then ((res) => {
				setUserDetail({
					...userDetail, 
					"follower" : res.follower.follower, 
					"following" : res.follower.following, 
					"followed" : res.follower.followed
				})
				console.log(res)
			})
			.catch((err) => {
				console.error(err)
			})
		}
	}

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
					<P.FollowBtn onClick={followHandler} style={followBtnStyle}>
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