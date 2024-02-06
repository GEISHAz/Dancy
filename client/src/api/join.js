import { privateApi, publicApi, joinApi, textApi} from '../util/http-commons'
import axios from 'axios';
import { httpStatusCode } from '../util/http-status';

const baseURL = 'http://i10d210.p.ssafy.io:8080'


export const emailCheck = async (formData) => {
    try {
    const response = await textApi.post(`/email/verify/send`, formData);
     // 응답 상태 코드 확인
     const statusCode = response.status;

     // 상태 코드에 따라 다른 처리 수행
     if (statusCode === httpStatusCode.OK) {
         // 성공적인 응답
         return "이메일이 성공적으로 전송되었습니다.";
     } else if (statusCode === httpStatusCode.BADREQUESAT) {
         // 잘못된 요청
         return "잘못된 요청입니다.";
     } else if (statusCode === httpStatusCode.NOTFOUND) {
         // 리소스가 없음
         return "요청한 리소스를 찾을 수 없습니다.";
     } else {
         // 기타 상황에 대한 처리
         return "알 수 없는 오류가 발생했습니다.";
     }ㄴ
  
    } catch (error) {
      console.error("이메일 응답에 에러가 발생함", error);
      throw error;
    }
  };
  
  