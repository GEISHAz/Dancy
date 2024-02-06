import { privateApi, publicApi } from '../util/http-commons'
import axios from 'axios';

const baseURL = 'http://i10d210.p.ssafy.io:8080'
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
