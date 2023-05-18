import NavBar from "@/components/NavBar";
import CompanyComparisonWindow from "@/components/comparison/CompanyComparisonWindow";
import ComparisonChart from "@/components/comparison/ComparisonChart";
import ComparisonTitle from "@/components/comparison/ComparisonTitle";
import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import Head from "next/head";
import Image from "next/image";


interface corpAcorpBProps {
  data: any;
}

export default function corpB({ data }: corpAcorpBProps) {

  // const [showMessage, setShowMessage] = useState<boolean>();

  return (
    <>
      <Head>
        <title>{`컴퍼니소스 | ${data.data.corpA.corpName} vs ${data.data.corpB.corpName}`}</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content={`${data.data.corpA.corpName}기업과
          ${data.data.corpB.corpName}기업의 비교 결과}`}
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content={`https://company-source.com/${data.data.corpA.corpId}/${data.data.corpB.corpId}`} />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
        <meta
          name="google-site-verification"
          content="0FzOO996BLTIEWFgwlmmYv-F1WmHiM6SrbwEbK9-p3k"
        />
      </Head>
      <NavBar />
      <div className="flex flex-col bg-no-repeat bg-cover bg-comparison h-[400px]">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">


          {/* 기업 비교 선택창 부분 */}
          <CompanyComparisonWindow />
          {/* <ComparisonStartMessage /> */}


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
                  <span className="ml-12">
                    "{data.data.corpA.corpName}"기업과 "
                    {data.data.corpB.corpName}"기업의 비교 결과
                  </span>
                </div>
              </div>
              <div className="flex flex-col">
                <div className="flex flex-wrap mx-[1.5vw] mt-30">
                  {data.data.analysis.map((item: any, index: number) => {
                    let width = { chart: "", info: "" }
                    if (item.result.length < 4) {
                      width.chart = "w-[45.7%]";
                      if (item.analysis_method === "114") {
                        width.info = "w-[1000px]";
                      } else {
                        width.info = "w-[400px]";
                      }
                    } else {
                      width.chart = "w-[95.7%]";
                      width.info = "min-w-[600px] max-w-[1000px]";
                    }
                    return item.exist_all ? (
                      <div
                        key={index}
                        className={"flex flex-col mx-[1.5vw] mt-10 " + width.chart}
                      >
                        <ComparisonTitle
                          name={item.analysis_name}
                          description={item.analysisInfo.analysis_description}
                          width={width.info}
                          analysisInfo={item.analysisInfo}
                        />
                        {/* 차트 부분 */}
                        <div className="h-auto pr-20 mt-10 mb-20 bg-white border-gray-500 rounded-5 border-1">
                          <ComparisonChart
                            corpAName={data.data.corpA.corpName}
                            corpBName={data.data.corpB.corpName}
                            analysisMethod={item.analysisInfo.analysis_id}
                            chartData={item.result}
                          />
                        </div>
                        <div className="mt-20"></div>
                      </div>
                    ) : (
                      ""
                    );
                  })}
                </div>
              </div>
            </div>
          </div>
          <div className="p-50"></div>
        </div>
      </div>
    </>
  );
}

export const getServerSideProps = async ({ params }: any) => {
  const { corpA: corpAId, corpB: corpBId } = params;
  const { data } = await axios.get(
    SERVER_URL + `/analysis/comparison/${corpAId}/${corpBId}`
  );

  return {
    props: {
      data: data,
    },
  };
};
