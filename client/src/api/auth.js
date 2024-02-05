import { privateApi, publicApi } from '../util/http-commons'
import axios from 'axios';

const baseURL = 'http://i10d210.p.ssafy.io:8080'
const url = 'auth'

export const login = async (formData) => {
  try {
    const response = await privateApi.post(`/${url}/login`, formData);
    const token = response.data.accessToken;
		const { email, password } = formData
		
    localStorage.setItem("token", token);

    return { token, email, password };

  } catch (error) {
    console.error("로그인 에러:", error);
    throw error;
  }
};

export const logout = async () => {
  try {
    localStorage.removeItem("token")
  } catch (error) {
    console.error("로그아웃 에러:", error);
    window.alert(error.message)
    throw error;
  }
}
