import { useState } from "react";
import Pagination from "../components/Pagination";

const Home = () => {
  const [currentPage, setCurrentpage] = useState(0);

  const handlePageChanged = (page: number) => {
    setCurrentpage(page);
  };

  return (
    <div>
      This is Home Component
      <Pagination
        blockSize={10}
        totalPages={25}
        currentPage={currentPage}
        onPageChanged={handlePageChanged}
      />
    </div>
  );
};

export default Home;
