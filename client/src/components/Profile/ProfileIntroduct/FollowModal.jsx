import FollowItem from './FollowItem';
import * as F from './FollowModal.style'

export default function FollowModal ({ getData, info, isFollow }) {
	const data = false;
	const postData = () => {
		getData(data)
	}
	console.log(info)
	return (
		<F.Modal>
			<F.ModalBackdrop onClick={postData} />

			<F.ModalWrap>
				<F.ModalView>
          <F.Txt>{isFollow ? '팔로워' : '팔로잉'}</F.Txt>
          <hr />

          <F.FollowList>
            {info.map((item) => (
              <FollowItem key={item.key} {...item} />
            ))}
          </F.FollowList>
				</F.ModalView>
			</F.ModalWrap>
		</F.Modal>
	)
}

FollowModal.defaultProps = {
  Profile: [],
};