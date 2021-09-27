import { useEffect, useState } from "react";

interface PaginationProp {
  blockSize: number;
  totalPages: number;
  currentPage: number;
}

const Pages = ({
  blockSize,
  totalPages,
  currentBlock,
  currentPage,
}: {
  blockSize: number;
  totalPages: number;
  currentBlock: number;
  currentPage: number;
}) => {
  let blocks: number[] = [];

  let num = 0;
  while (true) {
    if (blocks.length > 0 && blocks[blocks.length - 1] === totalPages - 1)
      break;

    if (num === blockSize) break;

    blocks.push(currentBlock * blockSize + num);
    num++;
  }

  console.log(blocks);

  return (
    <>
      {blocks.map((num) => (
        <li
          className={`page-item ${currentPage === num ? "active" : ""}`}
          key={`page-${num}`}
        >
          <a
            className="page-link"
            onClick={(e) => e.preventDefault()}
            href="!#"
          >
            {num + 1}
          </a>
        </li>
      ))}
    </>
  );
};

// 페이지블럭 크기, 전체 페이지 개수
const Pagination = ({ blockSize, totalPages, currentPage }: PaginationProp) => {
  // 현재 페이지 블럭번호
  const [currentBlock, setCurrentBlock] = useState<number>(0);

  console.log(`--totalPages: ${totalPages}`);
  console.log(`--blockSize: ${blockSize}`);
  console.log(`--currentBlock: ${currentBlock}`);

  return (
    <nav aria-label="Page navigation example">
      <ul className="pagination">
        {currentBlock != 0 && (
          <li className="page-item">
            <a
              className="page-link"
              onClick={(e) => {
                e.preventDefault();
                setCurrentBlock(currentBlock - 1);
              }}
              href="!#"
            >
              PREV
            </a>
          </li>
        )}
        <Pages
          blockSize={blockSize}
          totalPages={totalPages}
          currentBlock={currentBlock}
          currentPage={currentPage}
        />
        {totalPages > blockSize && currentBlock * blockSize !== totalPages - 1 && (
          <li className="page-item">
            <a
              className="page-link"
              onClick={(e) => {
                e.preventDefault();
                setCurrentBlock(currentBlock + 1);
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
