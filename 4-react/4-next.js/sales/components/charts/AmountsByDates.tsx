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
    series: [{ name: "date", data: data.map((item) => item.amount) }],
    options: {
      xaxis: {
        categories: data.map((item) => item.date.substr(5, 5)),
      },
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
