import NavBar from "@/components/NavBar";
import CompanyComparisonWindow from "@/components/comparison/CompanyComparisonWindow";
import Head from "next/head";

export default function comparison() {

  return (
    <>
      <Head>
        <title>컴퍼니소스 | 기업 비교</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스(Company Source)의 기업 비교 페이지 입니다."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/comparison" />
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
      <div className="flex flex-col h-full bg-no-repeat bg-cover bg-comparison">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">

          {/* 기업 비교 선택창 부분 */}
          <CompanyComparisonWindow />

        </div>
      </div>
    </>
  );
}
