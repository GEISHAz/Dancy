import { privateApi, publicApi } from '../util/http-commons'

const url = 'mypage'

export const userInfo = async (nickname) => {
  try {
    const res = await privateApi.get(`/${url}/${nickname}`);
		// console.log(res.data)

		const userInfo = res.data
    return { userInfo }
  } catch (error) {
    console.error(error);
    throw error;
  }
};
