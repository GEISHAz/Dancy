import styled from "styled-components";

export const VideoPlayerContainer = styled.div`
  position: relative;
  width: 100%;
  /* width: 1000px; */
  background-color: black;
  color: white;
  border-radius: 20px;
  transition: background-color 0.3s;
	justify-content: center;
	align-items: center;
	display: flex;
  video {
    width: 100%;	
    height: 552px;
		/* object-fit: contain; */
    border-radius: 20px; 
  };
`;

export const ControlsWrapper = styled.div`
  display: flex;
	width: 100%;
  justify-content: space-between;
  background-color: rgba(128, 128, 128, 0.2);
  padding: 10px;
	border-bottom-left-radius: 20px;
	border-bottom-right-radius: 20px;
  opacity: 0;
  transition: opacity 0.3s;
  ${VideoPlayerContainer}:hover & {
    opacity: 1;
  }
`;

export const Controls = styled.div`
  display: flex;
  align-items: center;

  div {
    display: flex;
    flex-direction: row;
    align-items: center;
    gap: 10px;
		padding-left:15px;
  }
`;

export const VolumeControl = styled.div`
  position: relative;
  color: #e4b1c9;
  input[type="range"] {
    height: ${props => props.isOpen ? '100%' : '0'};
    opacity: ${props => props.isOpen ? '1' : '0'};
    transition: height 0.3s ease, opacity 0.3s ease;
  }
  &:hover input[type="range"],
  &:focus-within input[type="range"] {
    display: block;
  }
`;


export const PlayBtn = styled.button`

`;

export const VideoRightOptions = styled.div`
  display: flex;
  flex-direction: row;
  gap: 20px;
`
