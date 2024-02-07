import styled from "styled-components";

export const NotificationArea = styled.div`
  display: flex;
  align-items: center;
`

export const NotificationImage = styled.img`
  cursor: pointer;
`;

export const DropdownContainer = styled.div`
  position: absolute;
  width: 250px;
	top: 20px;
  height: 100%;
  cursor: auto;
  box-shadow: 0px 6px 6px rgba(0, 0, 0, 1);
`;


export const NotificationTitle = styled.span`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 250px;
  height: 44px;
  border: 1px solid #252525;
  border-bottom: none;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  background-color: #ffd2cb;
  font-size: 16px;
  font-weight: 600;
  color: #252525;
  font-family: 'NanumSquareRound', sans-serif;
`

export const DropdownMenuContainer = styled.div`
  margin-right: 4px;
  max-height: 260px;
  overflow-y: auto;
`;

export const DropdownMenu = styled.div`
  background-color: #FFFFFF;
  border: 1px solid #252525;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  width: 100%;
  display: relative;
  padding: 10px 0;

  ::-webkit-scrollbar {
    display : none;
  }
`;

export const DropdownItemContainer = styled.div`
  margin: 10px;
`;

export const DropdownItem = styled.div`
  display: flex;
  flex-direction: row;
  font-size: 12px;
  align-items: center;
  font-weight: 500;
  cursor: auto;
`;

export const ProfileImage = styled.img`
  border-radius: 50%;
  cursor: pointer;
  margin-right: 10px;
  width: 32px;
  height: 32px;
`;

export const NotificationContent = styled.div`
  margin-left: 10px;
`

export const UserName = styled.span`
	font-weight: 1000;
	cursor: pointer;
`;

export const NotificationText = styled.span`

`;

export const TimeStamp = styled.span`
  display: flex;
  font-size: 8px;
	margin-top: 4px;
	flex-direction: row-reverse;
	color: gray;
`;