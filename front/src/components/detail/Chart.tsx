import { getTempChart } from "@/pages/detail/[searchdetail]";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useQuery } from "react-query";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend
} from "recharts";

interface ChartLabel {
  name: string,
  uv: number,
  pv: number,
  amt: number,
}

export default function Chart({ chartData }: any) {

  const { data: tempChart, isLoading, isError, error } = useQuery(['tempChart'], getTempChart, { refetchOnWindowFocus: false, staleTime: 10 * 1000, cacheTime: 30 * 1000, refetchInterval: 30 * 1000 })

  useEffect(() => {

    console.log(tempChart)

    const data = [
      {
        name: "Page A",
        uv: 4000,
        pv: 2400,
        amt: 2400
      },
      {
        name: "Page B",
        uv: 3000,
        pv: 1398,
        amt: 2210
      },
      {
        name: "Page C",
        uv: 2000,
        pv: 9800,
        amt: 2290
      },
      {
        name: "Page D",
        uv: 2780,
        pv: 3908,
        amt: 2000
      },
      {
        name: "Page E",
        uv: 1890,
        pv: 4800,
        amt: 2181
      },
      {
        name: "Page F",
        uv: 2390,
        pv: 3800,
        amt: 2500
      },
      {
        name: "Page G",
        uv: 3490,
        pv: 4300,
        amt: 2100
      }
    ];

    setData(data);
  }, [])

  const [data, setData] = useState<ChartLabel[]>([]);
  return (
    <BarChart
      width={400}
      height={300}
      data={data}
      margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" />
      <YAxis />
      <Tooltip />
      <Legend />
      <Bar dataKey="pv" fill="#8884d8" />
      <Bar dataKey="uv" fill="#82ca9d" />
    </BarChart >
  );
}
