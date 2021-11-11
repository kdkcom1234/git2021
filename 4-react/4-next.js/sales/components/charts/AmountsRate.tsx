import { useEffect, useState } from "react";

import dynamic from "next/dynamic";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

import { ApexOptions } from "apexcharts";

interface Prop {
  amount: number; // 실제값
  target: number; // 목표값
}

const AmountsRate = ({ amount, target }: Prop) => {
  // 목표 대비 비율
  const rate = (amount / target) * 100;

  // 컬러값 설정
  let color: string = "";
  if (rate >= 0 && rate < 25) {
    color = "#aa0000";
  } else if (rate >= 25 && rate < 75) {
    color = "#aaaa00";
  } else {
    color = "#00aa00";
  }

  // 차트 데이터 설정
  const chartData: {
    series: number[];
    options: ApexOptions;
  } = {
    series: [rate],
    options: {
      plotOptions: {
        radialBar: {
          startAngle: -90,
          endAngle: 90,
          dataLabels: {
            value: {
              offsetY: -10,
              fontSize: "20px",
            },
          },
        },
      },
      colors: [color],
      labels: [""],
    },
  };

  return (
    <div>
      {chartData && (
        <Chart
          options={chartData.options}
          series={chartData.series}
          type="radialBar"
          height="600px"
        />
      )}
    </div>
  );
};

export default AmountsRate;
