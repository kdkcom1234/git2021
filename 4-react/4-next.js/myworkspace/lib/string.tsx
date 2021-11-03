const getTimeString = (unixtime: number) => {
  // 1초: 1000
  // 1분: 60 * 1000
  // 1시간: 60 * 60 * 1000
  // 1일: 24 * 60 * 60 * 1000
  const day = 24 * 60 * 60 * 1000;

  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);

  // 현재시간보다 24시간 이전이면 날짜를 보여주고
  // 현재시간보다 24시간 미만이면 시간을 보여줌
  return unixtime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
};

const dataUrlToFile = async (
  dataUrl: string,
  fileName: string,
  type: string
): Promise<File> => {
  const res: Response = await fetch(dataUrl);
  const blob: Blob = await res.blob(); // 응답을 2진 데이터 객체로 변환
  return new File([blob], fileName, { type }); // 비동기로 파일객체를 생성
};

export { getTimeString, dataUrlToFile };
