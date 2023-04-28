import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import AnalysisInfo from "./AnalysisInfo";

interface Iprops {
  chartData: any;
}

export default function Chart103({
  chartData,
}: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  const formatYLabel = (value: string) => `${value}%`;
  return (
    <>
      <AnalysisInfo rate={chartData.data.rate} />
      <div className="flex flex-wrap justify-center text-12">
        {data
          ? data.data.result.map((item: any) => {
            console.log(item);
            return (
              <div>
                <BarChart
                  width={140}
                  height={300}
                  data={[item]}
                  margin={{
                    top: 5,
                    right: 30,
                    left: 20,
                    bottom: 5,
                  }}
                  className="-ml-10"
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="name" tickMargin={16} />
                  <YAxis tickFormatter={formatYLabel} />
                  <Tooltip />
                  <Bar
                    dataKey={Object.keys(item)[0]}
                    fill={
                      Object.values(item)[3] === "양호" ? "#8884d8" : ("불량" ? "red" : "orange")
                    }
                  />
                  <Bar dataKey="산업평균" fill="#82ca9d" />
                </BarChart>
              </div>
            );
          })
          : "데이터가 없어요 ㅠㅠ"}
      </div>
    </>
  );
}
