import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import Pagination from "../../components/Pagination";
import { AppDispatch, RootState } from "../../store";
import { requestFetchPagingPhotos } from "./photoSaga";

const getTimeString = (unixtime: number) => {
  // 1초: 1000
  // 1분: 60 * 1000
  // 1시간: 60 * 60 * 1000
  // 1일: 24 * 60 * 60 * 1000
  const day = 24 * 60 * 60 * 1000;

  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);

  // 현재시간보다 24시간 이전이면 날짜를 보여주고
  // 현재시간보다 24시간 미만이면 시간을 보여줌
  return unixtime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
};

const Photo = () => {
  // photo state 전체를 가져옴
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();
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
      dispatch(
        requestFetchPagingPhotos({
          page: 0,
          size: photo.pageSize,
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
    <div>
      <h2 className="text-center">Photos</h2>
      {/* 버튼 */}
      <div className="d-flex justify-content-end mb-2">
        <select
          className="form-select form-select-sm me-2"
          style={{ width: "60px" }}
          onChange={(e) => {
            handlePageSizeChanged(e);
          }}
        >
          {[2, 4, 8, 12].map((size) => (
            <option value={size} selected={photo.pageSize === size}>
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
            history.push("/photos/create");
          }}
        >
          <i className="bi bi-plus" />
          추가
        </button>
      </div>
      {/* 컨텐트 */}
      <div className="d-flex flex-wrap">
        {/* state 데이터 배열에 map함수로 출력 */}
        {photo.data.map((item, index) => (
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
                history.push(`/photos/detail/${item.id}`);
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
    </div>
  );
};

export default Photo;
