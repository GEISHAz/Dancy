import React, { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import * as R from "./Recomment.style.jsx";
import { DropdownToggle } from "./Comment.style.jsx";
import { getComment, deleteComment } from "../../api/comment";

export default function Recomment({ commentId }) {
  const [like, setLike] = useState([]);
  const [likeCount, setLikeCount] = useState([]);
  const [dropdownOpen, setDropdownOpen] = useState([]);
  const [recomments, setRecomments] = useState([])
  const parentId = commentId

  const state = useLocation();
  const articleId = Number(state.pathname.split("/")[2]);

  useEffect(() => {
    getComment(articleId, parentId)
    .then((res) => {
      setRecomments(res)
      return res
    })
    .then ((res) => {
      const initialLikeState = Array(res.length).fill(false);
      setLike(initialLikeState);
      setLikeCount(initialLikeState);
      setDropdownOpen(initialLikeState)
    })
    .catch((err) => console.log(err));
  }, []);

  const handleDelete = (commentId) => {
    console.log(commentId)
    deleteComment(commentId)
    window.location.reload()
  }

  const handleLike = (index) => {

  };

  const toggleDropdown = (index) => {
    setDropdownOpen(
      dropdownOpen.map((state, i) => (i === index ? !state : state))
    );
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
              <DropdownToggle onClick={() => toggleDropdown(index)}>
                ⋮
              </DropdownToggle>
              {dropdownOpen[index] && (
                <R.RecommentEditDeleteArea>
                  <R.RecommentEditImage src="/src/assets/editimage.png" />
                  <R.RecommentDeleteImage onClick={() => handleDelete(recomment.commentId)} src="/src/assets/deleteimage.png" />
                </R.RecommentEditDeleteArea>
              )}
            </R.RecommentUserNameArea>
            <R.RecommentContentArea>
              <R.RecommentContent>{recomment.content}</R.RecommentContent>
              <R.RecommentLikeImage
                src={
                  like[index]
                    ? "/src/assets/likeimage.png"
                    : "/src/assets/unlikeimage.png"
                }
                onClick={() => handleLike(index)}
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
