import { configureStore } from "@reduxjs/toolkit";
import profileReducer from "../features/profile/profileSlice";
import photoReduer from "../features/photo/photoSlice";
import contactReducer from "../features/contact/contactSlice";
import progressReducer from "../components/progress/progressSlice";
import alertReducer from "../components/alert/alertSlice";

// 최상위 사가
import rootSaga from "../saga";
import createSagaMiddleware from "@redux-saga/core";

// saga middleware 생성
// middleware: 중간에 먼가를 처리하는 소프트웨어
// redux saga는 redux 상태처리 전/후에 먼가를 해주는 라이브러리
const sagaMiddleware = createSagaMiddleware();

// global state(전역 상태) 저장소 만듦
// global state: profile, todo, contact .... 여러개 state가 있음
// ** 이러한 state들은 다른 컴포넌트와 state가 공유 됨
export const store = configureStore({
  // 각 state별로 처리할 reducer 목록
  reducer: {
    // state이름: reducer이름
    // profile state 처리하는 reducer를 등록
    profile: profileReducer,
    // photo state를 처리하는 reducer를 등록
    photo: photoReduer,
    contact: contactReducer,
    progress: progressReducer,
    alert: alertReducer,
  },
  // redux store(dispatcher)에 미들웨어 적용
  // middleware는 여러개 사용할 수 있음, [defaultMiddlware, sagaMiddleware, thunkMiddlware]
  middleware: [sagaMiddleware],
  devTools: true, // 개발툴 사용여부
});

// Redux
/*
 comp -> dispatch(reduxAction) 
   -> dispatcher -> reducer -> store/state
*/

// Redux-Saga
/*
 comp -> dispatch(sagaAction) 
   -> dispatcher -> saga -> api(서버연동) -> put(reduxAction) (추가단계)
     -> dispatcher -> reducer -> store/state
*/

// saga middleware를 실행
// rootSaga와 하위에 정의한 감지(take)할 Saga Action들에 대해서 감지 시작
sagaMiddleware.run(rootSaga);

// typesciprt에서는 몇가지 타입 선언을 해야함

// root state 타입 정의
// 가장 취상위 state
// state.profile, state.contact.....
export type RootState = ReturnType<typeof store.getState>;

// dispatch 타입 정의
// dispatch 함수의 generic type
export type AppDispatch = typeof store.dispatch;
