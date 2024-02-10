import { Link } from 'react-router-dom';
import * as I from './VideoItem.style'

const VideoItem = ({ articleTitle, articleThumbnail, articleId }) => {
  return (
    <Link to={`/detail/${articleId}`}>
      <I.ItemCotainer>
        {/* <I.ItemThumb src={thumbnailImageUrl} alt="" /> */}
        <I.ItemThumb src={articleThumbnail} />
        <I.ItemTitle>{articleTitle}</I.ItemTitle>
      </I.ItemCotainer>
    </Link>
  )
}

VideoItem.defaultProps = {
  videoItem: [],
};

export default VideoItem;