import * as C from './Reply.style'
import React, { useState, useRef, useEffect } from "react";
import { postComment } from "../../api/comment";

export const Reply = ({ articleId, commentId }) => {
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");
	const replyInput = useRef();
	const [recomments, setRecomments] = useState([]);
  const [commentData, setCommentData] = useState({
    "content": "",
    "parentId": commentId,
  });

  useEffect(() => {
    replyInput.current.focus()
  }, [])
	
	const handleReplyChange = (e) => {
    setCommentData({ ...commentData, [e.target.name]: e.target.value });
  };

	const handlePost = () => {
		if (commentData.content.length < 1) {
			replyInput.current.focus();
			return;
		}
    
		postComment({ articleId, commentData })
		.then ((res) => {
			setRecomments([...recomments, res])
			setCommentData({
				content: "",
				parentId: commentId,
			})
		})
		.catch ((err) => {
			console.error(err)
		})
	}

  const handleEnter = (e) => {
    if (e.key === "Enter") {
      handlePost(e);
    }
  };

	return (
		<C.ReplyWrapper>
			<C.ReplyInput
				ref={replyInput}
				name="content"
				type="text"
				value={commentData.content}
				autoComplete='off'
				onChange={(e) => handleReplyChange(e)}
				placeholder={placeholder}
				onFocus={() => setPlaceholder("")}
				onBlur={() => setPlaceholder("답글을 입력하세요")}
        onKeyDown={handleEnter}
			/>
			<C.ReplyBtns>
				<C.ReplyButton onClick={handlePost}>답글달기</C.ReplyButton>
				<C.CancelButton onClick={() => toggleReplyInput(index)}>취소</C.CancelButton>
			</C.ReplyBtns>
		</C.ReplyWrapper>
	)
}