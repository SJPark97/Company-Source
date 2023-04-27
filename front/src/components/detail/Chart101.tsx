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

// interface ChartLabel {
//   name: string;
//   [key: string]: number;
// }

interface Iprops {
  analysisCode: string;
  companyId: string;
  chartData: any;
}

export default function Chart101({ chartData }: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  return (
    <>
      <div className="flex flex-wrap text-15">
        {data
          ? data.data.result.map((item: any) => {
              console.log(item);
              return (
                <div>
                  <BarChart
                    width={150}
                    height={300}
                    data={[item]}
                    margin={{
                      top: 5,
                      right: 30,
                      left: 20,
                      bottom: 5,
                    }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Bar
                      dataKey={Object.keys(item)[0]}
                      fill={
                        Object.values(item)[3] === "양호" ? "#8884d8" : "red"
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
