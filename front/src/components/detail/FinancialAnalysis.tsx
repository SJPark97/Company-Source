import AnalysisTitle from "./AnalysisTitle";
import Chart101 from "./Chart101";
import Chart103 from "./Chart103";
import Chart104 from "./Chart104";
import Chart109 from "./Chart109";
import Chart110 from "./Chart110";
import Chart111 from "./Chart111";
import Chart405 from "./Chart405";
import Chart113 from "./Chart113";
import dynamic from "next/dynamic";
import Chart102 from "./Chart102";
import Chart105 from "./Chart105";
import Chart106 from "./Chart106";
import Chart108 from "./Chart108";
import Chart114 from "./Chart114";
import Chart408 from "./Chart408";
import Chart303 from "./Chart303";


interface Iprops {
  companyId: string,
  analysisList: Array<any>,
}

interface iChartCode {
  [key: string]: any
}

// const Chart101 = dynamic(() => import('./Chart101'), { ssr: false })

const chartCode: iChartCode = {
  "101": Chart101,
  "102": Chart102,
  "103": Chart103,
  "104": Chart104,
  "105": Chart105,
  "106": Chart106,
  "108": Chart108,
  "109": Chart109,
  "110": Chart110,
  "111": Chart111,
  "113": Chart113,
  // "114": Chart114,
  "303": Chart303,
  "405": Chart405,
  "408": Chart408,
}

export default function FinancialAnalysis({ analysisList }: Iprops) {

  return (
    <>
      <div className="flex flex-col">
        <div className="flex flex-wrap mx-[1.5vw] mt-30">

          {analysisList.map((analysisItem: any, index) => {
            const ChartComponent = chartCode[analysisItem.data.analysis_method]
            let width = { chart: "", info: "" }
            if (analysisItem.data.result.length < 4) {
              width.chart = "w-[45.7%]";
              width.info = "w-[400px]";
            } else {
              width.chart = "w-[95.7%]";
              width.info = "min-w-[600px] max-w-[1000px]";
            }
            return (
              analysisItem.data.exist_all ?
                <div key={index} className={"flex flex-col mx-[1.5vw] mt-10 " + width.chart}>
                  <AnalysisTitle name={analysisItem.data.analysis_name} rate={analysisItem.data.rate} description={analysisItem.data.analysisInfo.analysis_description} width={width.info} />
                  {/* 차트 부분 */}
                  <div className="h-auto pr-20 mt-10 mb-20 bg-white border-gray-500 rounded-5 border-1">
                    <ChartComponent chartData={analysisItem} />
                  </div>
                  <div className="mt-20"></div>
                </div>
                :
                ""
            )
          }
          )}
        </div>
      </div >
    </>
  )
}