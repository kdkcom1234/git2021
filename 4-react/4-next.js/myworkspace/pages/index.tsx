import Layout from "../components/layout";
import Link from "next/link";

import { useRouter } from "next/router";
import { getTimeString } from "../lib/string";

import photoApi from "../api/photo";
import { PhotoItemResponse } from "../api/photo";

import Image from "next/image";

interface IndexProp {
  photos: PhotoItemResponse[];
}

const Index = ({ photos }: IndexProp) => {
  const router = useRouter();

  return (
    <Layout>
      <section>
        <p>
          Recent Photos - <Link href="/photos">more..</Link>
        </p>
        <div className="d-flex flex-wrap">
          {/* state 데이터 배열에 map함수로 출력 */}
          {photos.map((item, index) => (
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
                {/* <Image
                  src={item.photoUrl}
                  className="card-img-top"
                  alt={item.title}
                  layout="responsive"
                  width={220}
                  height={150}
                /> */}
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
      </section>
    </Layout>
  );
};

export async function getServerSideProps() {
  // Fetch data from external API
  const res = await photoApi.fetchPaging(0, 4);

  // Pass data to the page via props
  return { props: { photos: res.data.content } };
}

export default Index;
