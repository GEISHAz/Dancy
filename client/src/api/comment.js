import { privateApi, publicApi } from '../util/http-commons'

const url = 'comment'

export const getComment = async (articleId, parentId) => {
  try {
		const res = await privateApi.get(`/${url}/${articleId}`, { params: {"parentId": parentId}})
		const commentInfo = res.data
		// console.log(commentInfo)
		return commentInfo
	} catch (error) {
    console.error(error);
    throw error;
	}
}

export const postComment = async ({ articleId, commentData }) => {
	try {
		const res = await privateApi.post(`/${url}/${articleId}`, commentData)
		const comment = res.data
		
		return comment
	} catch (error) {
    console.error(error);
    throw error;
	}
}

export const deleteComment = async (commentId) => {
  console.log('del')
  try {
    const res = await privateApi.delete(`/${url}/${commentId}`, {params: {'commentId': commentId}})
    console.log(res)
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export const updateComment = async ({commentId, updateData}) => {
  try {
    const res = await privateApi.put(`/${url}/${commentId}`, updateData)
    const update = res.data
    console.log(update)
  } catch (error) {
    console.error(error);
    throw error;
  }
}