import * as C from './Reply.style'
import React, { useState, useRef } from "react";
import { postComment } from "../../api/comment";

export const Reply = ({ articleId, commentId }) => {
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");

	const replyInput = useRef();
  const [commentData, setCommentData] = useState({
    "content": "",
    "parentId": commentId,
  });
	
	const handleReplyChange = (e) => {
    setCommentData({ ...commentData, [e.target.name]: e.target.value });
		console.log(commentData)
  };

	const handlePost = () => {
		if (commentData.content.length < 1) {
			replyInput.current.focus();
			return;
		}
    
    console.log(articleId, commentData)
		postComment({ articleId, commentData })
		.then ((res) => {
			console.log(res)
			setCommentData({
				content: "",
				parentId: commentId,
			})
			window.location.reload()
		})
		.catch ((err) => {
			console.error(err)
		})
	}

	return (
		<C.ReplyWrapper>
			<C.ReplyInput
				ref={replyInput}
				name="content"
				type="text"
				value={commentData.content}
				onChange={(e) => handleReplyChange(e)}
				placeholder={placeholder}
				onFocus={() => setPlaceholder("")}
				onBlur={() => setPlaceholder("답글을 입력하세요")}
			/>
			<C.ReplyBtns>
				<C.ReplyButton onClick={handlePost}>답글달기</C.ReplyButton>
				<C.CancelButton onClick={() => toggleReplyInput(index)}>취소</C.CancelButton>
			</C.ReplyBtns>
		</C.ReplyWrapper>
	)
}