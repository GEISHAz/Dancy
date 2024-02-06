import ProfileFeed from "../components/Profile/ProfileFeed/ProfileFeed"
import ProfileIntroduct from "../components/Profile/ProfileIntroduct/ProfileIntroduct"

export default function Profile() {
	const Profile = {
		nickName: 'namhyun._.',
		introduceText: `너의 드라마 속 나는 지나가는 조연에 불과했지만..
    내 드라마 속 너는 주인공이었어..`,
		profileImageUrl: '',
		following: [
      { key: 1, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'min._.ho0o', following: true},
      { key: 2, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'sun_.ovo', following: false},
      { key: 3, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: '__jun9su', following: true},
      { key: 4, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'DWooNam_', following: true},
      { key: 5, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'sypark_030', following: false},
      { key: 6, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'SSAFY', following: false},
    ],
		follower: [
      { key: 1, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'min._.ho0o', following: true},
      { key: 2, profile: 'src/assets/profileIntro/ProfileImgDefault.png', nickName: 'sun_.ovo', following: false},
    ],
		followed: true,
	}

	return (
		<div className="flex gap-x-2 justify-center mt-14">
			<ProfileIntroduct />
			<ProfileFeed Profile={Profile} />
		</div>
	)
}