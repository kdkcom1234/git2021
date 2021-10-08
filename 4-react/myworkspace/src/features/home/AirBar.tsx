import { ApexOptions } from "apexcharts";
import axios from "axios";
import { useEffect, useState } from "react";
import Chart from "react-apexcharts";

const AirBar = () => {
  const [chartData, setChartData] = useState<{
    options: ApexOptions;
    series: {
      name: string;
      data: number[];
    }[];
  }>();

  const getData = async () => {
    const result = await axios.get<
      {
        dataTime: string;
        sidoName: string;
        cityName: string;
        pm10Value: number;
        pm25Value: number;
      }[]
    >(`${process.env.REACT_APP_API_BASE}/opendata/air/sido/current`);

    const data = result.data;

    // 차트의 옵션들, x축 문자열
    const options: ApexOptions = {
      title: {
        text: `서울 미세먼지 현황 (${result.data[0].dataTime})`,
      },
      xaxis: {
        // 배열 -> 배열, 요소의 개수가 동일함, 요소의 형식은 다름
        // map함수를 사용함
        // ["강남구", ... , "중랑구"]
        categories: data.map((item) => item.cityName),
      },
      fill: {
        // colors: ["#D9534F", '#1A73E8', '#B32824' ..., "#D9534F"]
        // colors: [첫번째 시리즈값25개, 두번째시리즈값25개]
        colors: [
          ({ value, seriesIndex }: { value: number; seriesIndex: number }) => {
            // seriesIndex: 0, PM10
            // seriesIndex: 1, PM2.5
            // console.log(value);
            // console.log(seriesIndex);

            let color = "";

            if (seriesIndex === 0) {
              // PM10일 때
              if (value <= 30) color = "rgb(50, 161, 255)";
              else if (value > 30 && value <= 80) color = "rgb(0, 199, 60)";
              else if (value > 80 && value <= 150) color = "rgb(253, 155, 90)";
              else color = "rgb(255, 89, 89)";
            } else {
              // PM2.5일 때
              if (value <= 15) color = "rgb(50, 161, 255)";
              else if (value > 15 && value <= 35) color = "rgb(0, 199, 60)";
              else if (value > 35 && value <= 75) color = "rgb(253, 155, 90)";
              else color = "rgb(255, 89, 89)";
            }
            return color;
          },
        ],
      },
    };
    // 실제 값들
    const series = [
      {
        name: "PM10",
        data: data.map((item) => item.pm10Value),
      },
      {
        name: "PM2.5",
        data: data.map((item) => item.pm25Value),
      },
    ];

    setChartData({ options, series });
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div>
      {chartData && (
        <Chart
          options={chartData?.options}
          series={chartData?.series}
          type="bar"
          width="1000"
          height="400"
        />
      )}
    </div>
  );
};

export default AirBar;
