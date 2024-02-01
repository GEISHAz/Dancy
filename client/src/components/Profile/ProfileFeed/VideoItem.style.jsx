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
  background-image: url(${thumImg});
  background-size: 200px;
`

export const ItemTitle = styled.div`
  font-family: 'NYJ Gothic B';
  font-size: 16px;
  margin-left: 5px;
`
