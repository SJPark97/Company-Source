import NavBar from "@/components/NavBar";
import Title from "@/components/detail/Title";
import { useRouter } from "next/router";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import analysisCodeList from "@/models/analysisCodeList";
import Views from "@/components/detail/Views";

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
      <NavBar />
      <div className="flex flex-col bg-gray-100">
        {/* 기업 개요 부분 */}
        <div className="bg-white border-gray-500 rounded-5 my-100 mx-[13vw] border-1">
          {searchdetail && (
            <>
              <div className="flex justify-between mx-[3vw] mt-20">
                <Title companyLogo={companyOverviewInfo.corpImg} name={companyOverviewInfo.corpName} />
                <Views total={companyOverviewInfo.totalView} yesterday={companyOverviewInfo.yesterdayView} />
              </div>
              <div className="flex">
                <CompanyOverview companyOverviewInfo={companyOverviewInfo} evaluationSummary={evaluaionSummary} />
              </div>
            </>
          )}
        </div>

        {/* 재무 분석 부분 */}
        <Title name="재무 분석" />
        {searchdetail && (
          <FinancialAnalysis
            companyId={searchdetail as string}
            analysisList={analysisList}
          />
        )}
      </div>
    </>
  );
}

export const getStaticPaths = async () => {
  return {
    paths: [{ params: { searchdetail: "77777777" } }],
    fallback: true,
  };
};

export const getStaticProps = async ({ params }: any) => {
  // 회사의 id를 router의 params에서 받아와서 저장
  const companyId = params?.searchdetail;
  console.log(companyId);
  const getAnalysisList = [];
  const evaluaionSummary = [];

  for (const analysisCode of analysisCodeList) {
    const res = await axios.get(
      SERVER_URL + `/analysis/${analysisCode.id}/${companyId}`
    );

    getAnalysisList.push(res.data);
    if (res.data.data.is_exist_all) {
      evaluaionSummary.push({ analysisName: res.data.data.analysis_name, rate: res.data.data.rate })
    } else {
      evaluaionSummary.push({ analysisName: res.data.data.analysis_name, rate: "정보없음" })
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
