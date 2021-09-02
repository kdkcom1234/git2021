import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { penguin } from "../../common/data";

// - 목록조회: 4열 그리드화면으로 목록조회(프로필, 타이틀, 이미지)
// - 사진추가: 추가버튼으로 제목, 설명, 이미지파일 선택 후 추가, 목록버튼

// 데이터구조를 interface로 만듦
export interface PhotoItem {
  id: number;
  profileUrl: string;
  username: string;
  title: string;
  description?: string;
  photoUrl: string;
  createdTime: number;
}
// 백엔드 연동 고려해서 state 구조를 설계
interface PhotoState {
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 정보
}

// photo state를 목록 -> array
const initialState: PhotoState = {
  data: [
    {
      id: 5, // id는 숫자이거나, 증가되는 문자열
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      createdTime: new Date().getTime(),
    },
    {
      id: 4,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      createdTime: new Date().getTime(),
    },
    {
      id: 3,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      createdTime: new Date().getTime(),
    },
    {
      id: 2,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      createdTime: new Date().getTime(),
    },
    {
      id: 1,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      createdTime: new Date().getTime(),
    },
  ],
  isFetched: false,
};

const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {
    // PayloadAction<payload타입>
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      console.log("--in reducer function--");
      console.log(photo);
      state.data.unshift(photo);
    },
  },
});

export const { addPhoto } = photoSlice.actions;

export default photoSlice.reducer;
