import AnalysisTitle from "./AnalysisTitle";
import Chart101 from "./Chart101";
import Chart103 from "./Chart103";
import Title from "./Title";

interface Iprops {
  companyId: string,
  analysisList: Array<any>,
}

const chartTypeList = [
  { analysisCode: "101", chartTag: Chart101 },
  { analysisCode: "103", chartTag: Chart103 },
];

export default function FinancialAnalysis({ companyId, analysisList }: Iprops) {


  return (
    <>
      <div className="flex flex-col">

        {/* {chartTypeList.map((chartType) => {
          return (
            <>
              <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
                <div className="flex flex-col">
                  <AnalysisTitle name="유동성 분석" />
                  <div>
                    <div className="flex">
                      <div className="p-20 bg-white m-30 rounded-10">
                        <chartType.chartTag analysisCode={chartType.analysisCode} companyId={companyId as string} chartData={analysisList} />
                      </div>
                      <div className="p-20 bg-white text-40 my-30 mr-30 rounded-10">
                        설명설명설명설명설명설명설명설명
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div className="mt-20"></div>
            </>
          )
        })} */}

        <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
          <div className="flex flex-col">
            <AnalysisTitle name={analysisList[0].data.analysis_name} rate={analysisList[0].data.rate} />
            <div>
              <div className="flex justify-around">
                <div className="p-20 bg-white m-30 rounded-10">
                  <Chart101 analysisCode="101" companyId={companyId as string} chartData={analysisList[0]} />
                </div>
                <div className="p-20 bg-white text-40 my-30 mr-30 rounded-10 max-w-[400px]">
                  설명설명설명설명설명설명설명설명
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="mt-20"></div>

        <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
          <div className="flex flex-col">
            <AnalysisTitle name={analysisList[1].data.analysis_name} rate={analysisList[1].data.rate} />
            <div>
              <div className="flex justify-around">
                <div className="p-20 bg-white text-40 m-30 rounded-10 max-w-[400px]">
                  설명설명설명설명설명설명설명설명
                </div>
                <div className="p-20 bg-white my-30 mr-30 rounded-10">
                  <Chart103 analysisCode="103" companyId={companyId as string} chartData={analysisList[1]} />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="mt-20"></div>

      </div>
    </>
  )
}