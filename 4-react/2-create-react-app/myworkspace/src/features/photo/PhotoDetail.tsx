import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { requestRemovePhotoPaging } from "./photoSaga";
// import { removePhoto } from "./photoSlice";

const PhotoDetail = () => {
  // useParam<타입>(), 매개변수들을 객체화할 형식을 제너릭으로 넣어줌
  // Generic: <타입> 타입을 매개변수로 넣음
  // 타입에 따라서 처리를 다르게 하기위함
  // 객체지향 다형성(poly mophism): 같은 이름의 함수가 내부적으로 처리를 다르게 해줌
  const { id } = useParams<{ id: string }>();
  // console.log(id);

  // 타입 단언을 하지 않으면 추론에 의해서 PhotoItem | undefined 타입이 됨
  // 타입 단언을 하면 반환 형식을 정의할 수 있음
  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  ); // 반환형식을 타입 추론으로 처리
  // ) as PhotoItem; // 타입 단언 (type assertion)
  // console.log(photoItem);

  // 삭제 여부 감지 및 가져오기
  const isRemoveCompleted = useSelector(
    (state: RootState) => state.photo.isRemoveCompleted
  );

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    isRemoveCompleted && history.push("/photos");
  }, [isRemoveCompleted, history]);

  const handDeleteClick = () => {
    // saga action으로 대체
    // dispatch(requestRemovePhoto(+id)); // 전체 조회일 때
    dispatch(requestRemovePhotoPaging(+id)); // 숫자 페이징일 때
    // dispatch(requestRemovePhotoNext(+id)); // 더보기 페이징일 때

    // dispatch(removePhoto(+id)); // id값만 넣어서 삭제
    // history.push("/photos"); // 목록화면으로 이동
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Photo Detail</h2>
      {!photoItem && <div className="text-center my-5">데이터가 없습니다.</div>}
      {photoItem && (
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>{photoItem.title}</td>
            </tr>
            <tr>
              <th>설명</th>
              <td>{photoItem.description}</td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <img
                  src={photoItem.photoUrl}
                  alt={photoItem.title}
                  width={"100%"}
                />
              </td>
            </tr>
          </tbody>
        </table>
      )}

      <div className="d-flex">
        <div style={{ width: "50%" }}>
          <button
            className="btn btn-secondary me-1"
            onClick={() => {
              history.push("/photos");
            }}
          >
            <i className="bi bi-grid-3x3-gap me-1"></i>
            목록
          </button>
        </div>
        <div style={{ width: "50%" }} className="d-flex justify-content-end">
          <button
            className="btn btn-primary me-1"
            onClick={() => {
              history.push(`/photos/edit/${id}`);
            }}
          >
            <i className="bi bi-pencil me-1" />
            수정
          </button>
          <button
            className="btn btn-danger"
            onClick={() => {
              handDeleteClick();
            }}
          >
            <i className="bi bi-trash me-1" />
            삭제
          </button>
        </div>
      </div>
    </div>
  );
};

export default PhotoDetail;
