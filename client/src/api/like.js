import { privateApi, publicApi } from '../util/http-commons'

const url = 'like'

export const articleLike = async (articleId) => {
	try {
		const res = await privateApi.post(`/${url}/article-like/${articleId}`)
		const likeInfo = res.data
		
		return likeInfo
	} catch (error) {
    console.error(error);
    throw error;
	}
}

export const likeUsers = async (articleId) => {
	try {
		const res = await privateApi.get(`/${url}/who-like/${articleId}`)
    const likeUser = res.data
		
    return likeUser
	} catch (error) {
    console.error(error);
    throw error;
	}
}