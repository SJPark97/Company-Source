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
import ComparisonLegend from "./ComparisonLegend";

interface Iprops {
  corpAName: string,
  corpBName: string,
  analysisMethod?: string,
  chartData: any
}

export default function ComparisonChart({ corpAName, corpBName, chartData }: Iprops) {

  // const [data, setData] = useState<any>();

  // useEffect(() => {
  //   setData(chartData);
  // }, []);

  return (
    <>
      <div>

        <div className="flex justify-center flex-nowrap text-12">
          {chartData
            ? chartData.map((item: any, index: number) => {
              return (
                <div key={index}>
                  <BarChart
                    width={120}
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
                    <ReferenceLine y={0} stroke="#000" />
                    <Bar dataKey={corpAName} fill="#82ca9d" width={10} />
                    <Bar dataKey={corpBName} fill="#8884d8" width={10} />
                  </BarChart>
                </div>
              );
            })
            : ""}
        </div>
        <ComparisonLegend corpAName={corpAName} corpBName={corpBName} />
      </div>
    </>
  );
}