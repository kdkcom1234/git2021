import { createAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { takeLatest, call, put } from "redux-saga/effects";
import authApi, { ProfileResponse } from "../../api/auth";
import profileReducer, { initialProfile } from "../../provider/modules/profile";

export const requestFetchProfile = createAction(
  `${profileReducer.name}/requestFetchProfile`
);

function* fetchProfile() {
  console.log("--fetchProfile");
  const result: AxiosResponse<ProfileResponse> = yield call(authApi.getProfile);
  // ProfileResponse -> ProfileState
  // 타입은 달라도 필드 구조가 같으면 바로 대입 가능함
  yield put(initialProfile(result.data));
}

export default function* profileSaga() {
  yield takeLatest(requestFetchProfile, fetchProfile);
}
