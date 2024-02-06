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
    profileImage: null
    },
  });