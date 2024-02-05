import { privateApi, publicApi } from '../util/http-commons'
import axios from 'axios';

const baseURL = 'http://i10d210.p.ssafy.io:8080'
const url = 'auth'

export const login = async (formData) => {
  try {
    const response = await axios.post(`${baseURL}/${url}/login`, formData);
    const token = response.data.accessToken;
		
    localStorage.setItem("token", token);

    return { token };

  } catch (error) {
		console.log(publicApi)
    console.error("로그인 에러:", error);
    throw error;
  }
};