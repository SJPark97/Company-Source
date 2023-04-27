import NavBar from "@/components/NavBar";
import AnalysisTitle from "@/components/detail/AnalysisTitle";
import Chart101 from "@/components/detail/Chart101";
import OverviewContent from "@/components/detail/CompanyOverviewContent";
import Title from "@/components/detail/Title";
import Image from "next/image";
import { useRouter } from "next/router";
import { QueryClient, dehydrate } from "react-query";
import chartQueryClient from "@/hooks/chartQueryClient";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";
import Chart103 from "@/components/detail/Chart103";
import { GetStaticProps, GetStaticPropsContext } from "next";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import analysisCodeList from "@/models/analysisCodeList";

interface searchdetaiProps {
	analysisList: []
}

export default function searchdetail({ analysisList }: searchdetaiProps) {
	console.log(analysisList)
	const router = useRouter();
	const { searchdetail } = router.query;

	// console.log(analysisList)

	return (
		<>
			<NavBar />
			<div className="flex flex-col">

				{/* 기업 개요 부분 */}
				<Title name="기업 개요" />
				<CompanyOverview />

				{/* 재무 분석 부분 */}
				<Title name="재무 분석" />
				{searchdetail && <FinancialAnalysis companyId={searchdetail as string} analysisList={analysisList} />}
			</div >
		</>
	)
}

export const getStaticPaths = async () => {
	return {
		paths: [{ params: { searchdetail: "234" } }],
		fallback: true,
	};
};

export const getStaticProps = async ({ params }: any) => {
	// 회사의 id를 router의 params에서 받아와서 저장
	const companyId = params?.searchdetail;
	console.log(companyId)
	const getAnalysisList = [];

	for (const analysisCode of analysisCodeList) {
		const res = await axios.get(SERVER_URL + `/${analysisCode.id}/${companyId}`);
		getAnalysisList.push(res.data);
	}

	return {
		props: {
			analysisList: getAnalysisList,
		},
	};
};
