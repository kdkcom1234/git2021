import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { removePhoto } from "./photoSlice";

const PhotoDetail = () => {
  // url 경로에 id 매개변수를 가져오기
  // /photos/:id
  // 예) /photos/1
  // id = 1

  // const param = useParams<{ id: string }>();
  // const id = param.id;

  // 객체의 속성명과 선언할 변수명이 동일할 때
  // path variable을 문자열로만 처리됨
  const { id } = useParams<{ id: string }>();
  const history = useHistory();

  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  );

  const dispatch = useDispatch<AppDispatch>();

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2>Photo Detail</h2>

      <table className="table">
        <tbody>
          <tr>
            <th>제목</th>
            <td>{photoItem?.title}</td>
          </tr>
          <tr>
            <th>설명</th>
            <td>{photoItem?.description}</td>
          </tr>
          <tr>
            <th>이미지</th>
            <td>
              <img src={photoItem?.photoUrl} alt={photoItem?.title} />
            </td>
          </tr>
        </tbody>
      </table>

      <div className="d-flex justify-content-end">
        <button
          className="btn btn-secondary me-1"
          onClick={() => {
            history.push("/photos");
          }}
        >
          <i className="bi bi-grid-3x3-gap me-1"></i>
          목록
        </button>
        <button
          className="btn btn-primary me-1"
          onClick={() => {
            history.push(`/photos/edit/${id}`);
          }}
        >
          <i className="bi bi-check" />
          수정
        </button>
        <button
          className="btn btn-primary"
          onClick={() => {
            dispatch(removePhoto(+id));
            history.push("/photos");
          }}
        >
          <i className="bi bi-check" />
          삭제
        </button>
      </div>
    </div>
  );
};

export default PhotoDetail;
