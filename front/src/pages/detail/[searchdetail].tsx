import NavBar from "@/components/NavBar";
import AnalysisTitle from "@/components/detail/AnalysisTitle";
import Chart_101 from "@/components/detail/Chart101";
import OverviewContent from "@/components/detail/OverviewContent";
import Title from "@/components/detail/Title";
import Image from "next/image";
import { useRouter } from "next/router"
import { dehydrate } from "react-query";
import chartQueryClient from "@/hooks/chartQueryClient";

export default function searchdetail() {

	const router = useRouter();
	const { searchdetail } = router.query;

	return (
		<>
			<NavBar />
			<div className="flex flex-col">

				{/* 기업 개요 부분 */}
				<Title name="기업 개요" />
				<div className="flex justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
					<div className="flex flex-col justify-center mx-[4vw]">
						<Image src="/samsung-detail.png" alt="logo" width={364} height={129} />
					</div>
					<div className="flex flex-col justify-center w-[45vw] p-30">
						<div className="h-auto bg-white rounded-10">
							<div className="mx-[2vw] my-20 text-28">{searchdetail}</div>
							<div className="flex flex-wrap justify-between m-20">
								{/* API랑 연결하면 map으로 바꿔줄 부분 */}
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
								<OverviewContent title="기업형태" content="대기업, 주식회사" />
							</div>
						</div>
					</div>
				</div>

				{/* 재무 분석 부분 */}
				<Title name="재무 분석" />
				<div className="flex flex-col">

					<div className="justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
						<div className="flex flex-col">
							<AnalysisTitle />
							<div>
								<div className="flex">
									<div className="p-20 bg-white m-30 rounded-10">
										{searchdetail && <Chart_101 analysisCode="101" companyId={searchdetail as string} />}
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
							<AnalysisTitle />
							<div>
								<div className="flex">
									<div className="p-20 bg-white text-40 m-30 rounded-10">
										설명설명설명설명설명설명설명설명
									</div>
									<div className="p-20 bg-white my-30 mr-30 rounded-10">
										{searchdetail && <Chart_101 analysisCode="101" companyId={searchdetail as string} />}
									</div>
								</div>
							</div>
						</div>
					</div>

					<div className="mt-20"></div>

				</div>
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