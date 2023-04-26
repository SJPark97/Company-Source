import AnalysisTitle from "./AnalysisTitle";
import Chart101 from "./Chart101";
import Title from "./Title";

interface Iprops {
  companyId: string,
}

export default function FinancialAnalysis({ companyId }: Iprops) {
  return (
    <>
      <div className="flex flex-col">

        <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
          <div className="flex flex-col">
            <AnalysisTitle name="레버리지 분석" />
            <div>
              <div className="flex">
                <div className="p-20 bg-white m-30 rounded-10">
                  <Chart101 analysisCode="101" companyId={companyId as string} />
                </div>
                <div className="p-20 bg-white text-40 my-30 mr-30 rounded-10">
                  설명설명설명설명설명설명설명설명
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="mt-20"></div>

        <div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
          <div className="flex flex-col">
            <AnalysisTitle name="레버리지 분석" />
            <div>
              <div className="flex">
                <div className="p-20 bg-white text-40 m-30 rounded-10">
                  설명설명설명설명설명설명설명설명
                </div>
                <div className="p-20 bg-white my-30 mr-30 rounded-10">
                  <Chart101 analysisCode="101" companyId={companyId as string} />
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