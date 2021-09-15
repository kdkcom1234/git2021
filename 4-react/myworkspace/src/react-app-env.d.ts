/// <reference types="react-scripts" />
declare namespace NodeJS {
  interface ProcessEnv {
    REACT_APP_API_BASE: string; // .env 변수에 대한 타입선언
  }
}
