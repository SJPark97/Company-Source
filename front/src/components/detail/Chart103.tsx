import getData from "@/hooks/getData";
import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import { useQuery } from "react-query";

interface Iprops {
  analysisCode: string;
  companyId: string;
}

export default function Chart103({ analysisCode, companyId }: Iprops) {
  const { data } = useQuery<any>(
    ["analysis", analysisCode],
    () => getData(analysisCode, companyId),
    {
      refetchOnWindowFocus: false,
    }
  );
  if (data) {
    console.log(data.data.data, "here");
  }

  const formatYLabel = (value: string) => `${value}%`;
  return (
    <>
      <div className="flex flex-wrap">
        <BarChart
          width={500}
          height={300}
          data={data && [data.data.data.result[0]]}
          margin={{
            top: 5,
            right: 30,
            left: 20,
            bottom: 5,
          }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis tickFormatter={formatYLabel} />
          <Tooltip />
          <Legend />
          <Bar dataKey="잡탕마을" fill="#8884d8" />
          <Bar dataKey="산업평균" fill="#82ca9d" />
        </BarChart>
        <BarChart
          width={500}
          height={300}
          data={data && [data.data.data.result[1]]}
          margin={{
            top: 5,
            right: 30,
            left: 20,
            bottom: 5,
          }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis tickFormatter={formatYLabel} />
          <Tooltip />
          <Legend />
          <Bar dataKey={data && `${data.data.data.corp_name}`} fill="#8884d8" />
          <Bar dataKey="산업평균" fill="#82ca9d" />
        </BarChart>
      </div>
    </>
  );
}
