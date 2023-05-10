import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
} from "recharts";
import Legend from "./Legend";

interface Iprops {
  chartData: any;
}

export default function Chart405({ chartData }: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  return (
    <>
      <div>

        <div className="flex justify-center flex-nowrap text-12">
          {data
            ? data.data.result.map((item: any, index: number) => {
              return (
                <div key={index}>
                  <BarChart
                    width={140}
                    height={300}
                    data={[item]}
                    margin={{
                      top: 40,
                      right: 30,
                      left: 0,
                      bottom: 40,
                    }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" tickMargin={16} interval={1} />
                    <YAxis />
                    <Tooltip wrapperStyle={{ zIndex: "50" }} />
                    {/* <Legend /> */}
                    <Bar
                      dataKey={data.data.corp_name}
                      fill={
                        data.data.rate === "정상" ? "#82ca9d" : (data.data.rate === "부실" ? "red" : "#efad45")
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
