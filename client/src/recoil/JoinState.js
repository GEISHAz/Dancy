import { atom } from 'recoil';
// 회원가입 관련 데이터들을 저장합니다.

export const joinState = atom({
    key: 'joinState',
    default: {
      email: '',
      password: '',
      checkpassword: '',
      birthdate: '',
      gender: '',
    nickname: '',
    profileImageUrl: null
    },
});
  
// 사진 임시 저장 용 전역 관리