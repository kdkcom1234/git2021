/** @type {import('next').NextConfig} */
module.exports = {
  reactStrictMode: true,
  // 외부도멩인 이미지에 next/image를 사용하려면
  // 이미지 도메인을 등록해주어야함
  images: { domains: ["d3o6g8deu522v9.cloudfront.net"] },
};
