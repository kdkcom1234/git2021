import { useState } from "react";

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
  // 현재 페이지 블럭번호
  const [currentBlock, setCurrentBlock] = useState<number>(
    Math.floor(currentPage / blockSize)
  );

  console.log(`--totalPages: ${totalPages}`);
  console.log(`--blockSize: ${blockSize}`);
  console.log(`--currentPage: ${currentPage}`);
  console.log(`--currentBlock: ${currentBlock}`);

  interface PagesProps {
    blockSize: number;
    totalPages: number;
    currentBlock: number;
  }

  const getPages = ({ blockSize, totalPages, currentBlock }: PagesProps) => {
    let blocks: number[] = [];

    let num = 0;
    while (true) {
      // 가장 마지막 블럭일 때
      if (blocks.length > 0 && blocks[blocks.length - 1] === totalPages - 1)
        break;

      // 블럭이 다 만들어졌을 때
      if (num === blockSize) break;

      blocks.push(currentBlock * blockSize + num);
      num++;
    }

    // console.log(blocks);

    return blocks;
  };

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
                setCurrentBlock(currentBlock - 1);
                onPageChanged && onPageChanged(currentBlock * blockSize - 1);
              }}
              href="!#"
            >
              PREV
            </a>
          </li>
        )}
        {/* 페이지 번호 영역 */}
        {getPages({ blockSize, totalPages, currentBlock }).map((num) => (
          <li
            className={`page-item ${currentPage === num ? "active" : ""}`}
            key={`page-${num}`}
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
        {totalPages > blockSize && currentBlock * blockSize !== totalPages - 1 && (
          <li className="page-item">
            <a
              className="page-link"
              onClick={(e) => {
                e.preventDefault();
                setCurrentBlock(currentBlock + 1);
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
