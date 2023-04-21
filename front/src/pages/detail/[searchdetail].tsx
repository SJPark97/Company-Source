import NavBar from "@/components/NavBar";
import { useRouter } from "next/router"
import { useEffect } from "react";

export default function searchdetail() {
	const router = useRouter();
	const { searchdetail } = router.query;

	return (
		<>
			<NavBar />
			<div>{searchdetail}기업의 상세 페이지 입니다.</div>

		</>
	)
}