import { authtokenApi } from "../util/http-commons"
import { loginState } from '../recoil/LoginState';
import { useSetRecoilState } from 'recoil';

const url = 'auth';

export const deleteUser = async (password, setLoginState) => {
  try {
    const res = await authtokenApi.put(`/${url}/delete`, 
      {
        'password': password
      }
    );
    localStorage.removeItem("token")
    setLoginState(false)
    return res;
  } catch (error) {
    console.error(error);
    throw error;
  }
}