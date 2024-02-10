import styled from "styled-components";

export const EditorWrap = styled.div`
  width: 70%;
  display: flex;

`

export const CommentInput = styled.input`
font-size: 15px;
width: 90%;
color: #222222;
border: none;
border-bottom: solid #aaaaaa 1px;
padding-bottom: 10px;
padding-left: 5px;
position: relative;
background: none;
z-index: 5;

&:focus {
  outline: none;
}
`

export const EditBtn = styled.button`
  font-size: 15px;
  color: #222222;
  
`