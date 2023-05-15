import NavBar from "@/components/NavBar";
import ComparisonChart from "@/components/comparison/ComparisonChart";
import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/router";

interface corpAcorpBProps {
  data: any;
}

export default function corpB({ data }: corpAcorpBProps) {
  const router = useRouter();
  const { corpA: corpAId, corpB: corpBId } = router.query;

  console.log(data)
  return (
    <>
      <NavBar />
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

            <div className="flex flex-col">

              {data.data.analysis.map((item: any) => {
                return (
                  <>
                    <ComparisonChart corpAName={data.data.corpA.corpName} corpBName={data.data.corpB.corpName} analysisMethod={item.analysisInfo.analysis_id} chartData={item.result} />
                  </>
                )
              })}
              {/* <ComparisonChart corpAName={data.data.corpA.corpName} corpBName={data.data.corpB.corpName} chartData={data.data.analysis101.result} /> */}

            </div >

          </div>
        </div >
      </div>

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