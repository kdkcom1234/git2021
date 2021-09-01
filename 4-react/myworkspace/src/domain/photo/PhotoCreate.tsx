import { useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { PhotoItem, addPhoto } from "./photoSlice";

const PhotoCreate = () => {
  const history = useHistory();
  const photoData = useSelector((state: RootState) => state.photo.data);
  const profile = useSelector((state: RootState) => state.profile);
  const dispatch = useDispatch<AppDispatch>();

  const title = useRef<HTMLInputElement>(null);
  const desc = useRef<HTMLTextAreaElement>(null);
  const file = useRef<HTMLInputElement>(null);

  const add = () => {
    if (file.current?.files?.length) {
      const imageFile = file.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        let maxId: number | undefined = 1;
        if (photoData.size) {
          maxId = Array.from(photoData.values())[0].id;
        }

        const item: PhotoItem = {
          id: maxId ? maxId + 1 : 1,
          profileUrl: profile.image ? profile.image : "",
          username: profile.username ? profile.username : "",
          title: title.current ? title.current.value : "",
          description: desc.current?.value,
          photoUrl: reader.result ? reader.result.toString() : "",
        };

        // redux store에 photo state에 item을 추가
        dispatch(addPhoto(item));
        // 포토 목록으로 이동
        history.push("/photos");
      };

      reader.readAsDataURL(imageFile);
    }
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Photos Create</h2>{" "}
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input className="form-control" type="text" ref={title} />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  ref={desc}
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
                  ref={file}
                />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
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
          className="btn btn-primary"
          onClick={() => {
            add();
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
