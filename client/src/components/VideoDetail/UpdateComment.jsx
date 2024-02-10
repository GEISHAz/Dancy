import * as U from './UpdateComment.style'
import { updateComment } from "../../api/comment";
import React, { useState, useRef } from "react";

export default function UpdateComment ({ commentId, commentData }) {

  const updateInput = useRef();
  const [updateData, setUpdateData] = useState({
    content: commentData,
    parentId: 0,
  });

  const handleUpdateChange = (e) => {
    setUpdateData({ ...updateData, [e.target.name]: e.target.value });
  };

  const handleUpdate = () => {
    console.log('update')
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
      // console.log(res)
			window.location.reload()
    })
    .catch ((err) => {
      console.error(err)
    })
  };

  return (
    <U.EditorWrap>
      <U.CommentInput
        ref={updateInput}
        name="content"
        type="text"
        value={updateData.content}
        onChange={handleUpdateChange}
      />
      <U.EditBtn onClick={handleUpdate}>수정</U.EditBtn>
    </U.EditorWrap>
  )
}