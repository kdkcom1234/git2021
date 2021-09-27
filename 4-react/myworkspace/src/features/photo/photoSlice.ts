import { createSlice, PayloadAction } from "@reduxjs/toolkit";
// import { penguin } from "../../common/data";

// - 목록조회: 4열 그리드화면으로 목록조회(프로필, 타이틀, 이미지)
// - 사진추가: 추가버튼으로 제목, 설명, 이미지파일 선택 후 추가, 목록버튼

// 데이터구조를 interface로 만듦
export interface PhotoItem {
  id: number;
  title: string;
  description?: string;
  photoUrl: string;
  fileType: string;
  fileName: string;
  createdTime: number;
}

export interface PhotoPage {
  data: PhotoItem[];
  totalElements: number;
  totalPages: number;
  page: number;
  pageSize: number;
  isLast: boolean;
}

// 백엔드 연동 고려해서 state 구조를 설계
interface PhotoState {
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아왔는지에 대한 여부
  isAddCompleted?: boolean; // 데이터 추가가 완료되었는지 여부
  isRemoveCompleted?: boolean; // 데이터 삭제가 완료되었는지 여부
  isModifyCompleted?: boolean; // 데이터 수정이 완료되었는지 여부
  totalElements?: number;
  totalPages: number;
  page: number;
  pageSize: number;
  isLast?: boolean;
}

// photo state를 목록 -> array
const initialState: PhotoState = {
  data: [],
  isFetched: false,
  page: 0,
  pageSize: 2,
  totalPages: 0,
};

const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {
    // PayloadAction<payload타입>
    // payload로 item객체를 받음
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      console.log("--in reducer function--");
      console.log(photo);
      state.data.unshift(photo);
      state.isAddCompleted = true; // 추가가 되었음으로 표시
    },
    // payload 없는 reducer
    // completed 관련된 속성을 삭제함(undefined 상태)
    initialCompleted: (state) => {
      delete state.isAddCompleted;
      delete state.isRemoveCompleted;
      delete state.isModifyCompleted;
    },
    // payload로 id값을 받음
    // action: PayloadAction<number>
    // reducer 넘어오는 action은 payload있는 액션인데,
    // payload의 타입이 number이다.
    removePhoto: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id에 해당하는 아이템의 index를 찾고 그 index로 splice를 한다.
      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1
      );
      state.isRemoveCompleted = true; // 삭제 되었음을 표시
    },
    modifyPhoto: (state, action: PayloadAction<PhotoItem>) => {
      // 생성해서 넘긴 객체
      const modifyItem = action.payload;
      // state에 있는 객체
      const photoItem = state.data.find((item) => item.id === modifyItem.id);
      // state에 있는 객체의 속성을 넘김 객체의 속성으로 변경
      if (photoItem) {
        photoItem.title = modifyItem.title;
        photoItem.description = modifyItem.description;
        photoItem.photoUrl = modifyItem.photoUrl;
        photoItem.fileName = modifyItem.fileName;
        photoItem.fileType = modifyItem.fileType;
      }
      state.isModifyCompleted = true; // 변경 되었음을 표시
    },
    // payload값으로 state를 초기화하는 reducer 필요함
    initialPhoto: (state, action: PayloadAction<PhotoItem[]>) => {
      const photos = action.payload;
      // 백엔드에서 받아온 데이터
      state.data = photos;
      // 데이터를 받아옴으로 값을 남김
      state.isFetched = true;
    },
    // payload값으로 state를 초기화하는 reducer 필요함
    initialPagedPhoto: (state, action: PayloadAction<PhotoPage>) => {
      // 백엔드에서 받아온 데이터
      // 컨텐트
      state.data = action.payload.data;
      // 페이징 데이터
      state.totalElements = action.payload.totalElements;
      state.totalPages = action.payload.totalPages;
      state.page = action.payload.page;
      state.pageSize = action.payload.pageSize;
      state.isLast = action.payload.isLast;
      // 데이터를 받아옴으로 값을 남김
      state.isFetched = true;
    },
  },
});

// action creator 내보내기: action creator는 action객체를 반환하는 함수
export const {
  addPhoto,
  removePhoto,
  modifyPhoto,
  initialPhoto,
  initialCompleted,
  initialPagedPhoto,
} = photoSlice.actions;

export default photoSlice.reducer;
