import styled, {keyframes, css} from "styled-components";

export const CommentContainer = styled.div`
  font-family: 'NanumSquareRound';
  width: 1000px;
  height: auto;
`;

export const CommentTitleArea = styled.div`
  display: flex;
  align-items: center;
`


export const CommentTitle = styled.div`
  margin: 20px;
  font-size: 24px;
  font-weight: 600;
  color: #252525;
`;

export const CommentTitleLine = styled.hr`
  flex: 1;
  border-color: #252525;
`;

export const CommentArea = styled.div`
  position: relative;
  display: flex;
  flex-direction: row;
  margin: 10px;
  gap: 10px;
`;

export const CommentUserProfileImage = styled.button`
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: lightgray;
  border: none;
  cursor: pointer;
`;

export const CommentUserDetail = styled.div`
  flex-grow: 1;
  margin-bottom: 10px;
`;

export const CommentUserNameArea = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 5px;
  position: relative;
  div{
    display: flex;
    flex-direction: row;
    align-items: center;
    gap: 4px;
  }
`;

export const CommentUserName = styled.div`
  font-weight: 500;
`;

export const DropdownToggle = styled.div`
  position: relative;
  cursor: pointer;
`

export const CommentEditDeleteArea = styled.div`
  position: absolute;
  top: 25px;
  right: -25px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 10px;
  z-index: 1000;
  img {
    width: 16px;
    height: 18px;
  }
`;


export const CommentEditImage = styled.img`
  cursor: pointer;
`;

export const CommentDeleteImage = styled.img`
  cursor: pointer;
  margin-left: 5px;
`;

export const CommentContentArea = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
`;

export const CommentContent = styled.div`
  font-size: 16px;
`;

export const CommentLikeImage = styled.img`
  width: 20px;
  height: 20px;
  cursor: pointer;
`;

export const CommentCreateRecommentArea = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  div{
    display: flex;
    flex-direction: row;
    gap: 8px;
    align-content: center;
  }
`;

export const CommentCreatedAt = styled.div`
  font-size: 12px;
  color: gray;
  padding-top: 4px;
`;

export const CommentCreateRecomment = styled.div`
  color: gray;
  font-size: 13px;
  cursor: pointer;
  font-weight: 600;
`;

export const CommentNumberOfLikes = styled.div`
  font-size: 12px;
  color: gray;
`;

export const RecommentArea = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4px;
`;

export const RecommentLine = styled.hr`
  flex: 0 0 50px;
  margin-top: 3px;
  border-color: #252525;
`;

export const MoreRecomments = styled.div`
  cursor: pointer;
  margin-top: 5px;
  font-size: 12px;
`;

export const Card = styled.div`
  scale: 0.9;
`;

export const slideDown = keyframes`
  0% {
    max-height: 0;
    opacity: 0;
  }
  100% {
    max-height: 500px;
    opacity: 1;
  }
`;

export const slideUp = keyframes`
  0% {
    max-height: 500px;
    opacity: 1;
  }
  100% {
    max-height: 0;
    opacity: 0;
  }
`;

export const AnimatedRecomment = styled.div`
  overflow: hidden;
  animation: ${props => props.isOpen ? slideDown : slideUp} 0.5s forwards;
  max-height: ${props => props.isOpen ? '500px' : '0'};
`;

export const ReplyWrapper = styled.div`
  position: relative;
  width: 100%;
  margin: 10px 0px;
`

export const ReplyInput = styled.input`
  font-size: 15px;
  color: #222222;
  width: 100%;
  border: none;
  border-bottom: solid #aaaaaa 1px;
  padding-bottom: 10px;
  padding-left: 10px;
  position: relative;
  background: none;
  z-index: 5;

  ::placeholder {
    color: #aaaaaa;
  }

  &:focus {
    outline: none;
  }
`;

export const ReplyButton = styled.button`
  display: block;
  font-size: 12px;
  margin-top: 10px;
  color: #252525;
  padding: 5px 10px;
  cursor: pointer;

  &:hover {
    border-radius: 20px;
    color: white;
    background-color: #e4b1c9;
  }
`;

export const CancelButton = styled.button`
  display: block;
  font-size: 12px;
  margin-top: 10px;
  color: #252525;
  padding: 5px 10px;
  cursor: pointer;
  
  &:hover {
    border-radius: 20px;
    color: white;
    background-color: #e4b1c9;
  }
`;

export const ReplyBtns = styled.div`
  display: flex;
  flex-direction: row-reverse;
  gap: 10px;
`





