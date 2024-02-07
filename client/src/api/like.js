import { privateApi, publicApi } from '../util/http-commons'

const url = 'like'

export const articleLike = async (articleId) => {
	try {
		const res = await privateApi.post(`/${url}/article-like/${articleId}`)

		console.log(res)
	} catch (error) {
    console.error(error);
    throw error;
	}
}