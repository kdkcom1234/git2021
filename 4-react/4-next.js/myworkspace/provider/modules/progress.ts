import { createSlice } from "@reduxjs/toolkit";

// redux-toolkit에는 immer가 내장되어있기 때문에
// state 타입은 객체 타입만 가능함
// 원시타입(string, number, boolean) 안 됨
interface ProgressState {
  status: boolean;
}

const initialState: ProgressState = {
  status: false,
};

const progressSlice = createSlice({
  name: "progress",
  initialState,
  reducers: {
    startProgress: (state) => {
      state.status = true;
    },
    endProgress: (state) => {
      state.status = false;
    },
  },
});

export const { startProgress, endProgress } = progressSlice.actions;
export default progressSlice.reducer;
