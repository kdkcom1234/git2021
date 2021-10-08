import { ApexOptions } from "apexcharts";
import { useEffect, useRef, useState } from "react";
import Chart from "react-apexcharts";
// import lineData from "./airLineData";
import barData from "./airBarData";
import axios from "axios";

const AirLine = () => {
  const [chartData, setChartData] = useState<{
    options: ApexOptions;
    series: {
      name: string;
      data: number[];
    }[];
  }>();

  const cityRef = useRef<HTMLSelectElement>(null);

  const getData = async () => {
    const result = await axios.get<
      {
        dataTime: string;
        sidoName: string;
        cityName: string;
        pm10Value: number;
        pm25Value: number;
      }[]
    >(
      `${process.env.REACT_APP_API_BASE}/opendata/air/sido/current/${cityRef.current?.value}`
    );

    const data = result.data;
    // const data = lineData;

    const options: ApexOptions = {
      xaxis: {
        // 배열 -> 배열, 요소의 개수가 동일함, 요소의 형식은 다름
        // map함수를 사용함
        // ["2021-10-05 23:00", ... , "2021-10-06 10:00"]
        categories: data
          .map((item) => item.dataTime)
          .sort()
          .map((item) => item.substr(11, 5)),
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
        <>
          <div
            style={{ width: "1000px" }}
            className="d-flex justify-content-end"
          >
            <select
              className="form-select form-select-sm me-2"
              style={{ width: "110px" }}
              onChange={() => {
                getData();
              }}
              ref={cityRef}
            >
              {barData
                .map((item) => item.cityName)
                .map((city) => (
                  <option key={`sel-${city}`} value={city}>
                    {city}
                  </option>
                ))}
            </select>
          </div>
          <Chart
            options={chartData.options}
            series={chartData.series}
            type="line"
            width="1000"
            height="300"
          />
        </>
      )}
    </div>
  );
};

export default AirLine;
