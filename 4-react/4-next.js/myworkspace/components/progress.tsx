import { useSelector } from "react-redux";
import { RootState } from "../provider";

const Progress = () => {
  const progress = useSelector((state: RootState) => state.progress);
  // console.log(progress);

  return (
    <>
      {/* 너비값은 컨텐츠 너비, 높이값은 자식 높이 */}
      {/* spinner, progress 이런 것들은 사용자의 입력 방지 */}
      {progress.status && (
        <div
          className="position-fixed"
          style={{
            width: "100%",
            height: "100%",
            left: 0,
            top: 0,
            zIndex: 9000,
          }}
        >
          <div
            className="spinner-border text-info position-fixed"
            role="status"
            style={{
              left: "calc(50% - 1rem)",
              top: "calc(50% - 1rem)",
              zIndex: 9900,
            }}
          >
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      )}
    </>
  );
};

export default Progress;
