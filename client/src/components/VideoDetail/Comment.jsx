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


export default function Comment() {
  const [comments, setComments] = useState([]);
  const [like, setLike] = useState([]);
  const [likeCount, setLikeCount] = useState([]);
  const [likeState, setLikeState] = useState([]);
  const [isRecommentOpen, setIsRecommentOpen] = useState([]);
  const [isReplyInputOpen, setIsReplyInputOpen] = useState([]);
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [isUpdate, setIsUpdate] = useState([]);
  const user = useRecoilValue(userState)              // 로그인 한 유저 정보
  const state = useLocation();
  const articleId = Number(state.pathname.split("/")[2]);

  const getTimeDifference = (createdDateArray) => {
    // 배열 해체하여 Date 객체로 변환
    const [year, month, day, hours, minutes, seconds] = createdDateArray;
    // Date 함수는 0월부터 시작하기 때문에 -1을 해줘야하고, 이렇게 해줬을 때 작성하게 되면 -1초가 뜸. 그래서 seconds에 -2를 줌
    const createdDate = new Date(year, month - 1, day, hours, minutes, seconds - 2);
    const diff = new Date() - createdDate;
    const secondsDiff = Math.floor(diff / 1000);
    const minutesDiff = Math.floor(secondsDiff / 60);
    const hoursDiff = Math.floor(minutesDiff / 60);
    const daysDiff = Math.floor(hoursDiff / 24);
    const weeksDiff = Math.floor(daysDiff / 7);

    if (weeksDiff > 0) {
      return `${weeksDiff}주 전`;
    } else if (daysDiff > 0) {
      return `${daysDiff}일 전`;
    } else if (hoursDiff > 0) {
      return `${hoursDiff}시간 전`;
    } else if (minutesDiff > 0) {
      return `${minutesDiff}분 전`;
    } else {
      return `${secondsDiff}초 전`;
    }
  };

  useEffect(() => {
    getComment(articleId, 0)
    .then((res) => {
      setComments(res)
      // console.log(res)
      return res
    })
    .then((res) => {
      const initialLikeState = Array(res.length).fill(false);
      setLike(initialLikeState);
      setLikeCount(initialLikeState);
      setIsRecommentOpen(initialLikeState);
      setIsReplyInputOpen(initialLikeState);
      setDropdownOpen(initialLikeState);
      setIsUpdate(initialLikeState);
    })
    .catch((err) => console.error(err));
  }, [articleId]);

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
        setComments([...comments, res])
        console.log(res)
      })

      .then(() => 
        setCommentData({
          content: "",
          parentId: 0,
        })
      )
      .catch((err) => {
        console.error(err);
      });
  };

  // 댓글 비동기 삭제
  const handleDelete = (commentId) => { 
    deleteComment(commentId)
    .then(() => {
      setComments(comments.filter(comment => comment.commentId !== commentId));
    })
    .catch ((error) => {
      console.error(error)
    })
  };

  // 엔터 키 누르면 제출되도록
  const handleEnter = (e) => {
    if (e.key === "Enter") {
      handlePost(e);
    }
  };

  // 좋아요 비동기 처리
  const handleLike = (commentId, index) => {
    commentLike(commentId)
    .then(() => {
      let newLikeState = [...likeState];
       // 해당 댓글의 좋아요 상태를 토글
      newLikeState[index] = !newLikeState[index];
      // likeState를 업데이트
      setLikeState(newLikeState); 
    })
    .catch((err) => console.error(err))
  };
	
	const toggleUpdateInput = (index) => {
    setIsUpdate(
      isUpdate.map((state, i) => (i === index ? !state : state))
    );
    console.log(isUpdate)
  }


  const toggleRecomment = (commentId) => {
    setIsRecommentOpen((prev) => ({
      ...prev,
      [commentId]: !prev[commentId],
    }));
  };

  const toggleReplyInput = (commentId) => {
    setIsReplyInputOpen((prev) => ({
      ...prev,
      [commentId]: !prev[commentId],
    }));
  };

  const toggleDropdown = (commentId) => {
    setDropdownOpen((prev) => ({
      ...prev,
      [commentId]: !prev[commentId],
    }));
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
          autoComplete="off"
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
                  {getTimeDifference(comment.createdDate)}
                  {/* {`${comment.createdDate[0]}. ${comment.createdDate[1]}. ${comment.createdDate[2]}.`} &nbsp; */}
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
                  likeState[index]
                    ? "/src/assets/likeimage.png"
                    : "/src/assets/unlikeimage.png"
                }
                onClick={() => handleLike(comment.commentId, index)}
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
