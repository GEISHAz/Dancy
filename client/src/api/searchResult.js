// searchResult.js
import { authtokenApi } from "../util/http-commons";

const url = 'search';

export const searchResultListByTitle = async (keyword) => {
  try {
    const res = await authtokenApi.get(`/${url}/title/${keyword}`, {
      params: { 'limit': 20 }
    });
    const searchResultList = res.data;
    return searchResultList;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const searchResultListByNickname = async (keyword) => {
  try {
    const res = await authtokenApi.get(`/${url}/nickname/${keyword}`, {
      params: { 'limit': 20 }
    });
    const searchResultList = res.data;
    return searchResultList;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
