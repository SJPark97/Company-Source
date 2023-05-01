import Image from "next/image";
import AnalysisTitle from "./AnalysisTitle";
import Chart101 from "./Chart101";
import Chart103 from "./Chart103";
import AnalysisRate from "./AnalysisInfo";


interface Iprops {
  companyId: string,
  analysisList: Array<any>,
}

interface iChartCode {
  [key: string]: any
}

const chartCode: iChartCode = {
  "101": Chart101,
  "103": Chart103
}

export default function FinancialAnalysis({ companyId, analysisList }: Iprops) {
  console.log(analysisList)
  let i = 1;
  return (
    <>
      <div className="flex flex-col">

        {analysisList.map((analysisItem: any) => {
          const ChartComponent = chartCode[analysisItem.data.analysis_method]
          i = i + 1;
          return (
            analysisItem.data.is_exist_all ?
              <>
                <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
                  <div className="flex flex-col">
                    <AnalysisTitle name={analysisItem.data.analysis_name} info={analysisItem.data.analysis_detail} />
                    <div>
                      <div className="flex">
                        {i % 2 ?
                          <>
                            {/* 설명 부분 */}
                            <div className="w-full p-20 overflow-y-auto bg-white m-30 rounded-10">
                              <div>지표 설명</div>
                              <pre className="font-sans whitespace-pre-wrap text-24">{analysisItem.data.analysisInfo.analysis_detail}</pre>
                            </div>
                            {/* 차트 부분 */}
                            <div className="w-full p-20 bg-white my-30 mr-30 rounded-10">
                              <ChartComponent chartData={analysisItem} />
                            </div>
                          </>
                          : <>
                            {/* 차트 부분 */}
                            <div className="w-full p-20 bg-white m-30 rounded-10">
                              <ChartComponent chartData={analysisItem} />
                            </div>
                            {/* 설명 부분 */}
                            <div className="w-full p-20 bg-white my-30 mr-30 rounded-10">
                              <pre className="font-sans whitespace-pre-wrap text-24">{analysisItem.data.analysisInfo.analysis_detail}</pre>
                            </div>
                          </>
                        }

                      </div>
                    </div>
                  </div>
                </div >

                <div className="mt-20"></div>
              </> :
              ""
          )
        }
        )}
        <div className="mt-20"></div>

      </div >
    </>
  )
}