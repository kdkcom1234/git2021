import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { RootState } from "../../store";

const Photo = () => {
  // photo state 전체를 가져옴
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();

  return (
    <div>
      <h2 className="text-center">Photos</h2>
      <div className="d-flex justify-content-end mb-2">
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
            <div className="card-header">
              <img
                width={24}
                height={16}
                src={item.profileUrl}
                alt={item.username}
              />
              <span>{item.username}</span>
            </div>
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
              </div>
            </div>
            {/* 컨텐트 wrapper -- 끝 */}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Photo;
