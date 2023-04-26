import NavBar from "@/components/NavBar";
import AnalysisTitle from "@/components/detail/AnalysisTitle";

import OverviewContent from "@/components/detail/OverviewContent";
import Title from "@/components/detail/Title";
import Image from "next/image";
import { useRouter } from "next/router"
import { dehydrate } from "react-query";
import chartQueryClient from "@/hooks/chartQueryClient";
import CompanyOverview from "@/components/detail/CompanyOverview";
import FinancialAnalysis from "@/components/detail/FinancialAnalysis";

export default function searchdetail() {

	const router = useRouter();
	const { searchdetail } = router.query;

	return (
		<>
			<NavBar />
			<div className="flex flex-col">

				{/* 기업 개요 부분 */}
				<Title name="기업 개요" />
				<CompanyOverview />

				{/* 재무 분석 부분 */}
				<Title name="재무 분석" />
				{searchdetail && <FinancialAnalysis companyId={searchdetail as string} />}
			</div>

		</>
	)
}

export const getStaticPaths = async () => {
	return {
		paths: [],
		fallback: true,
	}
}

export const getStaticProps = async ({ params }: any) => {
	const id = params.searchdetail;
	const queryClient = chartQueryClient(id);
	return {
		props: {
			dehydratedProps: dehydrate(queryClient),
		}
	}
}