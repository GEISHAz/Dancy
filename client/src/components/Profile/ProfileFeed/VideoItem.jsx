import * as I from './VideoItem.style'

const VideoItem = ({ articleTitle, articleThumbnail }) => {
  return (
    <I.ItemCotainer>
      {/* <I.ItemThumb src={thumbnailImageUrl} alt="" /> */}
      <I.ItemThumb src={articleThumbnail} />
      <I.ItemTitle>{articleTitle}</I.ItemTitle>
    </I.ItemCotainer>
  )
}

VideoItem.defaultProps = {
  videoItem: [],
};

export default VideoItem;