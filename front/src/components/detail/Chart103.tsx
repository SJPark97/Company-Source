// import * as am5 from "@amcharts/amcharts5";
// import * as am5xy from "@amcharts/amcharts5/xy";
// import * as am5radar from "@amcharts/amcharts5/radar"
// import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
// import { useEffect } from "react";

// interface Iprops {
//   chartData: any
// }

// export default function Chart103({ chartData }: Iprops) {
//   useEffect(() => {

//     let root = am5.Root.new("chartdiv");

//     let chart = root.container.children.push(am5radar.RadarChart.new(root, {
//       panX: false,
//       panY: false,
//       startAngle: 160,
//       endAngle: 380
//     }));


//     // Create axis and its renderer
//     // https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Axes
//     let axisRenderer = am5radar.AxisRendererCircular.new(root, {
//       innerRadius: -40
//     });

//     axisRenderer.grid.template.setAll({
//       stroke: root.interfaceColors.get("background"),
//       visible: true,
//       strokeOpacity: 0.8
//     });

//     let xAxis = chart.xAxes.push(am5xy.ValueAxis.new(root, {
//       maxDeviation: 0,
//       min: -40,
//       max: 20,
//       strictMinMax: true,
//       renderer: axisRenderer
//     }));

//     let axisDataItem = xAxis.makeDataItem({});

//     let clockHand = am5radar.ClockHand.new(root, {
//       pinRadius: am5.percent(20),
//       radius: am5.percent(100),
//       bottomWidth: 40
//     })

//     let bullet = axisDataItem.set("bullet", am5xy.AxisBullet.new(root, {
//       sprite: clockHand
//     }));

//     xAxis.createAxisRange(axisDataItem);

//     let label = chart.radarContainer.children.push(am5.Label.new(root, {
//       fill: am5.color(0xffffff),
//       centerX: am5.percent(50),
//       textAlign: "center",
//       centerY: am5.percent(50),
//       fontSize: "3em"
//     }));

//     axisDataItem.set("value", 50);
//     bullet.get("sprite").on("rotation", function () {
//       let value = axisDataItem.get("value");
//       let fill = am5.color(0xD9D9D9);
//       // xAxis.axisRanges.each(function (axisRange) {
//       //   if (value >= axisRange.get("value") && value <= axisRange.get("endValue")) {
//       //     fill = axisRange.get("axisFill").get("fill");
//       //   }
//       // })

//       label.set("text", Math.round(value as number).toString());

//       clockHand.pin.animate({ key: "fill", to: fill, duration: Infinity, easing: am5.ease.out(am5.ease.cubic) })
//       clockHand.hand.animate({ key: "fill", to: fill, duration: Infinity, easing: am5.ease.out(am5.ease.cubic) })
//     });

//     setInterval(function () {
//       axisDataItem.animate({
//         key: "value",
//         to: 30,
//         duration: 0,
//         easing: am5.ease.out(am5.ease.cubic)
//       });
//     }, 0)

//     chart.bulletsContainer.set("mask", undefined);


//     // Create axis ranges bands
//     // https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Bands
//     let bandsData = [{
//       title: "Unsustainable",
//       color: "#ee1f25",
//       lowScore: -40,
//       highScore: -20
//     }, {
//       title: "Volatile",
//       color: "#f04922",
//       lowScore: -20,
//       highScore: 0
//     }, {
//       title: "Foundational",
//       color: "#fdae19",
//       lowScore: -20,
//       highScore: 20
//     }];

//     am5.array.each(bandsData, function (data) {
//       let axisRange = xAxis.createAxisRange(xAxis.makeDataItem({}));

//       axisRange.setAll({
//         value: data.lowScore,
//         endValue: data.highScore
//       });

//       axisRange.get("axisFill")?.setAll({
//         visible: true,
//         fill: am5.color(data.color),
//         fillOpacity: 0.8
//       });

//       axisRange.get("label")?.setAll({
//         text: data.title,
//         inside: true,
//         // radius: 15,
//         fontSize: "0.9em",
//         fill: root.interfaceColors.get("background")
//       });
//     });


//     // Make stuff animate on load
//     // chart.appear(1000, 100);
//   })

//   return (
//     <div id="chartdiv" style={{ width: "100%", height: "500px" }}></div>
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
} from "recharts";
import Legend from "./Legend";

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
      <div>

        <div className="flex flex-wrap justify-center text-12">
          {data
            ? data.data.result.map((item: any) => {
              return (
                <BarChart
                  width={140}
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
                  <Bar
                    dataKey={data.data.corp_name}
                    fill={
                      item["평가"] === "양호" ? "#82ca9d" : (item["평가"] === "불량" ? "red" : "#efad45")
                    }
                  />
                  <Bar dataKey="산업평균" fill="#8884d8" />
                </BarChart>
              );
            })
            : "데이터가 없어요 ㅠㅠ"}
        </div>
        <Legend compare="산업평균" />
      </div>
    </>
  );
}
