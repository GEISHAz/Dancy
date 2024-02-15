import styled from "styled-components";
import thumImg from '../../../assets/profileFeed/archiveThumb.png'

export const ItemCotainer = styled.div`
  display: flex;
  flex-direction: column;
  row-gap: 10px;
  width: 200px;
  height: 178px;
`

export const ItemThumb = styled.div`
  width: 200px;
  height: 150px;
  border: 1px solid black;
  border-radius: 10px;
  /* background-image: url(${(props) => { props.src}}); */
  background-image: url(${props => props.src});
  background-size: 200px;
`
export const ItemTitleDiv = styled.div`
  width : 190px;
  text-align: left;
  overflow: hidden; // 내용이 넘칠 경우 숨김 처리
  white-space: nowrap; // 텍스트를 한 줄로 표시
  text-overflow: ellipsis; // 넘치는 텍스트를 "..."로 표시
`;

export const ItemTitle = styled.span`
  font-family: 'NYJ Gothic B';
  font-size: 16px;
  margin-left: 5px;
`
