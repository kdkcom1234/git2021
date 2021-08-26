import { useRef, useState } from "react";
// import { lorem, penguin, robot } from "../common/data";
// import { getTimeString } from "../common/lib/string";

interface PhotoState {
  id: number;
  title?: string | undefined;
  dataUrl?: string | undefined;
  fileType?: string | undefined;
  createTime: number;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return dateTime.toLocaleDateString();
};

const Photo = () => {
  const [photoList, setPhotoList] = useState<PhotoState[]>([]);

  const textRef = useRef<HTMLInputElement>(null);
  const fileRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    if (fileRef.current?.files?.length) {
      const file = fileRef.current?.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);

      reader.onload = () => {
        post(reader.result?.toString(), file.type);
      };
    } else {
      post(undefined, undefined);
    }
  };

  const post = (dataUrl: string | undefined, fileType: string | undefined) => {
    const feed: PhotoState = {
      id: photoList.length > 0 ? photoList[0].id + 1 : 1,
      // optional chaning
      title: textRef.current?.value,
      dataUrl: dataUrl,
      fileType: fileType,
      createTime: new Date().getTime(),
    };

    setPhotoList([feed, ...photoList]);

    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number) => {
    setPhotoList(photoList.filter((item) => item.id !== id));
  };

  return (
    <>
      <h2 className="text-center my-5">Photos</h2>
      <form
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault();
        }}
        ref={formRef}
      >
        <input
          type="text"
          className="form-control mb-1"
          placeholder="Title of image..."
          ref={textRef}
          style={{ boxSizing: "border-box" }}
        ></input>
        <div className="d-flex">
          <input
            type="file"
            className="form-control me-1"
            accept="image/png, image/jpeg"
            ref={fileRef}
          />
          <button
            className="btn btn-primary text-nowrap"
            type="button"
            onClick={() => {
              add(null);
            }}
          >
            입력
          </button>
        </div>
      </form>
      <div className="mt-3 d-flex flex-wrap">
        {photoList.map((item, index) => (
          <div className="card mt-1" key={item.id}>
            <img src={item.dataUrl} className="card-img-top" alt={item.title} />
            <div className="card-body">
              <p className="card-text">{item.title}</p>
              <div className="d-flex">
                <div className="w-100">
                  <span className="text-secondary">
                    {getTimeString(item.createTime)}
                  </span>
                </div>
                <a
                  href="#!"
                  onClick={(e) => {
                    e.preventDefault(); // 기본 동작 방지
                    del(item.id);
                  }}
                  className="link-secondary fs-6 text-nowrap"
                >
                  삭제
                </a>
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default Photo;
