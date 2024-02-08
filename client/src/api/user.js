import { privateApi, publicApi } from '../util/http-commons'

const url = 'user'

export const userDetails = async () => {
  try {
    const res = await privateApi.get(`/${url}/details`);
    //console.log("res", res);
    const userInfo = { 
      email: res.data.email,
      nickname: res.data.nickname,
      birthDate: res.data.birthDate,
      introduceText: res.data.introduceText,
      profileImageUrl: res.data.profileImageUrl,
      gender: res.data.gender
    }

    console.log("userInfo", userInfo);

    return { userInfo }
  } catch (error) {
    console.error(error);
    throw error;
  }
};