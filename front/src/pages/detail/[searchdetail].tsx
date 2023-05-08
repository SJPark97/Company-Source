import NavBar from "@/components/NavBar";
import Title from "@/components/detail/Title";
import { useRouter } from "next/router";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import analysisCodeList from "@/models/analysisCodeList";
import Image from "next/image";
import dynamic from "next/dynamic";
import Head from "next/head";

interface searchdetaiProps {
  analysisList: [];
  evaluaionSummary: [];
  companyOverviewInfo: any;
}

export default function searchdetail({
  analysisList,
  companyOverviewInfo,
  evaluaionSummary,
}: searchdetaiProps) {
  const router = useRouter();
  const { searchdetail } = router.query;

  return (
    <>
      <Head>
        <title>컴퍼니소스 | {searchdetail ? companyOverviewInfo.corpName : "기업"}의 분석 결과</title>
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
                    width={30}
                    height={30}
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

      </div>
    </>
  );
}

export const getStaticPaths = async () => {
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

  for (const analysisCode of analysisCodeList) {
    const res = await axios.get(
      SERVER_URL + `/analysis/${analysisCode.id}/${companyId}`
    );

    getAnalysisList.push(res.data);
    if (res.data.data.is_exist_all) {
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

  const { data: getCompanyOverviewInfo } = await axios.get(
    SERVER_URL + `/corp/info/${companyId}`
  );

  return {
    props: {
      analysisList: getAnalysisList,
      evaluaionSummary: evaluaionSummary,
      companyOverviewInfo: getCompanyOverviewInfo.data,
    },
  };
};
