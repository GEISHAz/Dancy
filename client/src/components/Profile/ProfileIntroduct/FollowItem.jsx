import * as F from './FollowItem.style'

export const FollowItem = ({ profile, nickName, following, key }) => {
  console.log(profile, nickName, following, key)
  return (
    <F.Profile>
      <div className='flex items-center gap-x-2'>
        <F.ProfileImg src={profile}/>
        <F.NickName>{nickName}</F.NickName>
      </div>
      <F.FollowBtn isFollow={following}>
        {following ? '팔로잉' : '팔로우'}
      </F.FollowBtn>
    </F.Profile>
  )
}

export default FollowItem;