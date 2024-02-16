import { privateApi, publicApi, profileImgApi } from '../util/http-commons'

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
      gender: res.data.gender,
    }

    return userInfo
  }
  catch (error) {
    console.error(error);
    throw error;
  }
};

export const userChangeNickName = async (nickname) => {
  try {
    const res = await privateApi.put(`/${url}/nickname`,
    {
      "nickname" : `${nickname}`
    }
    );
    console.log("res", res);
    // 응답 상태 코드 확인
    const statusCode = res.status;
    console.log(statusCode);
    return statusCode;
  } catch (error) {
    console.log("error:", error.response.status)
    console.error("닉네임 변경 응답에 에러가 발생함", error);
    throw error.response.status;
  }
};

export const userChangeIntro = async (introduceText) => {
  try {
    const res = await privateApi.put(`/${url}/introduce`,
    {
      "introduceText" : `${introduceText}`
    }
    );
    console.log("res", res);
    // 응답 상태 코드 확인
    const statusCode = res.status;
    console.log(statusCode);
    return statusCode;
  } catch (error) {
    console.log("error:", error.response.status)
    console.error("상태메세지 변경 응답에 에러가 발생함", error);
    throw error.response.status;
  }
};


export const userChangeImg = async (img) => {
  try {
    const response = await profileImgApi.put(`/${url}/profile_image`,
        img
    );
    console.log("res", response);
    // 응답 상태 코드 확인
    const statusCode = response.status;
    console.log(statusCode);
    return response.data;
  } catch (error) {
    console.log("error:", error.response.status)
    console.error("프로필사진 변경 응답에 에러가 발생함", error);
    throw error.response.status;
  }
};
