import NavBar from "@/components/NavBar";
import Title from "@/components/detail/Title";
import { useRouter } from "next/router";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import analysisCodeList from "@/models/analysisCodeList";
import Image from "next/image";
import Head from "next/head";
import formatDescription from "@/utils/formatDescription";

interface searchdetaiProps {
  analysisList: [];
  evaluaionSummary: [];
  companyOverviewInfo: any;
  gptAnalysis: string;
}

export default function searchdetail({
  analysisList,
  companyOverviewInfo,
  evaluaionSummary,
  gptAnalysis,
}: searchdetaiProps) {
  const router = useRouter();
  const { searchdetail } = router.query;

  return (
    <>
      <Head>
        <title>{`컴퍼니소스 | ${searchdetail ? companyOverviewInfo.corpName : "기업"
          }의 분석 결과`}</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content={`컴퍼니소스에서 검색한 ${searchdetail ? companyOverviewInfo.corpName : "기업"
            }의 분석 결과입니다.}`}
        />
        <meta property="og:type" content="website" />
        <meta
          property="og:url"
          content={`https://company-source.com/detail/${searchdetail ? companyOverviewInfo.corpId : ""
            }`}
        />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
      </Head>
      <NavBar />
      <div className="flex flex-col bg-gray-100">
        {/* 기업 개요 부분 */}
        <div className="bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
          {searchdetail && (
            <>
              <div className="flex justify-between mx-[3vw] mt-20">
                <Title
                  companyLogo={companyOverviewInfo.corpImg}
                  name={companyOverviewInfo.corpName}
                />
              </div>
              <div className="flex">
                <CompanyOverview
                  companyOverviewInfo={companyOverviewInfo}
                  evaluationSummary={evaluaionSummary}
                />
              </div>
            </>
          )}
        </div>

        {/* 재무 분석 부분 */}
        <div className="bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
          {searchdetail && (
            <>
              <div className="ml-[3vw] mt-40 text-24 font-bold">
                <div className="flex">
                  <Image
                    src="/analysis.svg"
                    alt="analysis"
                    width={392}
                    height={392}
                    className="w-30 h-30"
                  />
                  <span className="ml-12">재무분석</span>
                </div>
              </div>
              <FinancialAnalysis
                companyId={searchdetail as string}
                analysisList={analysisList}
              />
            </>
          )}
        </div>

        {/* GPT 분석 */}
        <div className="bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
          {searchdetail && (
            <>
              <div className="ml-[3vw] mt-40 text-24 font-bold">
                <div className="flex text-white">
                  <Image
                    src="/gpt.png"
                    alt="gpt"
                    width={1500}
                    height={1500}
                    className="self-center w-30 h-30 rounded-2"
                  />
                  <span className="ml-12 text-black">GPT의 기업설명</span>
                </div>
                <div className="text-18 my-40 mr-[3vw] font-normal">
                  {formatDescription(gptAnalysis)}
                </div>
              </div>
            </>
          )}
        </div>

        {/* 페이지 최하단 마진100 */}
        <div className="mb-100"></div>
      </div>
    </>
  );
}

export const getStaticPaths = async () => {
  // // 빌드 시에 html 페이지를 모두 렌더링하는 코드
  // const res = await axios.get(
  //   SERVER_URL + `/corp/all`
  // );
  // const data = res.data;

  // return {
  //   paths: data.map((corpId: string) => ({
  //     params: {
  //       searchdetail: corpId.toString()
  //     }
  //   })),
  //   fallback: true,
  // };

  // 페이지 최초 방문 시 html을 렌더링해주는 방법
  return {
    paths: [],
    fallback: true,
  };
};

export const getStaticProps = async ({ params }: any) => {
  // 회사의 id를 router의 params에서 받아와서 저장
  const companyId = params?.searchdetail;
  const getAnalysisList = [];
  const evaluaionSummary = [];

  // 차트 데이터 받아오는 API
  for (const analysisCode of analysisCodeList) {
    const res = await axios.get(
      SERVER_URL + `/analysis/${analysisCode.id}/${companyId}`
    );

    getAnalysisList.push(res.data);
    if (res.data.data.exist_all) {
      evaluaionSummary.push({
        analysisName: res.data.data.analysis_name,
        rate: res.data.data.rate,
      });
    } else {
      evaluaionSummary.push({
        analysisName: res.data.data.analysis_name,
        rate: "정보없음",
      });
    }
  }

  // 기업 개요 데이터 받아오는 API
  const { data: getCompanyOverviewInfo } = await axios.get(
    SERVER_URL + `/corp/info/${companyId}`
  );

  // GPT 데이터 받아오는 API
  const getGptAnalysis = await axios.get(
    SERVER_URL + `/analysis/gpt/${companyId}`
  );
  let gptAnalysis = "";
  if (getGptAnalysis.status === 200) {
    gptAnalysis = getGptAnalysis.data.data;
  } else {
    gptAnalysis = "데이터가 없어요ㅠㅠ";
  }

  return {
    props: {
      analysisList: getAnalysisList,
      evaluaionSummary: evaluaionSummary,
      companyOverviewInfo: getCompanyOverviewInfo.data,
      gptAnalysis: gptAnalysis,
    },
  };
};
