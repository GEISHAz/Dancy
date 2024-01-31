import ProfileFeed from "../components/Profile/ProfileFeed/ProfileFeed"
import ProfileIntroduct from "../components/Profile/ProfileIntroduct/ProfileIntroduct"

export default function Profile() {
	const Profile = {
		nickName: 'namhyun._.',
		introduceText: `안 아픈 줄 알았는데......\n 너무 아파서..\n감각이 없었던 거 였어.`,
		profileImageUrl: '',
		following: 1,
		follower: 672,
		followed: true,
	}
	
	return (
		<div className="flex gap-x-2 justify-center mt-14">
			<ProfileIntroduct Profile={Profile} />
			<ProfileFeed Profile={Profile} />
		</div>
	)
}