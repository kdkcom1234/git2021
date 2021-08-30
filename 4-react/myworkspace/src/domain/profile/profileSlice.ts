import { createSlice, PayloadAction } from "@reduxjs/toolkit";
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
  // state 변경함수 목록
  reducers: {
    // action type: profile/saveProfile에 해당하는 reducer 함수
    // slice명/redcuer함수명
    // 함수명: (기존state변수명, action변수명) => { state 변경처리 }
    saveProfile: (state, action: PayloadAction<ProfileState>) => {
      // action ==
      // {
      //   type: "profile/saveProfile",
      //   payload: { image: "...", username: "..." },
      // }
      console.log(action.type);
      console.log(action.payload);
      const profile = action.payload; // 매개변수로 받은 데이터
      // immer가 내장 되어있음 따라서 state 변수를 직접 제어함
      state.image = profile.image;
      state.username = profile.username;
    },
  },
});

// action creator 내보내기 -> 컴포넌트에서 사용하기 위함
// reducer 함수명에 맞는 action creator들을 createSlice할 때 자동으로 생성함

// action = {type: "...", payload: {...}}
// action.type: 처리할 액션의 종류를 나타내는 문자열
// action.payload: 처리할 데이터

// action creator는 action 객체를 생성하는 함수
// saveProfile(payload) => {type: "profile/saveProfile", payload}
export const { saveProfile } = profileSlice.actions;

// slice.reducer
// == state 변경함수를 여러개를 가지고 있는 객체
// slice.reducer: { function..(), function..(), ....}
// 내보내기의 기본객체를 reducer 함
export default profileSlice.reducer;
