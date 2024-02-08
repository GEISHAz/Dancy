import React, {useState, useEffect} from "react";
import axios from 'axios';
import { Link, useParams } from "react-router-dom";
import Recomment from "./Recomment"
import { 
  CommentContainer,
  CommentTitleArea,
  CommentTitle,
  CommentTitleLine,
  CommentInputWrapper,
  CommentInput,
  CommentBtns,
  CommentButton,
  CommentCancelButton,
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
  const [comments, setComments] = useState([]);
  const [like, setLike] = useState([]);
  const [likeCount, setLikeCount] = useState([]);
  const [isRecommentOpen, setIsRecommentOpen] = useState([]);
  const [isReplyInputOpen, setIsReplyInputOpen] = useState([]);
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");
  const [dropdownOpen, setDropdownOpen] = useState([]);
	const { articleId } = useParams();

  const fetchComments = async () => {
    try {
        const response = await axios.get(`http://i10d210.p.ssafy.io:8080/comment/${articleId}`);
				setComments(response.data);
        const initialLikeState = Array(response.data.length).fill(false);
				setLike(initialLikeState);
				setLikeCount(initialLikeState);
				setIsRecommentOpen(initialLikeState);
				setIsReplyInputOpen(initialLikeState);
				setDropdownOpen(initialLikeState);
				console.log(response)
    } catch (error) {
        console.error('댓글을 가져오는데 실패했습니다.', error);
    }
  };

  useEffect(() => {
    fetchComments();
  }, []);

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
      <CommentInputWrapper>
        <CommentInput 
          type="text"
          placeholder={placeholder}
          onFocus={() => setPlaceholder("")}
          onBlur={() => setPlaceholder("답글을 입력하세요")}
        />
        <CommentBtns>
          <CommentButton>답글달기</CommentButton>
          <CommentCancelButton>취소</CommentCancelButton>
        </CommentBtns>
      </CommentInputWrapper>
      {comments.map((comment, index) => (
        <CommentArea key={index}>
          <CommentUserProfileImage />
          <CommentUserDetail>
            <CommentUserNameArea>
              <div>
                <Link to={`/profile/${comment.username}`}>
                  <CommentUserName>{comment.authorNickname}</CommentUserName>
                </Link>
                <CommentCreatedAt>{getTimeDifference(comment.createdDate)}</CommentCreatedAt>
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
              <CommentLikeImage 
                src={like[index] ? "/src/assets/likeimage.png" : "/src/assets/unlikeimage.png"}
                onClick={() => handleLike(index)} 
              />
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
