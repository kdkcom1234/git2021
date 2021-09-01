import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { penguin } from "../../common/data";

export interface PhotoItem {
  id: number;
  profileUrl: string;
  username: string;
  title: string;
  description?: string;
  photoUrl: string;
}

interface PhotoState {
  // Map<키, 값>
  // Map<id값, PhotoItem>
  // Map<number, PhotoItem>
  data: Map<number, PhotoItem>; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 정보
}

const initialState: PhotoState = {
  // new Map([[key, value], [key, value], [key, value] ...])
  data: new Map([
    [
      // key
      5,
      // value
      {
        id: 5,
        profileUrl: penguin,
        username: "Daekeun Ko",
        title: "펭귄이",
        description: "세 마리 펭귄들의 대화",
        photoUrl: penguin,
      },
    ],
    [
      // key
      4,
      // value
      {
        id: 4,
        profileUrl: penguin,
        username: "Daekeun Ko",
        title: "펭귄이",
        description: "세 마리 펭귄들의 대화",
        photoUrl: penguin,
      },
    ],
    [
      // key
      3,
      // value
      {
        id: 3,
        profileUrl: penguin,
        username: "Daekeun Ko",
        title: "펭귄이",
        description: "세 마리 펭귄들의 대화",
        photoUrl: penguin,
      },
    ],
    [
      // key
      2,
      // value
      {
        id: 2,
        profileUrl: penguin,
        username: "Daekeun Ko",
        title: "펭귄이",
        description: "세 마리 펭귄들의 대화",
        photoUrl: penguin,
      },
    ],
    [
      // key
      1,
      // value
      {
        id: 1,
        profileUrl: penguin,
        username: "Daekeun Ko",
        title: "펭귄이",
        description: "세 마리 펭귄들의 대화",
        photoUrl: penguin,
      },
    ],
  ]),
  isFetched: false,
};

const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      // Map<number, PhotoItem>
      // Map<id, {..}>
      // key, value 형태로 추가
      state.data.set(photo.id, photo);
    },
    removePhoto: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // key로 삭제
      state.data.delete(id);
    },
    modifyPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      state.data.set(photo.id, photo);
    },
  },
});

export const { addPhoto, removePhoto, modifyPhoto } = photoSlice.actions;

export default photoSlice.reducer;
