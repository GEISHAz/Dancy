import React, { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import * as R from "./Recomment.style.jsx";
import { DropdownToggle } from "./Comment.style.jsx";
import { getComment, deleteComment } from "../../api/comment";
import UpdateRecomment from './UpdateRecomment.jsx'
import { commentLike } from "../../api/like.js";
import { useRecoilValue } from 'recoil';
import { userState } from '../../recoil/LoginState.js';

export default function Recomment({ commentId }) {
  const [like, setLike] = useState([]);
  const [likeCount, setLikeCount] = useState([]);
  const [dropdownOpen, setDropdownOpen] = useState([]);
  const [recomments, setRecomments] = useState([]);
  const [isUpdate, setIsUpdate] = useState([]);
  const parentId = commentId
	const user = useRecoilValue(userState)              // 로그인 한 유저 정보

  const state = useLocation();
  const articleId = Number(state.pathname.split("/")[2]);

  // const getTimeDifference = (createdDateArray) => {
  //   // 배열 해체하여 Date 객체로 변환
  //   const [year, month, day, hours, minutes, seconds] = createdDateArray;
  //   // Date 함수는 0월부터 시작하기 때문에 -1을 해줘야하고, 이렇게 해줬을 때 작성하게 되면 -1초가 뜸. 그래서 seconds에 -2를 줌
  //   const createdDate = new Date(year, month - 1, day, hours, minutes, seconds - 2);
  //   const diff = new Date() - createdDate;
  //   const secondsDiff = Math.floor(diff / 1000);
  //   const minutesDiff = Math.floor(secondsDiff / 60);
  //   const hoursDiff = Math.floor(minutesDiff / 60);
  //   const daysDiff = Math.floor(hoursDiff / 24);
  //   const weeksDiff = Math.floor(daysDiff / 7);

  //   if (weeksDiff > 0) {
  //     return `${weeksDiff}주 전`;
  //   } else if (daysDiff > 0) {
  //     return `${daysDiff}일 전`;
  //   } else if (hoursDiff > 0) {
  //     return `${hoursDiff}시간 전`;
  //   } else if (minutesDiff > 0) {
  //     return `${minutesDiff}분 전`;
  //   } else {
  //     return `${secondsDiff}초 전`;
  //   }
  // };



  useEffect(() => {
    getComment(articleId, parentId)
    .then((res) => {
      setRecomments(res)
      // console.log('recom', res)
      return res
    })
    .then ((res) => {
      const initialLikeState = Array(res.length).fill(false);
      setLike(initialLikeState);
      setLikeCount(initialLikeState);
      setDropdownOpen(initialLikeState);
      setIsUpdate(initialLikeState);
    })
    .catch((err) => console.error(err));
  }, [recomments]);

  // 대댓글 비동기 삭제
  const handleDelete = (commentId) => { 
    deleteComment(commentId)
    .then(() => {
      setRecomments(recomments.filter(comment => comment.commentId !== commentId));
    })
    .catch ((error) => {
      console.error(error)
    })
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

  const toggleDropdown = (index) => {
    setDropdownOpen(
      dropdownOpen.map((state, i) => (i === index ? !state : state))
    );
    // console.log(dropdownOpen);
  };

  return (
    <R.RecommentContainer>
      {recomments.map((recomment, index) => (
        <R.RecommentArea key={index}>
          <R.RecommentUserProfileImage src={recomment.authorProfileImageUrl} />
          <R.RecommentUserDetail>
            <R.RecommentUserNameArea>
              <Link to={`/profile/${recomment.authorNickname}`}>
                <R.RecommentUserName>{recomment.authorNickname}</R.RecommentUserName>
              </Link>
              { recomment.authorNickname === user.nickname ? 
                <DropdownToggle onClick={() => toggleDropdown(index)}>
                  ⋮
                </DropdownToggle> : null
              }
              {dropdownOpen[index] && (
                <R.RecommentEditDeleteArea>
                  <R.RecommentEditImage onClick={() => toggleUpdateInput(index)} src="/src/assets/editimage.png" />
                  <R.RecommentDeleteImage onClick={() => handleDelete(recomment.commentId)} src="/src/assets/deleteimage.png" />
                </R.RecommentEditDeleteArea>
              )}
            </R.RecommentUserNameArea>
            <R.RecommentContentArea>
              {isUpdate[index] ? <UpdateRecomment commentId={recomment.commentId} commentData={recomment.content} /> :
              <R.RecommentContent>{recomment.content}</R.RecommentContent>}
              <R.RecommentLikeImage
                src={
                  recomment.commentLike
                    ? "/src/assets/likeimage.png"
                    : "/src/assets/unlikeimage.png"
                }
                onClick={() => handleLike(recomment.commentId)}
              />
            </R.RecommentContentArea>
            <R.RecommentCreateArea>
              <div>
                <R.RecommentCreatedAt>
                  {/* {getTimeDifference(recomment.createdDate)} */}
                  {`${recomment.createdDate[0]}. ${recomment.createdDate[1]}. ${recomment.createdDate[2]}.`} &nbsp;
                </R.RecommentCreatedAt>
              </div>
              <R.RecommentNumberOfLikes>
                {likeCount[index]}
              </R.RecommentNumberOfLikes>
            </R.RecommentCreateArea>
          </R.RecommentUserDetail>
        </R.RecommentArea>
      ))}
    </R.RecommentContainer>
  );
}
