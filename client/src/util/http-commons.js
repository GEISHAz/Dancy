import axios from "axios";

const baseURL = 'http://i10d210.p.ssafy.io:8080';

axios.defaults.withCredentials = true;

export const axiosInstance = axios.create({
	baseURL: baseURL,
	headers: {
		"Content-Type": "application/json;charset=utf-8",
	},
});


axios.defaults.withCredentials = true;

export const publicApi= axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const privateApi = axios.create({
  baseURL: baseURL,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('access_token')}`,
  },
});

privateApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('access_token');
  config.headers.Authorization = 'Bearer ' + token;

  return config;
});
