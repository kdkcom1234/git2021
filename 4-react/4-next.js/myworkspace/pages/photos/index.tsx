import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useRouter } from "next/router";
import Pagination from "../../components/pagination";
import { AppDispatch, RootState } from "../../provider";
import { requestFetchPagingPhotos } from "../../middleware/modules/photo";

import { getTimeString } from "../../lib/string";

import Layout from "../../components/layout";

const Photo = () => {
  // photo state 전체를 가져옴
  const photo = useSelector((state: RootState) => state.photo);
  const router = useRouter();
  const dispatch = useDispatch<AppDispatch>();

  // const [currentPage, setCurrentPage] = useState<number>(0);

  // photo.isFetched를 가져올때와 바뀔때마다 실행됨
  // **dispatch 객체는 위에서 생성된 후 바뀌지 않으므로
  // **dispatch 객체에 따른 effect가 발생하지는 않음
  useEffect(() => {
    // console.log(dispatch);
    // console.log(photo.isFetched);
    // 데이터 fetch가 안되었으면 데이터를 받아옴
    if (!photo.isFetched) {
      // 서버에서 데이터를 받아오는 action을 디스패치함
      // dispatch(requestFetchPhotos());

      const photoPageSize = localStorage.getItem("photo_page_size");

      dispatch(
        requestFetchPagingPhotos({
          page: 0,
          size: photoPageSize ? +photoPageSize : photo.pageSize,
        })
      );
    }
  }, [dispatch, photo.isFetched, photo.pageSize]);

  const handlePageChanged = (page: number) => {
    console.log("--page: " + page);
    // setCurrentPage(page);
    dispatch(
      requestFetchPagingPhotos({
        page,
        size: photo.pageSize,
      })
    );
  };

  const handlePageSizeChanged = (e: React.ChangeEvent<HTMLSelectElement>) => {
    console.log(e.currentTarget.value);
    dispatch(
      requestFetchPagingPhotos({
        page: photo.page,
        size: +e.currentTarget.value,
      })
    );
  };

  return (
    <Layout>
      <section>
        <h2 className="text-center">Photos</h2>
        {(!photo.isFetched || photo.data.length === 0) && (
          <div className="text-center my-5">데이터가 없습니다.</div>
        )}
        {/* 버튼 */}
        <div className="d-flex justify-content-end mb-2">
          <select
            className="form-select form-select-sm me-2"
            style={{ width: "60px" }}
            value={photo.pageSize}
            onChange={(e) => {
              handlePageSizeChanged(e);
            }}
          >
            {[2, 4, 8, 12].map((size) => (
              <option key={`select-${size}`} value={size}>
                {size}
              </option>
            ))}
          </select>
          <button
            className="btn btn-secondary me-2"
            onClick={() => {
              dispatch(
                requestFetchPagingPhotos({
                  page: photo.page,
                  size: photo.pageSize,
                })
              );
            }}
          >
            <i className="bi bi-arrow-clockwise"></i>
            새로고침
          </button>
          <button
            className="btn btn-primary"
            onClick={() => {
              router.push("/photos/create");
            }}
          >
            <i className="bi bi-plus" />
            추가
          </button>
        </div>
        {/* 컨텐트 */}
        <div className="d-flex flex-wrap">
          {/* state 데이터 배열에 map함수로 출력 */}
          {photo.isFetched &&
            photo.data.length > 0 &&
            photo.data.map((item, index) => (
              <div
                key={`photo-item-${index}`}
                className="card"
                style={{
                  width: "calc((100% - 3rem) / 4)",
                  marginLeft: index % 4 === 0 ? "0" : "1rem",
                  marginTop: index > 3 ? "1rem" : "0",
                }}
              >
                {/* 컨텐트 wrapper -- 시작 */}
                <div
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    // id값을 물고 이동해야함
                    router.push(`/photos/detail/${item.id}`);
                  }}
                >
                  <img
                    src={item.photoUrl}
                    className="card-img-top"
                    alt={item.title}
                  />
                  <div className="card-body">
                    <h5 className="card-title">{item.title}</h5>
                    <h6 className="text-muted">
                      {getTimeString(item.createdTime)}
                    </h6>
                  </div>
                </div>
                {/* 컨텐트 wrapper -- 끝 */}
              </div>
            ))}
        </div>
        {/* 페이지네이션 */}
        <div className="d-flex justify-content-center mt-4">
          <Pagination
            blockSize={2} // 고정값
            totalPages={photo.totalPages}
            currentPage={photo.page}
            onPageChanged={handlePageChanged}
          />
        </div>
      </section>
    </Layout>
  );
};

export default Photo;
