import { createSlice } from "@reduxjs/toolkit";
import { penguin } from "../../common/data";

// redux store(리덕스 저장소)에 하나의 state 관리하고 처리할 수는 모듈
// slice에는 state와 reducer가 있음
// reducer는 state를 변경하는 함수

// state 타입
export interface ProfileState {
  image: string | undefined;
  username: string | undefined;
}

// state 초기 상태를 선언
const initialState: ProfileState = {
  image: penguin,
  username: "Daekeun Ko",
};

// slice를 생성
export const profileSlice = createSlice({
  name: "profile", // slice의 이름(state 이름)
  // initialState: initialState, // state 초기값
  initialState, // state 초기값
  reducers: {}, // state 변경함수 목록
});
