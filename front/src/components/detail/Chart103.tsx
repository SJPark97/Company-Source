import React, { PureComponent } from "react";
import axios from "axios";
import {
  BarChart,
  Bar,
  Cell,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import useChartQueries from "@/hooks/useChartQueries";
import { useRouter } from "next/router";
import { useQuery, useQueryClient } from "react-query";
import { SERVER_URL } from "@/utils/url";

interface Iprops {
  status: string;
  data: {
    results: Array<any>;
  };
}
// export default function Chart103({ props }: Iprops) {
export default function Chart103() {
  const queryClient = useQueryClient();
  const { data, isLoading, error } = useQuery('["analysis", 101]');
  console.log(data);
  //   console.log(queryClient, "22222222222222222222");
  //   const { isLoading, data, error } = useQuery(["\\analysis\\", 101], () => {
  //     return queryClient.getQueryData(["\\analysis\\", 101]);
  //   });
  //   console.log(data, "here");

  //   console.log(router);
  //   const chartQuery = useChartQueries;

  const formatYLabel = (value) => `${value}%`;
  return (
    <>
      <ResponsiveContainer width="100%" height="100%">
        <BarChart
          width={500}
          height={300}
          //   data={data.result[0]}
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
      </ResponsiveContainer>
      <ResponsiveContainer width="100%" height="100%">
        <BarChart
          width={500}
          height={300}
          //   data={data.result[1]}
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
      </ResponsiveContainer>
    </>
  );
}
