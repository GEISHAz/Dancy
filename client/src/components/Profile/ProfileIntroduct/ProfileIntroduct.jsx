import * as P from './ProfileIntroduct.style';


export default function ProfileIntroduct({Profile}) {
	const followBtnStyle = {
		backgroundColor: Profile.followed ? '#AABBFF' : '#898989',
	}
	return (
		<P.ProfileIntroBox>
			<P.ProfileRound>
				<P.ProfileImg />
			</P.ProfileRound>
			
			<P.UserName>{Profile.nickName}</P.UserName>
			
			<P.FollowBox>
				<div>
					<P.FollowTitle>팔로워</P.FollowTitle>
					<P.FollowNum>{Profile.follower}</P.FollowNum>
				</div>

				<div>
					<P.FollowTitle>팔로잉</P.FollowTitle>
					<P.FollowNum>{Profile.following}</P.FollowNum>
				</div>
			</P.FollowBox>

			<P.FollowBtn style={followBtnStyle}>
				{Profile.followed ? '팔로우' : '팔로잉'}
			</P.FollowBtn>

			<P.Hr />

			<P.IntroTxtBox>
				<P.IntroTitle>소개글</P.IntroTitle>
				<P.IntroTxt>{Profile.introduceText}</P.IntroTxt>
			</P.IntroTxtBox>
		</P.ProfileIntroBox>
	)
}