import { useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { requestAddPhotoPaging } from "./photoSaga";
import { PhotoItem } from "./photoSlice";
// import { addPhoto } from "./photoSlice";

const PhotoCreate = () => {
  // 입력 폼 ref 객체
  const titleInput = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);
  const fileInput = useRef<HTMLInputElement>(null);

  // 포토 데이터 배열 가져오기
  const photoData = useSelector((state: RootState) => state.photo.data);
  // 추가 완료 여부
  // 1. state 변경감지 및 값 가져오기
  const isAddCompleted = useSelector(
    (state: RootState) => state.photo.isAddCompleted
  );

  // dispatch 함수 만들기
  const dispatch = useDispatch<AppDispatch>();

  // history 객체 가져오기
  const history = useHistory();

  // isAddCompleted값이 변경되면 처리(처음 렌더링되는 시점에도 처리됨)
  // 2. state가 변경되면 처리되는 함수
  useEffect(() => {
    console.log("--isAddCompleted 변경: " + isAddCompleted);
    // true이면 화면이동
    isAddCompleted && history.push("/photos");
  }, [isAddCompleted, history, dispatch]);

  const handleAddClick = () => {
    // console.log(titleInput.current?.value);
    // console.log(descTxta.current?.value);
    if (fileInput.current?.files?.length) {
      // console.log(fileInput.current.files[0]);
    }

    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        // 추가할 객체 생성
        const item: PhotoItem = {
          // 기존데이터의 id 중에서 가장 큰 것 + 1
          id: photoData.length ? photoData[0].id + 1 : 1,
          // 입력 정보
          title: titleInput.current ? titleInput.current.value : "",
          description: descTxta.current?.value,
          photoUrl: reader.result ? reader.result.toString() : "",
          fileType: imageFile.type,
          fileName: imageFile.name,
          // 시스템 값(작성일시, 수정일시, 수정한사람....)
          createdTime: new Date().getTime(),
        };

        // console.log(item);

        // state에 데이터 추가
        // 1. addPhoto함수에서 Action 객체를 생성함
        //    -> {type:"photo/addPhoto", payload:item}
        // 2. Action 객체를 Dispatcher에 전달함
        // 3. Dispatcher에서 Action.type에 맞는 리듀서 함수를 실행
        //    -> 기존 state와 action객체를 매개변수를 넣어주고 실행

        /* ----- 기존 redux action ----- */
        // dispatch(addPhoto(item));

        /* ----- saga action으로 대체 ----- */
        // dispatch(requestAddPhoto(item)); // 전체조회
        dispatch(requestAddPhotoPaging(item)); // 넘버 페이징
        // dispatch(requestAddPhotoNext(item)); // 더보기 페이징

        // ** action creator를 사용하지 않고 아래 방법으로도 가능함
        // type: slice이름/reducer함수이름
        // payload: PayloadAction<페이로드타입>에 맞는 데이터 객체

        // 예)
        // type: photo/addPhoto
        // payload: item
        // dispatch({
        //   type: "photo/addPhoto",
        //   payload: item,
        // });

        // // 목록 화면으로 이동
        // history.push("/photos");
      };

      reader.readAsDataURL(imageFile);
    }
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Photo Create</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input className="form-control" type="text" ref={titleInput} />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  ref={descTxta}
                ></textarea>
              </td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <input
                  className="form-control"
                  type="file"
                  accept="image/*"
                  ref={fileInput}
                />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div>
        <button
          className="btn btn-secondary float-start"
          onClick={() => {
            history.push("/photos");
          }}
        >
          <i className="bi bi-grid-3x3-gap me-1"></i>
          목록
        </button>
        <button
          className="btn btn-primary float-end"
          onClick={() => {
            handleAddClick();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default PhotoCreate;
