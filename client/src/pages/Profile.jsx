import ProfileFeed from "../components/Profile/ProfileFeed/ProfileFeed"
import ProfileIntroduct from "../components/Profile/ProfileIntroduct/ProfileIntroduct"

export default function Profile() {
	return (
		<div className="flex gap-x-2 justify-center mt-14">
			<ProfileIntroduct />
			<ProfileFeed />
		</div>
	)
}