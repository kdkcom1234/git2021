import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { PhotoItem, modifyPhoto } from "./photoSlice";

const PhotoEdit = () => {
  const { id } = useParams<{ id: string }>();
  const history = useHistory();

  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  );

  const dispatch = useDispatch<AppDispatch>();

  const title = useRef<HTMLInputElement>(null);
  const desc = useRef<HTMLTextAreaElement>(null);
  const file = useRef<HTMLInputElement>(null);

  const [url, setUrl] = useState<string | undefined>(photoItem?.photoUrl);

  const changeFile = () => {
    if (file.current?.files?.length) {
      const imageFile = file.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        setUrl(reader.result?.toString());
      };

      reader.readAsDataURL(imageFile);
    }
  };

  const save = () => {
    // 파일이 있을 때 처리
    if (file.current?.files?.length) {
      const imageFile = file.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        if (photoItem) {
          const item: PhotoItem = {
            id: photoItem.id,
            profileUrl: photoItem.profileUrl,
            username: photoItem.username,
            title: title.current ? title.current.value : "",
            description: desc.current?.value,
            photoUrl: reader.result ? reader.result.toString() : "",
          };
          // redux store에 photo state에 item을 수정
          dispatch(modifyPhoto(item));
          // 포토 목록으로 이동
          history.push("/photos");
        }
      };

      reader.readAsDataURL(imageFile);
    }
    // 파일이 없을 때 처리
    else {
      if (photoItem) {
        const item: PhotoItem = {
          id: photoItem.id,
          profileUrl: photoItem.profileUrl,
          username: photoItem.username,
          title: title.current ? title.current.value : "",
          description: desc.current?.value,
          photoUrl: photoItem.photoUrl,
        };

        // redux store에 photo state에 item을 수정
        dispatch(modifyPhoto(item));
        history.push("/photos");
      }
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
                <input
                  className="form-control"
                  type="text"
                  ref={title}
                  defaultValue={photoItem?.title}
                />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "20vh" }}
                  ref={desc}
                  defaultValue={photoItem?.description}
                ></textarea>
              </td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <img src={url} alt={photoItem?.title} />
                <input
                  className="form-control"
                  type="file"
                  accept="image/*"
                  ref={file}
                  onChange={() => {
                    changeFile();
                  }}
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
            save();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default PhotoEdit;
