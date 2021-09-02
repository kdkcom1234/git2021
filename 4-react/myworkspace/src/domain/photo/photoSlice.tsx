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
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 정보
}

const initialState: PhotoState = {
  // new Map([[key, value], [key, value], [key, value] ...])
  data: [
    {
      id: 5,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
    },
    {
      id: 4,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
    },
    {
      id: 3,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
    },
    {
      id: 2,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
    },
    {
      id: 1,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
    },
  ],
  isFetched: false,
};

const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      state.data.unshift(photo);
    },
    removePhoto: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id로 index를 찾은 후 삭제
      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1
      );
    },
    modifyPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      const item = state.data.find((item) => item.id === photo.id);
      if (item) {
        item.photoUrl = photo.photoUrl;
        item.title = photo.title;
        item.description = photo.description;
      }
    },
  },
});

export const { addPhoto, removePhoto, modifyPhoto } = photoSlice.actions;

export default photoSlice.reducer;
