import { privateApi, publicApi } from '../util/http-commons'

const url = 'stage'

export const postArticle = async (formData) => {
  try {
    const res = await privateApi.post(`/${url}`, formData);
    const articleInfo = res.data

    return articleInfo
  } catch (error) {
    console.error(error);
    throw error;
  }
};


export const getArticle = async (articleId) => {
  try {
    const res = await privateApi.get(`/${url}`, {'articleId': articleId});
    const articleInfo = res.data
		console.log(articleInfo)

    return articleInfo
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export const deleteArticle = async (articleId) => {
  try {
    const res = await privateApi.delete(`/${url}/${articleId}`, {'articleId': articleId});
    console.log(res)
  } catch (error) {
    console.error(error)
    throw error;
  }
}