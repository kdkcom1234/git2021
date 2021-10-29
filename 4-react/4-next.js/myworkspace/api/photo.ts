import axios from "axios";

export interface PhotoPagingReponse {
  content: PhotoItemResponse[];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

// 서버로 부터 받아오는 데이터 1건에 대한 타입
export interface PhotoItemResponse {
  id: number;
  title: string;
  description: string;
  photoUrl: string;
  fileType: string;
  fileName: string;
  createdTime: number;
}

export interface PhotoItemRequest {
  title: string;
  description?: string;
  photoUrl: string;
  fileType: string;
  fileName: string;
}

// 서버하고 데이터 연동하는 api처리 목록을 별도의 객체로 작성
// process.env.변수명
const photoApi = {
  get: (id: number) =>
    axios.get<PhotoItemResponse>(
      `${process.env.NEXT_PUBLIC_API_BASE}/photos/${id}`
    ),
  // axios.get<응답데이터의타입>(요청URL);
  // GET 요청URL HTTP/1.1
  fetch: () =>
    axios.get<PhotoItemResponse[]>(
      `${process.env.NEXT_PUBLIC_API_BASE}/photos`
    ),

  fetchPaging: (page: number, size: number) =>
    axios.get<PhotoPagingReponse>(
      `${process.env.NEXT_PUBLIC_API_BASE}/photos/paging?page=${page}&size=${size}`
    ),

  // axios.post<응답타입>(요청URL, 요청객체(JSON바디));
  // POST 요청URL HTTP/1.1  {...}
  add: (photoItem: PhotoItemRequest) =>
    axios.post<PhotoItemResponse>(
      `${process.env.NEXT_PUBLIC_API_BASE}/photos`,
      photoItem
    ),
  // axios.delete<응답타입>(요청URL);
  // DELETE 요청URL HTTP/1.1
  remove: (id: number) =>
    axios.delete<boolean>(`${process.env.NEXT_PUBLIC_API_BASE}/photos/${id}`),

  // axios.PUT<응답타입>(요청URL, 요청객체(JSON바디));
  // PUT 요청URL HTTP/1.1  {...}
  modify: (id: number, photoItem: PhotoItemRequest) =>
    axios.put<PhotoItemResponse>(
      `${process.env.NEXT_PUBLIC_API_BASE}/photos/${id}`,
      photoItem
    ),
};

export default photoApi;
