import NavBar from "@/components/NavBar";
import Title from "@/components/detail/Title";
import { useRouter } from "next/router";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";
import axios from "axios";
import { SERVER_URL, TEST_URL } from "@/utils/url";
import analysisCodeList from "@/models/analysisCodeList";

interface searchdetaiProps {
	analysisList: []
	companyOverviewInfo: any;
}

export default function searchdetail({ analysisList, companyOverviewInfo }: searchdetaiProps) {

	const router = useRouter();
	const { searchdetail } = router.query;

	return (
		<>
			<NavBar />
			<div className="flex flex-col">

				{/* 기업 개요 부분 */}
				<Title name="기업 개요" />
				{searchdetail && <CompanyOverview companyOverviewInfo={companyOverviewInfo} />}

				{/* 재무 분석 부분 */}
				<Title name="재무 분석" />
				{searchdetail && <FinancialAnalysis companyId={searchdetail as string} analysisList={analysisList} />}
			</div >
		</>
	)
}

export const getStaticPaths = async () => {
	return {
		paths: [{ params: { searchdetail: "" } }],
		fallback: true,
	};
};

export const getStaticProps = async ({ params }: any) => {
	// 회사의 id를 router의 params에서 받아와서 저장
	const companyId = params?.searchdetail;
	console.log(companyId)
	const getAnalysisList = [];

	for (const analysisCode of analysisCodeList) {
		const res = await axios.get(SERVER_URL + `/analysis/${analysisCode.id}/${companyId}`);
		getAnalysisList.push(res.data);
	}

	const { data: getCompanyOverviewInfo } = await axios.get(SERVER_URL + `/corp/info/${companyId}`);

	return {
		props: {
			analysisList: getAnalysisList,
			companyOverviewInfo: getCompanyOverviewInfo,
		},
	};
};
