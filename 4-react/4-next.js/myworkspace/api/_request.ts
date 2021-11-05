import axios, { AxiosInstance } from "axios";
import { getSessionId, removeSessionId } from "../lib/cookie";

const createAxiosInstance = () => {
  // next 서버 컴파일 시점
  if (typeof document == "undefined") {
    // 쿠키 처리 못하므로 axios 객체 반환
    return axios;
  }

  let instance: AxiosInstance;

  // 세션 정보가 없으면
  if (!getSessionId()) {
    // axios 인스턴스 만들기
    instance = axios.create();
  } else {
    // 세션Id를 header 넣고 요청하도록 한다.
    instance = axios.create({
      headers: { APP_SESSION_ID: `${getSessionId()}` },
    });
  }

  // axios interceptors
  // ajax 요청 후 응답이 왔을 때의 후처리
  instance.interceptors.response.use(
    (res) => {
      // console.log("401/403");
      return res;
    },
    // 2xx 이외 범위에 에러 상태코드가 왔을 때 처리
    (error) => {
      // console.log("401/403");
      // 응답 상태코드가 401, 403 이면 로그인 페이지로 이동
      // 401: 인증이 안 됐음
      // 403: 권한이 없음
      if ([401, 403].indexOf(error.response.status) > -1) {
        console.log("401/403");
        window.location.replace("/signin");
      }

      return Promise.reject(error);
    }
  );

  return instance;
};

export { createAxiosInstance };
