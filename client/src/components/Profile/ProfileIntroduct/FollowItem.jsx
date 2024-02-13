import * as F from './FollowItem.style'
import { useNavigate } from 'react-router-dom';

export const FollowItem = ({ profileImageUrl, nickname, following, key, getData }) => {
  const data = false;
  const navigate = useNavigate();

	const postData = () => {
		getData(data)
    navigate(`/profile/${nickname}`)
    window.location.reload();
	}
  console.log(profileImageUrl, nickname, following, key)


  return (
    <F.Profile>
      <div className='flex items-center gap-x-2' onClick={postData} >
        <F.ProfileImg src={profileImageUrl}/>
        <F.NickName>{nickname}</F.NickName>
      </div>
      <F.FollowBtn isFollow={following}>
        {following ? '팔로잉' : '팔로우'}
      </F.FollowBtn>
    </F.Profile>
  )
}

export default FollowItem;