import axios from 'axios';
import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const userState = atom({
	key: 'userState',
	default: {
		email: "",
    nickname: "",
    birthDate: "",
    introduceText: "",
    profileImageUrl: null
	},
	effects_UNSTABLE: [persistAtom],     // 새로 고침이나 페이지 이동과 같은 상황에서도 Recoil 상태를 유지하도록 지속성 부여
})

export const loginState = atom({
	key: 'loginState',
	default: localStorage.getItem("token") ? true : false,
	effects_UNSTABLE: [persistAtom],
})
