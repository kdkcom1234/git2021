import { createAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { takeLatest, takeEvery, call, put } from "redux-saga/effects";
import authApi, { ProfileResponse } from "../../api/auth";
import profileReducer, {
  clearProfile,
  initialProfile,
} from "../../provider/modules/profile";

export const requestFetchSessionProfile = createAction(
  `${profileReducer.name}/requestFetchSessionProfile`
);

export const requestClearSessionProfile = createAction(
  `${profileReducer.name}/requestClearSession`
);

function* fetchProfile() {
  console.log("--fetchProfile");
  const result: AxiosResponse<ProfileResponse> = yield call(
    authApi.getSessionProfile
  );
  // ProfileResponse -> ProfileState
  // 타입은 달라도 필드 구조가 같으면 바로 대입 가능함
  yield put(initialProfile(result.data));
}

function* clearSessionProfile() {
  console.log("--clearSessionProfile");

  const result: AxiosResponse<string> = yield call(authApi.clearSessionProfile);

  console.log(result.data);

  yield put(clearProfile());
}

export default function* profileSaga() {
  yield takeLatest(requestFetchSessionProfile, fetchProfile);
  yield takeEvery(requestClearSessionProfile, clearSessionProfile);
}
