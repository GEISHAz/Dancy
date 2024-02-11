import { authtokenApi } from "../util/http-commons"

const url = 'auth';

export const deleteUser = async (password) => {
  try {
    const res = await authtokenApi.put(`/${url}/delete`, 
      {
        'password': password
      }
    );
    return res;
  } catch (error) {
    console.error(error);
    throw error;
  }
}