import { privateApi, publicApi } from '../util/http-commons'

const url = 'mypage'

export const userInfo = async (nickname) => {
  try {
    const res = await privateApi.get(`/${url}/${nickname}`);
		const userInfo = res.data

    return userInfo
  } catch (error) {
    console.error(error);
    throw error;
  }
};


export const myArticles = async (nickname) => {
  try {
    const res = await privateApi.get(`/${url}/article/${nickname}`, { params: { 'limit': 200 } });
		console.log("res", res.data)

		const myArticles = res.data

    return myArticles
  } catch (error) {
    console.error(error);
    throw error;
  }
};


export const keepArticles = async (nickname) => {
  try {
    const res = await privateApi.get(`/${url}/keep/${nickname}`, { params: { 'limit': 200 } });
		console.log("res", res.data)

		const keepArticles = res.data

    return keepArticles
  } catch (error) {
    console.error(error);
    throw error;
  }
};


