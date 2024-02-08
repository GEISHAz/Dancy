import * as C from './Reply.style'
import React, { useState, useRef } from "react";

export const Reply = ({ commentId }) => {
  const [placeholder, setPlaceholder] = useState("답글을 입력하세요");

	const replyInput = useRef();
  const [replyData, setReplyData] = useState({
    "content": "",
    "parentId": "",
  });
	
	const handleReplyChange = (e, commentId) => {
    setReplyData({ [e.target.name]: e.target.value, "parentId": commentId });
		console.log(replyData)
  };

	const handlePost = () => {
		if (replyData.content.length < 1) {
			replyInput.current.focus();
			return;
		}

		postComment({ articleId, replyData })
		.then ((res) => {
			console.log(res)
			setReplyData({
				content: "",
				parentId: "",
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
				value={replyData.content}
				onChange={(e) => handleReplyChange(e, commentId)}
				placeholder={placeholder}
				onFocus={() => setPlaceholder("")}
				onBlur={() => setPlaceholder("답글을 입력하세요")}
			/>
			<C.ReplyBtns>
				<C.ReplyButton onClick={() => handlePost('reply', comment.commentId)}>답글달기</C.ReplyButton>
				<C.CancelButton onClick={() => toggleReplyInput(index)}>취소</C.CancelButton>
			</C.ReplyBtns>
		</C.ReplyWrapper>
	)
}