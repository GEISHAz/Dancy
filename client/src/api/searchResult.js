import { authtokenApi } from "../util/http-commons";

const url = 'search';

export const searchResultList = async (keyword) => {
  try {
    const res = await authtokenApi.get(`/${url}/title/${keyword}`, {
      params: { 'limit': 20 }
    });
    const searchResultList = res.data;
		console.log(searchResultList)
    return searchResultList;
  } catch (error) {
    console.error(error);
    throw error;
  }
};