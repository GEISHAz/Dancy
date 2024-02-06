import * as F from './FollowItem.style'

export const FollowItem = ({ profileImageUrl, nickname, following, key }) => {
  console.log(profileImageUrl, nickname, following, key)
  return (
    <F.Profile>
      <div className='flex items-center gap-x-2'>
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