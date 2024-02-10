import * as L from './VideoList.style'
import VideoItem from './VideoItem';

const VideoList = ({ videoList }) => {
  return (
    <L.ListContainer>
      {videoList.map((videoItem) => (
        <VideoItem key={videoItem.videoId} {...videoItem} />
      ))}
    </L.ListContainer>
  );
};

VideoList.defaultProps = {
  videoList: [],
};

export default VideoList;