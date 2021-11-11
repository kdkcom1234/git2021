import Layout from "../../components/layout";
import Image from "next/image";

import axios from "axios";
import { GetServerSideProps } from "next";

interface PublicPhoto {
  albumId: number;
  id: number;
  title: string;
  url: string;
  thumbnailUrl: string;
}

interface DetailProp {
  photo: PublicPhoto;
}

const PublicPhotoDetail = ({ photo }: DetailProp) => {
  return (
    <Layout>
      <div className="card">
        {/* 컨텐트 wrapper -- 시작 */}
        <div style={{ cursor: "pointer" }}>
          <Image
            src={photo.thumbnailUrl}
            className="card-img-top"
            alt={photo.title}
            /* 이미지 크기에 맞게 가운데부분 노출 */
            layout="responsive"
            objectFit="cover"
            /* ------------------------------- */
            width={220}
            height={150}
          />
          <div className="card-body">
            <h5 className="card-title">{photo.title}</h5>
          </div>
        </div>
        {/* 컨텐트 wrapper -- 끝 */}
      </div>
    </Layout>
  );
};

export const getServerSideProps: GetServerSideProps = async (context) => {
  // SSR일 때 매개변수를 받는 방법
  // /public-photos/id
  const id = context.params?.id;

  // Fetch data from external API
  const res = await axios.get<PublicPhoto[]>(
    `https://jsonplaceholder.typicode.com/photos/${id}`
  );
  const photo = res.data;
  // const photo = {
  //   albumId: 1,
  //   id: 1,
  //   title: "accusamus beatae ad facilis cum similique qui sunt",
  //   url: "https://via.placeholder.com/600/92c952",
  //   thumbnailUrl: "https://via.placeholder.com/150/92c952",
  // };
  // Pass data to the page via props
  return { props: { photo } };
};

export default PublicPhotoDetail;
