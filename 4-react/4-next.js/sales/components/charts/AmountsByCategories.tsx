import { useEffect, useState } from "react";

import dynamic from "next/dynamic";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

import { ApexOptions } from "apexcharts";

function numberWithCommas(x: number) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

interface Prop {
  data: {
    category: string;
    amount: number;
  }[];
}

const AmountsByCategories = ({ data }: Prop) => {
  // 차트 데이터 설정
  const chartData: {
    series: {
      name: string;
      data: number[];
    }[];
    options: ApexOptions;
  } = {
    // [{name: "제품군", data: [30330, 7113 ...]}]
    series: [{ name: "제품군", data: data.map((item) => item.amount) }],
    options: {
      xaxis: {
        // categories: ["beverages", "condiments" ...]
        categories: data.map((item) => item.category),
      },
      // y축 형식 지정
      // yaxis: {
      //   labels: {
      //     formatter: function (val: number) {
      //       return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      //     },
      //   },
      // },
    },
  };

  return (
    <div>
      {chartData && (
        <Chart
          options={chartData.options}
          series={chartData.series}
          type="bar"
          height="350px"
        />
      )}
    </div>
  );
};

export default AmountsByCategories;
