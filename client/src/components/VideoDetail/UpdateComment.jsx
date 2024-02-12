import * as U from './UpdateComment.style'
import { updateComment } from "../../api/comment";
import React, { useState, useRef, useEffect } from "react";

export default function UpdateComment ({ commentId, commentData }) {

  const updateInput = useRef();
  const [updateData, setUpdateData] = useState({
    content: commentData,
    parentId: 0,
  });

  useEffect(() => {
    updateInput.current.focus()
  }, [])

  const handleUpdateChange = (e) => {
    setUpdateData({ ...updateData, [e.target.name]: e.target.value });
  };

  const handleUpdate = () => {
    if (updateData.content.length < 1) {
      updateInput.current.focus();
      return;
    }

    if (updateData.content.length < 1) {
      updateInput.current.focus();
      return;
    }
    

    updateComment({commentId, updateData})
    .then ((res) => {
			window.location.reload()
    })
    .catch ((err) => {
      console.error(err)
    })
  };

  const handleEnter = (e) => {
    if (e.key === "Enter") {
      handleUpdate(e);
    }
  };

  return (
    <U.EditorWrap>
      <U.CommentInput
        ref={updateInput}
        name="content"
        type="text"
        value={updateData.content}
        onChange={handleUpdateChange}
        onKeyDown={handleEnter}
      />
      <U.EditBtn onClick={handleUpdate}>수정</U.EditBtn>
    </U.EditorWrap>
  )
}