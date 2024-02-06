import { privateApi, publicApi } from '../util/http-commons'

const url = 'auth'

export const login = async (formData) => {
  try {
    const response = await privateApi.post(`/${url}/login`, formData);
    const token = response.data.accessToken;
    localStorage.setItem("token", token);
    return { token };
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
