import Image from "next/image";
import AnalysisTitle from "./AnalysisTitle";
// import Chart101 from "./Chart101";
import Chart103 from "./Chart103";
import Chart104 from "./Chart104";
import Chart109 from "./Chart109";
import Chart110 from "./Chart110";
import Chart111 from "./Chart111";
import Chart405 from "./Chart405";
import Chart113 from "./Chart113";
import AnalysisResult from "./AnalysisResult";
import Legend from "./Legend";
import dynamic from "next/dynamic";


interface Iprops {
  companyId: string,
  analysisList: Array<any>,
}

interface iChartCode {
  [key: string]: any
}

const Chart101 = dynamic(() => import('./Chart101'), { ssr: false })

const chartCode: iChartCode = {
  "101": Chart101,
  "103": Chart103,
  "104": Chart104,
  "109": Chart109,
  "110": Chart110,
  "111": Chart111,
  "405": Chart405,
  "113": Chart113,
}

export default function FinancialAnalysis({ analysisList }: Iprops) {
  return (
    <>
      <div className="flex flex-col">
        <div className="flex flex-wrap mx-[1.5vw] mt-30">

          {analysisList.map((analysisItem: any) => {
            const ChartComponent = chartCode[analysisItem.data.analysis_method]
            return (
              analysisItem.data.is_exist_all ?
                <>
                  <div className="flex flex-col mx-[1.5vw] mt-10 w-[45.7%]">
                    <AnalysisTitle name={analysisItem.data.analysis_name} rate={analysisItem.data.rate} description={analysisItem.data.analysisInfo.analysis_description} />
                    {/* 차트 부분 */}
                    <div className="h-auto pr-20 mt-10 mb-20 bg-white border-gray-500 rounded-5 border-1">
                      <ChartComponent chartData={analysisItem} />
                    </div>
                  </div>
                  <div className="mt-20"></div>
                </> :
                ""
            )
          }
          )}
        </div>


        <div className="mt-20"></div>

      </div >
    </>
  )
}