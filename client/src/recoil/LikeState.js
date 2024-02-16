import { atom, selector } from 'recoil';

export const commentLikeState = atom({
  key: 'likeState',
  default: {},
});
