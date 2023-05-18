import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ReferenceLine,
} from "recharts";
import AnalysisResult from "./AnalysisResult";
import Legend from "./Legend";

interface Iprops {
  chartData: any;
}

export default function Chart104({
  chartData,
}: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  const formatYLabel = (value: string) => `${value}%`;
  return (
    <>
      <div>

        <div className="flex flex-wrap justify-center text-12">
          {data
            ? data.data.result.map((item: any, index: number) => {
              return (
                <div key={index}>
                  <BarChart
                    width={150}
                    height={300}
                    data={[item]}
                    margin={{
                      top: 40,
                      right: 20,
                      left: 0,
                      bottom: 40,
                    }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" tickMargin={16} interval={1} />
                    <YAxis tickFormatter={formatYLabel} />
                    <Tooltip wrapperStyle={{ zIndex: "50" }} />
                    {/* <Legend /> */}
                    <ReferenceLine y={0} stroke="#000" />
                    <Bar
                      dataKey={data.data.corp_name}
                      fill={
                        item["평가"] === "양호" ? "#82ca9d" : (item["평가"] === "불량" ? "red" : "#efad45")
                      }
                    />
                    <Bar dataKey="산업평균" fill="#8884d8" />
                  </BarChart>
                </div>
              );
            })
            : "데이터가 없어요 ㅠㅠ"}
        </div>
        <Legend compare="산업평균" />
      </div>
    </>
  );
}
