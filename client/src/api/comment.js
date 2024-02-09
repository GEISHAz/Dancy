import { privateApi, publicApi } from '../util/http-commons'

const url = 'comment'

export const postComment = async ({ articleId, commentData }) => {
  console.log(articleId, commentData)
	try {
		const res = await privateApi.post(`/${url}/${articleId}`, commentData)
		const comment = res.data
		
		return comment
	} catch (error) {
    console.error(error);
    throw error;
	}
}