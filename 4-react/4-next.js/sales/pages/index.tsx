import { useEffect, useState } from "react";

import dynamic from "next/dynamic";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

import { ApexOptions } from "apexcharts";
import AmountsRate from "../components/charts/AmountsRate";
import AmountsByCategories from "../components/charts/AmountsByCategories";
import AmountsByDates from "../components/charts/AmountsByDates";
import axios from "axios";

const Home = () => {
  const [data, setData] = useState<{
    amounts: number;
    amountsByDates: {
      date: string;
      amount: number;
    }[];
    amountsByCategories: {
      category: string;
      amount: number;
    }[];
  }>();

  const getData = async () => {
    // const result = await axios.get<typeof data>(
    //   "http://localhost:5050/sales-orders/stats?sd=1997-01-01&ed=1997-01-31"
    // );
    const sample: typeof data = {
      amounts: 83351,
      amountsByDates: [
        {
          date: "1997-01-01",
          amount: 8702,
        },
        {
          date: "1997-01-02",
          amount: 3400,
        },
        {
          date: "1997-01-03",
          amount: 3357,
        },
        {
          date: "1997-01-06",
          amount: 500,
        },
        {
          date: "1997-01-07",
          amount: 4043,
        },
        {
          date: "1997-01-08",
          amount: 2017,
        },
        {
          date: "1997-01-09",
          amount: 396,
        },
        {
          date: "1997-01-10",
          amount: 2483,
        },
        {
          date: "1997-01-13",
          amount: 460,
        },
        {
          date: "1997-01-14",
          amount: 2906,
        },
        {
          date: "1997-01-15",
          amount: 118,
        },
        {
          date: "1997-01-16",
          amount: 15021,
        },
        {
          date: "1997-01-17",
          amount: 2288,
        },
        {
          date: "1997-01-20",
          amount: 2760,
        },
        {
          date: "1997-01-21",
          amount: 3963,
        },
        {
          date: "1997-01-22",
          amount: 62,
        },
        {
          date: "1997-01-23",
          amount: 15644,
        },
        {
          date: "1997-01-24",
          amount: 600,
        },
        {
          date: "1997-01-27",
          amount: 1226,
        },
        {
          date: "1997-01-28",
          amount: 240,
        },
        {
          date: "1997-01-29",
          amount: 2180,
        },
        {
          date: "1997-01-30",
          amount: 10395,
        },
        {
          date: "1997-01-31",
          amount: 590,
        },
      ],
      amountsByCategories: [
        {
          category: "beverages",
          amount: 30330,
        },
        {
          category: "condiments",
          amount: 7113,
        },
        {
          category: "confections",
          amount: 11954,
        },
        {
          category: "dairyproducts",
          amount: 12311,
        },
        {
          category: "grainscereals",
          amount: 5721,
        },
        {
          category: "meatpoultry",
          amount: 9714,
        },
        {
          category: "produce",
          amount: 3623,
        },
        {
          category: "seafood",
          amount: 2585,
        },
      ],
    };
    setData(sample);
    // setData(result.data);
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div
      style={{ display: "flex", justifyContent: "center", flexWrap: "wrap" }}
    >
      <div style={{ width: "50%" }}>
        <h2 style={{ textAlign: "center" }}>매출목표 달성율</h2>
        {data && <AmountsRate target={100000} amount={data.amounts} />}
      </div>
      <div style={{ width: "50%" }}>
        <h2 style={{ textAlign: "center" }}>제품별 매출액</h2>
        {data && <AmountsByCategories data={data.amountsByCategories} />}
      </div>
      <div style={{ width: "100%" }}>
        <h2 style={{ textAlign: "center" }}>일별 매출액</h2>
        {data && <AmountsByDates data={data.amountsByDates} />}
      </div>
    </div>
  );
};

export default Home;
