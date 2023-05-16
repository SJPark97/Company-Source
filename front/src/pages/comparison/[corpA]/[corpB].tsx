import NavBar from "@/components/NavBar";
import CompanyComparisonWindow from "@/components/comparison/CompanyComparisonWindow";
import ComparisonChart from "@/components/comparison/ComparisonChart";
import ComparisonModal from "@/components/comparison/ComparisonModal";
import ComparisonTitle from "@/components/comparison/ComparisonTitle";
import SelectedCard from "@/components/comparison/SelectedCard";
import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/router";

interface corpAcorpBProps {
  data: any;
}

export default function corpB({ data }: corpAcorpBProps) {

  console.log(data)
  return (
    <>
      <NavBar />
      <div className="flex flex-col bg-no-repeat bg-cover bg-comparison h-[400px]">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">

          {/* 기업 비교 선택창 부분 */}
          <CompanyComparisonWindow />

          {/* 기업 비교 차트 부분 */}
          <div className="flex flex-col bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
            <div className="flex flex-col">
              <div className="ml-[3vw] mt-40 text-24 font-bold">
                <div className="flex">
                  <Image
                    src="/analysis.svg"
                    alt="analysis"
                    width={392}
                    height={392}
                    className="w-30 h-30"
                  />
                  <span className="ml-12">"{data.data.corpA.corpName}"기업과 "{data.data.corpB.corpName}"기업의 비교 결과</span>
                </div>
              </div>
              <div className="flex flex-col">
                <div className="flex flex-wrap mx-[1.5vw] mt-30">
                  {data.data.analysis.map((item: any, index: number) => {
                    return (
                      item.exist_all ?
                        <div key={index} className="flex flex-col mx-[1.5vw] mt-10 w-[45.7%]">
                          <ComparisonTitle name={item.analysis_name} description={item.analysisInfo.analysis_description} />
                          {/* 차트 부분 */}
                          <div className="h-auto pr-20 mt-10 mb-20 bg-white border-gray-500 rounded-5 border-1">
                            <ComparisonChart corpAName={data.data.corpA.corpName} corpBName={data.data.corpB.corpName} analysisMethod={item.analysisInfo.analysis_id} chartData={item.result} />
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
            </div >
          </div>
        </div>
      </div >

    </>
  )
}

export const getServerSideProps = async ({ params }: any) => {
  const { corpA: corpAId, corpB: corpBId } = params;
  const { data } = await axios.get(SERVER_URL + `/analysis/comparison/${corpAId}/${corpBId}`)

  return {
    props: {
      data: data
    }
  }

}