import { privateApi, publicApi } from '../util/http-commons'
import axios from 'axios';

const baseURL = 'http://i10d210.p.ssafy.io:8080'
const url = 'follow'

export const followerData = async (nickname) => {
	try {
		const res = await privateApi.get(`/${url}/get-followers/${nickname}`)
		console.log(res) 

		const follower = res.data
		return { follower }
	} catch {
    console.error(error);
    throw error;
	}
}

export const followingData = async (nickname) => {
	try {
		const res = await privateApi.get(`/${url}/get-followings/${nickname}`)
		console.log(res)

		const following = res.data
		return { following }
	} catch {
    console.error(error);
    throw error;
	}
}