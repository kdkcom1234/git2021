import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useRouter } from "next/router";
import { AppDispatch, RootState } from "../../../provider";
import { requestModifyPhoto } from "../../../middleware/modules/photo";
import { PhotoItem } from "../../../provider/modules/photo";
import photoApi, { PhotoItemResponse } from "../../../api/photo";

import Layout from "../../../components/layout";

const PhotoEdit = () => {
  const router = useRouter();

  const id = router.query.id as string;

  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  );
  // console.log(photoItem);

  const isModifyCompleted = useSelector(
    (state: RootState) => state.photo.isModifyCompleted
  );

  const dispatch = useDispatch<AppDispatch>();

  const [url, setUrl] = useState<string | undefined>(photoItem?.photoUrl);

  const titleInput = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);
  const fileInput = useRef<HTMLInputElement>(null);

  useEffect(() => {
    isModifyCompleted && router.push("/photos");
  }, [isModifyCompleted, router]);

  // ------ 이벤트에 대해서 처리하는 부분 --------
  const changeFile = () => {
    console.log("change");
    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        setUrl(reader.result?.toString());
      };

      reader.readAsDataURL(imageFile);
    }
  };

  const handleSaveClick = () => {
    // 파일이 있을 때 처리
    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        if (photoItem) {
          // 기존 데이터 카피
          const item = { ...photoItem };
          // 변경할 속성만 대입
          item.title = titleInput.current ? titleInput.current.value : "";
          item.description = descTxta.current?.value;
          item.photoUrl = reader.result ? reader.result.toString() : "";
          item.fileType = imageFile.type;
          item.fileName = imageFile.name;

          // reducer로 state 수정 및 목록으로 이동
          saveItem(item);

          // // SSR 상태로 redux store가 없다면
          // if (!isModifyCompleted) {
          //   router.push("/photos");
          // }
        }
      };

      reader.readAsDataURL(imageFile);
    }
    // 파일이 없을 때 처리
    else {
      if (photoItem) {
        // 기존 데이터 카피
        const item = { ...photoItem };
        // 변경할 속성만 대입
        item.title = titleInput.current ? titleInput.current.value : "";
        item.description = descTxta.current?.value;

        // reducer로 state 수정 및 목록으로 이동
        saveItem(item);
      }
    }
  };

  const saveItem = (item: PhotoItem) => {
    // dispatch(modifyPhoto(item));
    dispatch(requestModifyPhoto(item)); // saga action으로 대체
    // history.push("/photos");
  };

  return (
    <Layout>
      <section style={{ width: "40vw" }} className="mx-auto">
        <h2 className="text-center">Photo Edit</h2>
        <form>
          <table className="table">
            <tbody>
              <tr>
                <th>제목</th>
                <td>
                  <input
                    className="form-control"
                    type="text"
                    defaultValue={photoItem?.title}
                    ref={titleInput}
                  />
                </td>
              </tr>
              <tr>
                <th>설명</th>
                <td>
                  <textarea
                    className="form-control"
                    style={{ height: "40vh" }}
                    defaultValue={photoItem?.description}
                    ref={descTxta}
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
                    ref={fileInput}
                    onChange={() => {
                      changeFile();
                    }}
                  />
                </td>
              </tr>
            </tbody>
          </table>
        </form>
        <div>
          <button
            className="btn btn-secondary me-1 float-start"
            onClick={() => {
              router.push("/photos");
            }}
          >
            <i className="bi bi-grid-3x3-gap me-1"></i>
            목록
          </button>
          <button
            className="btn btn-primary float-end"
            onClick={() => {
              handleSaveClick();
            }}
          >
            <i className="bi bi-check" />
            저장
          </button>
        </div>
      </section>
    </Layout>
  );
};

export default PhotoEdit;
