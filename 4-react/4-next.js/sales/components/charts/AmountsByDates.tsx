import { useEffect, useState } from "react";

import dynamic from "next/dynamic";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

import { ApexOptions } from "apexcharts";

interface Prop {
  data: {
    date: string;
    amount: number;
  }[];
}

const AmountsByDates = ({ data }: Prop) => {
  // 차트 데이터 설정
  const chartData: {
    series: {
      name: string;
      data: number[];
    }[];
    options: ApexOptions;
  } = {
    // series: 실제 데이터 값
    // [{name: "일자", data: [8702, 3400 ...]}]
    series: [{ name: "일자", data: data.map((item) => item.amount) }],
    options: {
      // x축의 라벨
      xaxis: {
        // categories: ["01-01", "01-02" ...]
        categories: data.map((item) => item.date.substr(5, 5)),
      },
      // y축의 형식을 지정
      yaxis: {
        labels: {
          formatter: function (val: number) {
            return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
          },
        },
      },
    },
  };

  return (
    <div>
      {chartData && (
        <Chart
          options={chartData.options}
          series={chartData.series}
          type="line"
          height="200px"
        />
      )}
    </div>
  );
};

export default AmountsByDates;
