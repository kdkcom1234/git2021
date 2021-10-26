interface PaginationProp {
  blockSize: number;
  totalPages: number;
  currentPage: number;
  onPageChanged?: (pageNo: number) => void;
}

// 페이지블럭 크기, 전체 페이지 개수, 현재페이지
const Pagination = ({
  blockSize,
  totalPages,
  currentPage,
  onPageChanged,
}: PaginationProp) => {
  // // 현재 페이지 블럭번호
  let currentBlock = Math.floor(currentPage / blockSize);

  console.log(`--totalPages: ${totalPages}`);
  console.log(`--blockSize: ${blockSize}`);
  console.log(`--currentPage: ${currentPage}`);
  console.log(`--currentBlock: ${currentBlock}`);

  return (
    <nav>
      <ul className="pagination">
        {/* PREVIOUS 영역 */}
        {currentBlock !== 0 && (
          <li className="page-item">
            <a
              className="page-link"
              onClick={(e) => {
                e.preventDefault();
                onPageChanged && onPageChanged(currentBlock * blockSize - 1);
              }}
              href="!#"
            >
              PREV
            </a>
          </li>
        )}
        {/* 페이지 번호 영역 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 작으면 남아있는 페이지수 만큼 페이지번호를 만듦 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 크거나 같으면 블록사이즈만큼 페이지번호 만듦 */}
        {Array.from(
          Array(
            totalPages - currentBlock * blockSize < blockSize
              ? totalPages - currentBlock * blockSize
              : blockSize
          ).keys()
        )
          // 현재 블록기준으로 페이지번호를 생성함
          // 예) index = 0, 1, 2
          //     currentBlock = 2, blockSize = 3
          //     num = 6, 7, 8
          .map((index) => currentBlock * blockSize + index)
          .map((num) => (
            <li
              className={`page-item ${currentPage === num ? "active" : ""}`}
              key={`page-${currentBlock * blockSize + num}`}
            >
              <a
                className="page-link"
                onClick={(e) => {
                  e.preventDefault();
                  onPageChanged && onPageChanged(num);
                }}
                href="!#"
              >
                {num + 1}
              </a>
            </li>
          ))}
        {/* NEXT 영역 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 크면 NEXT 보임 */}
        {totalPages - currentBlock * blockSize > blockSize && (
          <li className="page-item">
            <a
              className="page-link"
              onClick={(e) => {
                e.preventDefault();
                onPageChanged &&
                  onPageChanged(currentBlock * blockSize + blockSize);
              }}
              href="!#"
            >
              Next
            </a>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Pagination;
