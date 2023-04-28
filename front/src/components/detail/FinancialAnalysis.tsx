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
            <>
              <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
                <div className="flex flex-col">
                  <AnalysisTitle name={analysisItem.data.analysis_name} />
                  <div>
                    <div className="flex">
                      {i % 2 ?
                        <>
                          <div className="w-full p-20 bg-white m-30 rounded-10">
                            설명설명설명설명설명설명설명설명
                          </div>
                          <div className="w-full p-20 bg-white my-30 mr-30 rounded-10">
                            <ChartComponent chartData={analysisItem} />
                          </div>
                        </>
                        : <>
                          <div className="w-full p-20 bg-white m-30 rounded-10">
                            <ChartComponent chartData={analysisItem} />
                          </div>
                          <div className="w-full p-20 bg-white my-30 mr-30 rounded-10">
                            설명설명설명설명설명설명설명설명
                          </div>
                        </>
                      }

                    </div>
                  </div>
                </div>
              </div>

              <div className="mt-20"></div>
            </>
          )
        })}

        <div className="mt-20"></div>

      </div>
    </>
  )
}