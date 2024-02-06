import { noneApi, privateApi, publicApi, joinApi, textApi} from '../util/http-commons'
import axios from 'axios';

const baseURL = 'http://i10d210.p.ssafy.io:8080'


export const emailCheck = async (formData) => {
    try {
        const response = await publicApi.post(`/email/verify/send`,
            { "targetEmail": `${formData}` }
        );
     // 응답 상태 코드 확인
        const statusCode = response.status;
        console.log(statusCode);

        return statusCode;
  
    } catch (error) {
        console.log("error:", error.response.status)
        console.error("이메일 응답에 에러가 발생함", error);
      throw error.response.status;
    }
  };


export const nickNameCheck = async (formData) => {
  console.log(formData);
  try{
    const response = await noneApi.get(`/user/exists/${formData}`);
    console.log("response",response);
     // 응답 상태 코드 확인
     const statusCode = response.status;
     console.log(statusCode);

     return statusCode;

  }catch(error){
    console.log("error:", error.response.status)
    console.error("닉네임 응답에 에러가 발생함", error);
    throw error.response.status;
  }
}

  
  
  