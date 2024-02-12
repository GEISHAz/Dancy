import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

// const { persistAtom } = recoilPersist();
const { persistAtom } = recoilPersist({
  key: "localStorage", // 고유한 key 값
  storage: localStorage,
})

export const practiceState = atom({
	key: 'practiceState',
	default: {
    avgAccuracy : '',
    errorList : [],
	},
	effects_UNSTABLE: [persistAtom],     // 새로 고침이나 페이지 이동과 같은 상황에서도 Recoil 상태를 유지하도록 지속성 부여
})