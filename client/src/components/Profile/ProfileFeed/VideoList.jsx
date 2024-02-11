import * as L from './VideoList.style'
import VideoItem from './VideoItem';

const VideoList = ({ videoList }) => {
  return (
    <L.ListContainer>
      {videoList.map((videoItem, index) => (
        <VideoItem key={index} {...videoItem} />
      ))}
    </L.ListContainer>
  );
};

VideoList.defaultProps = {
  videoList: [],
};

export default VideoList;