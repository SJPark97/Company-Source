// import { getTempChart } from "@/pages/detail/[searchdetail]";
import getData from "@/hooks/getData";
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
  잡탕마을: number,
  산업평균: number,
}

interface Iprops {
  analysisCode: string,
  companyId: string
}


export default function Chart({ analysisCode, companyId }: Iprops) {
  const { data } = useQuery<any>(["analysis", analysisCode], () => getData(analysisCode, companyId), { refetchOnWindowFocus: false })
  const [chartData, setchartData] = useState<ChartLabel[]>([]);
  return (
    <BarChart
      width={400}
      height={300}
      data={data && data.data.data.result}
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
      <Bar dataKey="잡탕마을" fill="#8884d8" />
      <Bar dataKey="산업평균" fill="#82ca9d" />
    </BarChart >
  );
}
