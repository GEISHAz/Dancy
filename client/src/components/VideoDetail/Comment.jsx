import React, { useState, useEffect, useRef } from "react";
import { Link, useLocation } from "react-router-dom";
import Recomment from "./Recomment";
import * as C from "./Comment.style";
import { deleteComment, getComment, postComment } from "../../api/comment";
import { Reply } from "./Reply";
import UpdateComment from "./UpdateComment";
import { commentLike } from "../../api/like.js";
import { useRecoilValue } from 'recoil';
import { userState } from '../../recoil/LoginState.js';

// const getTimeDifference = (prevDate) => {
//   const diff = new Date() - prevDate;
//   const seconds = Math.floor(diff / 1000);
//   const minutes = Math.floor(seconds / 60);
//   const hours = Math.floor(minutes / 60);
//   const days = Math.floor(hours / 24);
//   const weeks = Math.floor(days / 7);
//   if (weeks > 0) {
//     return `${weeks}주 전`;
//   } else if (days > 0) {
//     return `${days}일 전`;
//   } else if (hours > 0) {
//     return `${hours}시간 전`;
//   } else if (minutes > 0) {
//     return `${minutes}분 전`;
//   } else {
//     return `${seconds}초 전`;
//   }
// };

export default function Comment() {
  const [comments, setComments] = useState([]);
  const [like, setLike] = useState([]);
  const [likeCount, setLikeCount] = useState([]);
  const [isRecommentOpen, setIsRecommentOpen] = useState([]);
  const [isReplyInputOpen, setIsReplyInputOpen] = useState([]);
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");
  const [dropdownOpen, setDropdownOpen] = useState([]);
  const [isUpdate, setIsUpdate] = useState([]);
	const user = useRecoilValue(userState)              // 로그인 한 유저 정보

  const state = useLocation();
  const articleId = Number(state.pathname.split("/")[2]);

  useEffect(() => {
    getComment(articleId, 0)
    .then((res) => {
      setComments(res)
      return res
    })
    .then((res) => {
      const initialLikeState = Array(res.length).fill(false);
      setLike(initialLikeState);
      setLikeCount(initialLikeState);
      setIsRecommentOpen(initialLikeState);
      setIsReplyInputOpen(initialLikeState);
      setDropdownOpen(initialLikeState);
      setIsUpdate(initialLikeState)
    })
    .catch((err) => console.error(err));
  }, []);

  const commentInput = useRef();
  const [commentData, setCommentData] = useState({
    content: "",
    parentId: 0,
  });

  const handleCommentChange = (e) => {
    setCommentData({ ...commentData, [e.target.name]: e.target.value });
  };

  const handlePost = () => {
    if (commentData.content.length < 1) {
      commentInput.current.focus();
      return;
    }

    postComment({ articleId, commentData })
      .then((res) => {
        setCommentData({
          content: "",
          parentId: 0,
        });
        window.location.reload();
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleDelete = (commentId) => { 
    deleteComment(commentId)
    window.location.reload()
  }

  // 엔터 키 누르면 제출되도록
  const handleEnter = (e) => {
    if (e.key === "Enter") {
      handlePost(e);
    }
  };

  const handleLike = (commentId) => {
    commentLike(commentId)
    .then(() => window.location.reload())
    .catch((err) => console.error(err))
  };

  const toggleUpdateInput = (index) => {
    setIsUpdate(
      isUpdate.map((state, i) => (i === index ? !state : state))
    );
  }

  const toggleRecomment = (index) => {
    setIsRecommentOpen(
      isRecommentOpen.map((state, i) => (i === index ? !state : state))
    );
  };

  const toggleReplyInput = (index) => {
    setIsReplyInputOpen(
      isReplyInputOpen.map((state, i) => (i === index ? !state : state))
    );
  };

  const toggleDropdown = (index) => {
    setDropdownOpen(
      dropdownOpen.map((state, i) => (i === index ? !state : state))
    );
  };

  return (
    <C.CommentContainer>
      <C.CommentTitleArea>
        <C.CommentTitle>댓글</C.CommentTitle>
        <C.CommentTitleLine />
      </C.CommentTitleArea>
      <C.CommentInputWrapper>
        <C.CommentInput
          ref={commentInput}
          name="content"
          type="text"
          value={commentData.content}
          onChange={handleCommentChange}
          placeholder={placeholder}
          onFocus={() => setPlaceholder("")}
          onBlur={() => setPlaceholder("댓글을 입력하세요")}
        onKeyDown={handleEnter}
        />
        <C.CommentBtns>
          <C.CommentButton onClick={handlePost}>댓글 작성</C.CommentButton>
          <C.CommentCancelButton>취소</C.CommentCancelButton>
        </C.CommentBtns>
      </C.CommentInputWrapper>
      {comments.map((comment, index) => (
        <C.CommentArea key={index}>
          <Link to={`/profile/${comment.authorNickname}`}>
            <C.CommentUserProfileImage src={comment.authorProfileImageUrl} />
          </Link>
          <C.CommentUserDetail>
            <C.CommentUserNameArea>
              <div>
                <Link to={`/profile/${comment.authorNickname}`}>
                  <C.CommentUserName>
                    {comment.authorNickname}
                  </C.CommentUserName>
                </Link>
                <C.CommentCreatedAt>
                  {/* {getTimeDifference(comment.createdDate)} */}
                  {`${comment.createdDate[0]}. ${comment.createdDate[1]}. ${comment.createdDate[2]}.`} &nbsp;
                </C.CommentCreatedAt>
              </div>
              { comment.authorNickname === user.nickname ? 
                <C.DropdownToggle onClick={() => toggleDropdown(index)}>
                  ⋮
                </C.DropdownToggle> : null
              }
              {dropdownOpen[index] && (
                <C.CommentEditDeleteArea>
                  <C.CommentEditImage onClick={() => toggleUpdateInput(index)} src="/src/assets/editimage.png" />
                  <C.CommentDeleteImage onClick={() => handleDelete(comment.commentId)} src="/src/assets/deleteimage.png" />
                </C.CommentEditDeleteArea>
              )}
            </C.CommentUserNameArea>
            <C.CommentContentArea>
              {isUpdate[index] ? <UpdateComment commentId={comment.commentId} commentData={comment.content} /> :
              <C.CommentContent>{comment.content}</C.CommentContent>}
              <C.CommentLikeImage
                src={
                  comment.commentLike
                    ? "/src/assets/likeimage.png"
                    : "/src/assets/unlikeimage.png"
                }
                onClick={() => handleLike(comment.commentId)}
              />
            </C.CommentContentArea>
            <C.CommentCreateRecommentArea>
              <div>
                <C.CommentCreateRecomment onClick={() => toggleReplyInput(index)}>
                  답글달기
                </C.CommentCreateRecomment>
              </div>
              <C.CommentNumberOfLikes>
                {likeCount[index]}
              </C.CommentNumberOfLikes>
            </C.CommentCreateRecommentArea>
            {isReplyInputOpen[index] && (
              <Reply articleId={articleId} commentId={comment.commentId} />
            )}
            <C.RecommentArea>
              <C.RecommentLine />
              <C.MoreRecomments onClick={() => toggleRecomment(index)}>
                {isRecommentOpen[index] ? "답글 숨기기" : "답글 더보기"}
              </C.MoreRecomments>
            </C.RecommentArea>
            <C.AnimatedRecomment isOpen={isRecommentOpen[index]}>
              {isRecommentOpen[index] && <Recomment commentId={comment.commentId} />}
            </C.AnimatedRecomment>
          </C.CommentUserDetail>
        </C.CommentArea>
      ))}
    </C.CommentContainer>
  );
}
