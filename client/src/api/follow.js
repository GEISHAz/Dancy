import { privateApi, publicApi } from '../util/http-commons'

const url = 'follow'

export const followerData = async (nickname) => {
	try {
		const res = await privateApi.get(`/${url}/get-followers/${nickname}`)
		const follower = res.data
		return { follower }
	} catch (error) {
    console.error(error);
    throw error;
	}
}

export const followingData = async (nickname) => {
	try {
		const res = await privateApi.get(`/${url}/get-followings/${nickname}`)
		const following = res.data
		return { following }
	} catch (error) {
    console.error(error);
    throw error;
	}
}

export const followRequest = async (nickname) => {
	try {
		const res = await privateApi.post(`/${url}/request-follow`, {"nickname": nickname})
		const follower = res.data
		return { follower }
	} catch (error) {
		console.error(error);
    throw error;
	}
}

export const unFollowRequest = async (nickname) => {
	try {
		const res = await privateApi.post(`/${url}/request-unfollow`, {"nickname": nickname})
		const follower = res.data
		return { follower }
	} catch (error) {
		console.error(error);
    throw error;
	}
}