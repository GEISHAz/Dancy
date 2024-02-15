import axios from 'axios';
import { privateApi, publicApi } from '../util/http-commons'

const baseURL = 'http://i10d210.p.ssafy.io:8080';
const url = 'video'

export const getRef = async () => {
  try {
    const res = await privateApi.get(`/${url}`, {params:{'limit': 100}});
    const refVideos = res.data

    return refVideos
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const uploadMine = async (formData) => {
	console.log(formData)
  try {
    const res = await axios.post(`/${url}/upload/practice`, formData,
		{
			baseURL: baseURL,
			headers: {
				'Content-Type': 'multipart/form-data',
				'AUTH-TOKEN': `${localStorage.getItem('token')}`,
			},
		});
    const myVideo = res.data

    return myVideo
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const uploadRef = async (formData) => {
  try {
    const res = await axios.post(`/${url}/upload/reference`, formData,
		{
			baseURL: baseURL,
			headers: {
				'Content-Type': 'multipart/form-data',
				'AUTH-TOKEN': `${localStorage.getItem('token')}`,
			},
		});
    const refVideo = res.data

    return refVideo
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const plzAnalyze = async (formData) => {
	console.log(formData)
  try {
    const res = await privateApi.post(`/${url}/analyze`, formData);
    const plzTrans = res.data

    console.log(plzTrans)
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const analyzeResult = async (videoId) => {
  try {
    const res = await privateApi.get(`/${url}/after/${videoId}`);
    const resultInfo = res.data

    return resultInfo
  } catch (error) {
    console.error(error);
    throw error;
  }
};