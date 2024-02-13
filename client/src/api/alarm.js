import { privateApi, publicApi } from '../util/http-commons'

const url = 'notification'

export const allArticles = async () => {
  try {
    const res = await privateApi.get(`/${url}`,   {params:{'limit': 10}} );
    const allAlarms = res.data

      return allAlarms;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
