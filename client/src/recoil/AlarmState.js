import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
    key: "localStorage", // 고유한 key 값
    storage: localStorage,
});

// 알림이 왔는지 여부에 대해서만 설정해준다. 
export const alarmOccuredState = atom({
    key: "alarmOccuredState",
    default: false,
    effects_UNSTABLE: [persistAtom],
});

// 이전 알림 리스트 기억해야할듯?
export const alarmListState = atom({
    key: "alarmListState",
    default: [],
    effects_UNSTABLE: [persistAtom],
});


// 영상이 변환 완료 되었는지
export const convertAlarmState = atom({
    key: "convertAlarmState",
    default: false, // 영상이 여러개일땐 숫자로 변경해줘야함
    effects_UNSTABLE: [persistAtom],
});


//  영상이 변환 시작 되었는지 (create 페이지에서 true로 바꿔줘야함)
export const startToConvertState = atom({
    key: "startToConvertState",
    default: false, 
    effects_UNSTABLE: [persistAtom],
});