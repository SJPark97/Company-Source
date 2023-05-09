// import React, { useEffect, useRef } from "react";
// import Chart, { ChartConfiguration } from "chart.js/auto";

// interface Iprops {
//   chartData: any;
// }

// export default function Chart101({ chartData }: Iprops) {
//   console.log(chartData.data.result[0])
//   console.log(chartData)
//   const canvasEl = useRef<any>(null);

//   const colors = {
//     purple: {
//       default: "rgba(149, 76, 233, 1)",
//       half: "rgba(149, 76, 233, 0.5)",
//       quarter: "rgba(149, 76, 233, 0.25)",
//       zero: "rgba(149, 76, 233, 0)"
//     },
//     indigo: {
//       default: "rgba(80, 102, 120, 1)",
//       quarter: "rgba(80, 102, 120, 0.25)"
//     }
//   };

//   useEffect(() => {
//     const ctx = canvasEl.current.getContext("2d");
//     const gradient = ctx.createLinearGradient(0, 16, 0, 600);
//     gradient.addColorStop(0, colors.purple.half);
//     gradient.addColorStop(0.65, colors.purple.quarter);
//     gradient.addColorStop(1, colors.purple.zero);

//     const data = {
//       labels: chartData.data.result.map((item: any) => {
//         return item.name
//       }),
//       datasets: [{
//         type: 'bar',
//         label: 'Bar Dataset',
//         data: chartData.data.result.map((item: any) => {
//           return item[chartData.data.corp_name]
//         }),
//         borderColor: 'rgb(255, 99, 132)',
//         backgroundColor: 'rgba(255, 99, 132, 0.2)'
//       }, {
//         type: 'line',
//         label: 'Line Dataset',
//         data: chartData.data.result.map((item: any) => {
//           return item["산업평균"]
//         }),
//         fill: false,
//         borderColor: 'rgb(54, 162, 235)'
//       }]
//     };
//     const config = {
//       type: 'scatter',
//       data: data,
//       options: {
//         scales: {
//           y: {
//             beginAtZero: true
//           }
//         }
//       }
//     };
//     const myLineChart = new Chart(ctx, config as ChartConfiguration);

//     return function cleanup() {
//       myLineChart.destroy();
//     };
//   });

//   return (
//     <>
//       <div>
//         <canvas ref={canvasEl} height="210" />
//       </div>
//     </>
//   );
// }



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
import Legend from "./Legend";

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
      <div>

        <div className="flex justify-center flex-nowrap text-12">
          {data
            ? data.data.result.map((item: any) => {
              return (
                <>
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
                    <Bar
                      dataKey={data.data.corp_name}
                      fill={
                        item["평가"] === "양호" ? "#82ca9d" : (item["평가"] === "불량" ? "red" : ("#efad45"))
                      }
                    />
                    <Bar dataKey="산업평균" fill="#8884d8" />
                  </BarChart>
                </>
              );
            })
            : "데이터가 없어요 ㅠㅠ"}
        </div>
        <Legend compare="산업평균" />
      </div>
    </>
  );
}
