import * as I from './VideoItem.style'

const VideoItem = ({ articleTitle, thumbnailImageUrl }) => {
  return (
    <I.ItemCotainer>
      <I.ItemThumb src={thumbnailImageUrl} alt="" />
      <I.ItemTitle>{articleTitle}</I.ItemTitle>
    </I.ItemCotainer>
  )
}

VideoItem.defaultProps = {
  videoItem: [],
};

export default VideoItem;