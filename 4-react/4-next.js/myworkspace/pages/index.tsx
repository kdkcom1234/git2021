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
  // // // Fetch data from external API
  const res = await axios.get<PublicPhoto[]>(
    "https://jsonplaceholder.typicode.com/photos?_start=0&_limit=8"
  );
  const photos = res.data;

  // const photos = [
  //   {
  //     id: 66,
  //     title: "알라",
  //     description: "코알라",
  //     commentCnt: 0,
  //     photoUrl:
  //       "https://d3o6g8deu522v9.cloudfront.net/304c684537bdcb228810f5c1ce6c59f189a2af9e8a3f337d85a2fbee26ea26fd",
  //     fileType: "image/jpeg",
  //     fileName: "koala.jpg",
  //     createdTime: 1635843858981,
  //     userId: null,
  //   },
  //   {
  //     id: 65,
  //     title: "펭귄",
  //     description: "귄이",
  //     commentCnt: 0,
  //     photoUrl:
  //       "https://d3o6g8deu522v9.cloudfront.net/23cb63d7eb7c002c00eabc09874d4effdb03f91aac2586696f4d3fd91c4f4bd1",
  //     fileType: "image/jpeg",
  //     fileName: "penguin.jpg",
  //     createdTime: 1635834011039,
  //     userId: null,
  //   },
  //   {
  //     id: 59,
  //     title: "3",
  //     description: "sd",
  //     commentCnt: 0,
  //     photoUrl:
  //       "https://d3o6g8deu522v9.cloudfront.net/2cbf52e87bf0385a32311ce8ffe7d1f65e8e171d7a9a90a2c67ab641ef90d343",
  //     fileType: "image/jpeg",
  //     fileName: "기린.jpg",
  //     createdTime: 1635753640540,
  //     userId: null,
  //   },
  //   {
  //     id: 58,
  //     title: "3",
  //     description: "3",
  //     commentCnt: 0,
  //     photoUrl:
  //       "https://d3o6g8deu522v9.cloudfront.net/d5d55ff02750837b491002197423db80c3f96387909d305585ed8a750c843312",
  //     fileType: "image/jpeg",
  //     fileName: "koala.jpg",
  //     createdTime: 1635753633160,
  //     userId: null,
  //   },
  // ];

  // Pass data to the page via props
  return { props: { photos } };
}

export default Index;
