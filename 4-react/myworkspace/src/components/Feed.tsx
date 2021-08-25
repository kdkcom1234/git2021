import { useRef, useState } from "react";
// import { lorem, penguin, robot } from "../common/data";
// import { getTimeString } from "../common/lib/string";

interface FeedState {
  id: number;
  content?: string | undefined;
  dataUrl?: string | undefined;
  fileType?: string | undefined;
  createTime: number;
  modifyTime?: number;
  isEdit?: boolean;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Feed = () => {
  const [feedList, setFeedList] = useState<FeedState[]>([]);

  const textRef = useRef<HTMLTextAreaElement>(null);
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
    const feed: FeedState = {
      id: feedList.length > 0 ? feedList[0].id + 1 : 1,
      // optional chaning
      content: textRef.current?.value,
      dataUrl: dataUrl,
      fileType: fileType,
      createTime: new Date().getTime(),
    };

    setFeedList([feed, ...feedList]);

    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number) => {
    setFeedList(feedList.filter((item) => item.id !== id));
  };

  return (
    <>
      <h2 className="text-center my-5">Feeds</h2>
      <form
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault();
        }}
        ref={formRef}
      >
        <textarea
          className="form-control mb-1"
          placeholder="Leave a post here"
          ref={textRef}
          style={{ boxSizing: "border-box", height: "15vh" }}
        ></textarea>
        <div className="d-flex">
          <input
            type="file"
            className="form-control me-1"
            accept="image/png, image/jpeg, video/mp4"
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
      <div className="mt-3">
        {feedList.map((item) => (
          <div className="card mt-1" key={item.id}>
            {item.fileType &&
              (item.fileType?.includes("image") ? (
                <img
                  src={item.dataUrl}
                  className="card-img-top"
                  alt={item.content}
                />
              ) : (
                <video className="card-img-top" controls>
                  <source src={item.dataUrl} type="video/mp4"></source>
                </video>
              ))}
            <div className="card-body">
              <p className="card-text">{item.content}</p>
              <div className="d-flex">
                <div className="w-100">
                  <span className="text-secondary">
                    {getTimeString(
                      item.modifyTime ? item.modifyTime : item.createTime
                    )}
                  </span>
                </div>
                <a
                  href="#!"
                  onClick={(e) => {
                    e.preventDefault();
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

export default Feed;
