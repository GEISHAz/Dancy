import React, {useState} from "react";
import { Link } from "react-router-dom";
import * as R from "./Recomment.style.jsx";
import { DropdownToggle } from "./Comment.style.jsx";

const Recomments = [
	{
		username: "namhyun._.",
		content: "내용입니다1",
		created_at: new Date(),
	},
	{
		username: "sunovo._.",
		content: "내용입니다2",
		created_at: new Date(),
	},
	{
		username: "minhodo._.",
		content: "내용입니다3",
		created_at: new Date(),
	},
]


const getTimeDifference = (prevDate) => {
	const diff = new Date() - prevDate;
	const seconds = Math.floor(diff / 1000);
	const minutes = Math.floor(seconds / 60);
	const hours = Math.floor(minutes / 60);
	const days = Math.floor(hours / 24);
	const weeks = Math.floor(days / 7);
	if (weeks > 0) {
		return `${weeks}주 전`;
	} else if (days > 0) {
		return `${days}일 전`;
	} else if (hours > 0) {
		return `${hours}시간 전`
	} else if (minutes > 0) {
		return `${minutes}분 전`
	} else {
		return `${seconds}초 전`
	}
};

export default function Recomment() {
	const [like, setLike] = useState(Recomments.map(() => false));
  const [likeCount, setLikeCount] = useState(Recomments.map(() => 0));
	const [dropdownOpen, setDropdownOpen] = useState(Recomments.map(() => false));

	const handleLike = (index) => {
		setLike(like.map((state, i) => i === index ? !state : state))
		if (!like[index]) {
			setLikeCount(likeCount.map((count, i) => i === index ? count + 1 : count));
		}	else {
			setLikeCount(likeCount.map((count, i) => i === index ? count - 1 : count));
		}
	}

	const toggleDropdown = (index) => {
		setDropdownOpen(dropdownOpen.map((state, i) => i === index ? !state : state));
	}

	return (
		<R.RecommentContainer>
			{Recomments.map((recomment, index) => (
				<R.RecommentArea key={index}>
					<R.RecommentUserProfileImage />
					<R.RecommentUserDetail >
						<R.RecommentUserNameArea>
							<Link to={`/profile/${recomment.username}`}>
								<R.RecommentUserName>{recomment.username}</R.RecommentUserName>
							</Link>
							<DropdownToggle onClick={() => toggleDropdown(index)}>⋮</DropdownToggle>
							{dropdownOpen[index] && (
								<R.RecommentEditDeleteArea>
									<R.RecommentEditImage src="/src/assets/editimage.png" />
									<R.RecommentDeleteImage src="/src/assets/deleteimage.png" />
								</R.RecommentEditDeleteArea>
							)}
						</R.RecommentUserNameArea>
					<R.RecommentContentArea>
						<R.RecommentContent>{recomment.content}</R.RecommentContent>
						<R.RecommentLikeImage src={like[index] ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"} onClick={() => handleLike(index)} />
					</R.RecommentContentArea>
					<R.RecommentCreateArea>
						<div>
							<R.RecommentCreatedAt>{getTimeDifference(recomment.created_at)}</R.RecommentCreatedAt>
						</div>
						<R.RecommentNumberOfLikes>{likeCount[index]}</R.RecommentNumberOfLikes>
					</R.RecommentCreateArea>
				
					</R.RecommentUserDetail>
				</R.RecommentArea>
			))}
		</R.RecommentContainer>
	)
};
