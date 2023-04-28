import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import AnalysisInfo from "./AnalysisInfo";

// interface ChartLabel {
//   name: string;
//   [key: string]: number;
// }

interface Iprops {
  chartData: any;
}

export default function Chart101({ chartData }: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  return (
    <>
      <AnalysisInfo rate={chartData.data.rate} />
      <div className="flex flex-wrap content-start justify-center text-12">
        {data
          ? data.data.result.map((item: any) => {
            console.log(item);
            return (
              <>
                <BarChart
                  width={120}
                  height={300}
                  data={[item]}
                  margin={{
                    top: 5,
                    right: 10,
                    left: 10,
                    bottom: 5,
                  }}
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="name" tickMargin={16} />
                  <YAxis />
                  <Tooltip />
                  {/* <Legend /> */}
                  <Bar
                    dataKey={Object.keys(item)[0]}
                    fill={
                      Object.values(item)[3] === "양호" ? "#8884d8" : ("불량" ? "red" : "orange")
                    }
                  />
                  <Bar dataKey="산업평균" fill="#82ca9d" />
                </BarChart>
              </>
            );
          })
          : "데이터가 없어요 ㅠㅠ"}
      </div>
    </>
  );
}
