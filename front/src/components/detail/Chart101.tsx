// import React, { useEffect, useRef } from "react";
// import Chart, { ChartConfiguration } from "chart.js/auto";

// interface Iprops {
//   chartData: any;
// }

// export default function Chart101({ chartData }: Iprops) {
//   console.log(chartData.data.result[0])
//   console.log(chartData)
//   const canvasEl = useRef<any>(null);

//   useEffect(() => {
//     const ctx = canvasEl.current.getContext("2d");


//     const data = {
//       labels: chartData.data.result.map((item: any) => {
//         return item.name
//       }),
//       datasets: [
//         {
//           label: "양호",
//           data: chartData.data.result.map((item: any) => {
//             return item["평가"] == "양호" && item[chartData.data.corp_name]
//           }),
//           borderColor: 'rgb(255, 99, 132)',
//           backgroundColor: '#82ca9d',
//           stack: 'Stack 0'
//         },
//         {
//           label: "불량",
//           data: chartData.data.result.map((item: any) => {
//             return item["평가"] == "불량" && item[chartData.data.corp_name]
//           }),
//           borderColor: 'rgb(255, 99, 132)',
//           backgroundColor: '#FF0000',
//           stack: 'Stack 0'
//         },
//         {
//           label: "보통",
//           data: chartData.data.result.map((item: any) => {
//             return item["평가"] == "보통" && item[chartData.data.corp_name]
//           }),
//           borderColor: 'rgb(255, 99, 132)',
//           backgroundColor: '#EFAD45',
//           stack: 'Stack 0'
//         },
//         {
//           label: "산업평균",
//           data: chartData.data.result.map((item: any) => {
//             return item["산업평균"]
//           }),
//           borderColor: 'rgb(54, 162, 235)',
//           backgroundColor: '#8884d8',
//           stack: 'Stack 1'
//         }
//       ]
//     };


//     const config = {
//       type: 'bar',
//       data: data,
//       options: {
//         plugins: {
//           legend: {
//             position: 'bottom'
//           },
//           title: {
//             display: true,
//             text: 'Chart.js Bar Chart - Stacked'
//           },
//         },
//         responsive: true,
//         interaction: {
//           intersect: false,
//         },
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
