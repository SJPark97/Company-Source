import React, { useState, useEffect } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import axios from "axios";

interface IProps {
  status: string;
  message: string;
  data: {
    corp_id: string;
    rate: string;
    analysis_method: number;
    analysis_name: string;
    result: {
      name: string;
      companyName: number;
      tempEverage: number;
      evaluation: string;
    }[];
  };
}

const tickFormatter = (tick: string) => `${tick}%`;
export default function ChartTest({ temp }: any) {
  // const [data, setData] = useState<Array<IProps>>([]);
  console.log(temp);

  // useEffect(() => {
  //   const data = [
  //     [
  //       {
  //         name: "비유동비율",
  //         영광기업: 191,
  //         산업평균: 150,
  //         평가: "불량",
  //       },
  //     ],
  //     [
  //       {
  //         name: "비유동장기적합률",
  //         영광기업: 66,
  //         산업평균: 90,
  //         평가: "양호",
  //       },
  //     ],
  //   ];
  //   setData(data);
  // }, []);

  return (
    <>
      <div>테스트</div>
      {/* <BarChart
        width={500}
        height={300}
        data={temp[0]}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis tickFormatter={tickFormatter} />
        <Tooltip />
        <Legend />
        <Bar dataKey="영광기업" fill="#8884d8" />
        <Bar dataKey="산업평균" fill="#82ca9d" />
      </BarChart>
      <BarChart
        width={500}
        height={300}
        data={temp[1]}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis tickFormatter={tickFormatter} />
        <Tooltip />
        <Legend />
        <Bar dataKey="영광기업" fill="#8884d8" />
        <Bar dataKey="산업평균" fill="#82ca9d" />
      </BarChart> */}
    </>
  );
}

export async function getServerSideProps() {
  console.log("엉");
  const temp = await axios.get(
    "http://192.168.31.142:8080/api/v1/analysis/101/234"
  );
  console.log(temp);
  return {
    props: {
      temp,
    },
  };
}
