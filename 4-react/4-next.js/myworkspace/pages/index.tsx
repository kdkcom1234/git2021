import Layout from "../components/layout";

import { useRouter } from "next/router";

import Image from "next/image";
import axios from "axios";

interface PublicPhoto {
  albumId: number;
  id: number;
  title: string;
  url: string;
  thumbnailUrl: string;
}

interface IndexProp {
  photos: PublicPhoto[];
}

const Index = ({ photos }: IndexProp) => {
  const router = useRouter();

  return (
    <Layout>
      <section>
        <p>Public Photos</p>
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
                  router.push(`/public-photos/${item.id}`);
                }}
              >
                <Image
                  src={item.thumbnailUrl}
                  className="card-img-top"
                  alt={item.title}
                  /* 이미지 크기에 맞게 가운데부분 노출 */
                  layout="responsive"
                  objectFit="cover"
                  /* ------------------------------- */
                  width={220}
                  height={150}
                />
                <div className="card-body">
                  <h5 className="card-title">{item.title}</h5>
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
  // // Fetch data from external API
  const res = await axios.get<PublicPhoto[]>(
    "https://jsonplaceholder.typicode.com/photos?_start=0&_limit=50"
  );
  const photos = res.data;
  // const photos = [
  //   {
  //     albumId: 1,
  //     id: 1,
  //     title: "accusamus beatae ad facilis cum similique qui sunt",
  //     url: "https://via.placeholder.com/600/92c952",
  //     thumbnailUrl: "https://via.placeholder.com/150/92c952",
  //   },
  //   {
  //     albumId: 1,
  //     id: 2,
  //     title: "reprehenderit est deserunt velit ipsam",
  //     url: "https://via.placeholder.com/600/771796",
  //     thumbnailUrl: "https://via.placeholder.com/150/771796",
  //   },
  //   {
  //     albumId: 1,
  //     id: 3,
  //     title: "officia porro iure quia iusto qui ipsa ut modi",
  //     url: "https://via.placeholder.com/600/24f355",
  //     thumbnailUrl: "https://via.placeholder.com/150/24f355",
  //   },
  //   {
  //     albumId: 1,
  //     id: 4,
  //     title: "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
  //     url: "https://via.placeholder.com/600/d32776",
  //     thumbnailUrl: "https://via.placeholder.com/150/d32776",
  //   },
  //   {
  //     albumId: 1,
  //     id: 5,
  //     title: "natus nisi omnis corporis facere molestiae rerum in",
  //     url: "https://via.placeholder.com/600/f66b97",
  //     thumbnailUrl: "https://via.placeholder.com/150/f66b97",
  //   },
  //   {
  //     albumId: 1,
  //     id: 6,
  //     title: "accusamus ea aliquid et amet sequi nemo",
  //     url: "https://via.placeholder.com/600/56a8c2",
  //     thumbnailUrl: "https://via.placeholder.com/150/56a8c2",
  //   },
  //   {
  //     albumId: 1,
  //     id: 7,
  //     title: "officia delectus consequatur vero aut veniam explicabo molestias",
  //     url: "https://via.placeholder.com/600/b0f7cc",
  //     thumbnailUrl: "https://via.placeholder.com/150/b0f7cc",
  //   },
  //   {
  //     albumId: 1,
  //     id: 8,
  //     title: "aut porro officiis laborum odit ea laudantium corporis",
  //     url: "https://via.placeholder.com/600/54176f",
  //     thumbnailUrl: "https://via.placeholder.com/150/54176f",
  //   },
  // ];

  // Pass data to the page via props
  return { props: { photos } };
}

export default Index;
