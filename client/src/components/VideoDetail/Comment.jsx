import React, {useState, useEffect} from "react";
import { Link } from "react-router-dom";
import Recomment from "./Recomment"
import { 
  CommentContainer,
  CommentTitleArea,
  CommentTitle,
  CommentTitleLine,
  CommentArea,
  CommentUserProfileImage,
  CommentUserDetail,
  CommentUserNameArea,
  CommentUserName,
  CommentEditImage,
	DropdownToggle,
  CommentEditDeleteArea,
  CommentDeleteImage,
  CommentContentArea,
  CommentContent,
  CommentLikeImage,
  CommentCreateRecommentArea,
  CommentCreatedAt,
  CommentCreateRecomment,
  CommentNumberOfLikes,
  RecommentArea,
  RecommentLine,
  MoreRecomments,
  AnimatedRecomment,
	ReplyWrapper,
  ReplyInput,
  ReplyButton,
  CancelButton,
	ReplyBtns,
} from "./Comment.style"

const Comments = [
  {
    username: "namhyun._.2",
		content: "저랑 뷔랑 닮았나요? ㅎㅎ",
    created_at: new Date(),
  },
  {
    username: "sunovo._.2",
		content: "저랑 뷔랑 닮았나요? ㅎㅎ",
    created_at: new Date(),
  },
  {
    username: "minhodo._.2",
		content: "저랑 뷔랑 닮았나요? ㅎㅎ",
    created_at: new Date(),
  },
  {
    username: "seolyeon._.2",
		content: "저랑 뷔랑 닮았나요? ㅎㅎ",
    created_at: new Date(),
  },
  {
    username: "dongwoo._.2",
		content: "저랑 뷔랑 닮았나요? ㅎㅎ",
    created_at: new Date(),
  }
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

export default function Comment() {
	const [like, setLike] = useState(Comments.map(() => false));
  const [likeCount, setLikeCount] = useState(Comments.map(() => 0));
	const [isRecommentOpen, setIsRecommentOpen] = useState(Comments.map(() => false));
	const [isReplyInputOpen, setIsReplyInputOpen] = useState(Comments.map(() => false));
	const [placeholder, setPlaceholder] = useState("답글을 입력하세요");
	const [dropdownOpen, setDropdownOpen] = useState(Comments.map(() => false));



	const handleLike = (index) => {
    setLike(like.map((state, i) => i === index ? !state : state));
    if (!like[index]) {
      setLikeCount(likeCount.map((count, i) => i === index ? count + 1 : count));
    } else {
      setLikeCount(likeCount.map((count, i) => i === index ? count - 1 : count));
    }
	}

	const toggleRecomment = (index) => {
		setIsRecommentOpen(isRecommentOpen.map((state, i) => i === index ? !state : state));
	}

	const toggleReplyInput = (index) => {
		setIsReplyInputOpen(isReplyInputOpen.map((state, i) => i === index ? !state : state));
	}

	const toggleDropdown = (index) => {
    setDropdownOpen(dropdownOpen.map((state, i) => i === index ? !state : state));
  }

	return (
		<CommentContainer>
			<CommentTitleArea>
				<CommentTitle>댓글</CommentTitle>
				<CommentTitleLine />
			</CommentTitleArea>
			{Comments.map((comment, index) => (
				<CommentArea key={index}>
					<CommentUserProfileImage />
					<CommentUserDetail>
						<CommentUserNameArea>
							<div>
							<Link to={`/profile/${comment.username}`}>
								<CommentUserName>{comment.username}</CommentUserName>
							</Link>
							<CommentCreatedAt>{getTimeDifference(comment.created_at)}</CommentCreatedAt>
							</div>
							<DropdownToggle onClick={() => toggleDropdown(index)}>⋮</DropdownToggle>
							{dropdownOpen[index] && (
								<CommentEditDeleteArea>
									<CommentEditImage src="/src/assets/editimage.png"/>
									<CommentDeleteImage src="/src/assets/deleteimage.png"/>
								</CommentEditDeleteArea>
							)}
						</CommentUserNameArea>
						<CommentContentArea>
							<CommentContent>{comment.content}</CommentContent>
							<CommentLikeImage src={like[index] ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"} onClick={() => handleLike(index)} />
						</CommentContentArea>
						<CommentCreateRecommentArea>
							<div>
								<CommentCreateRecomment onClick={() => toggleReplyInput(index)}>답글달기</CommentCreateRecomment>
							</div>
							<CommentNumberOfLikes>{likeCount[index]}</CommentNumberOfLikes>
						</CommentCreateRecommentArea>
						{isReplyInputOpen[index] && (
							<ReplyWrapper>
								<ReplyInput
									type="text"
									placeholder={placeholder}
									onFocus={() => setPlaceholder("")}
									onBlur={() => setPlaceholder("답글을 입력하세요")}
								/>
								<ReplyBtns>
									<ReplyButton>답글달기</ReplyButton>
									<CancelButton onClick={() => toggleReplyInput(index)}>취소</CancelButton>
								</ReplyBtns>
							</ReplyWrapper>
						)}
						<RecommentArea>
							<RecommentLine />
							<MoreRecomments onClick={() => toggleRecomment(index)}>
								{isRecommentOpen[index] ? "답글 숨기기" : "답글 더보기"}
							</MoreRecomments>
						</RecommentArea>
						<AnimatedRecomment isOpen={isRecommentOpen[index]}>
							<Recomment />
						</AnimatedRecomment>
					</CommentUserDetail>
				</CommentArea>
			))}
		</CommentContainer>
	)
}
