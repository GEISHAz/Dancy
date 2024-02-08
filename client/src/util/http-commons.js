import axios from "axios";

const baseURL = 'http://i10d210.p.ssafy.io:8080';

axios.defaults.withCredentials = true;

export const noneApi = axios.create({
  baseURL: baseURL,
});

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
    'AUTH-TOKEN': `${localStorage.getItem('token')}`,
  },
});

privateApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['AUTH-TOKEN'] = `${token}`;
  }
  return config;
});

export const joinApi = axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'multipart/form-data',
  }
});

export const textApi = axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'text/plain',
  }
});

export const profileImgApi = axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'multipart/form-data',
    'AUTH-TOKEN': `${localStorage.getItem('token')}`,
  },
});

profileImgApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['AUTH-TOKEN'] = `${token}`;
  }
  return config;
});
