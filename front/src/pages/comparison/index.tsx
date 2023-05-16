import NavBar from "@/components/NavBar";
import CompanyComparisonWindow from "@/components/comparison/CompanyComparisonWindow";


export default function comparison() {

  return (
    <>
      <NavBar />
      <div className="flex flex-col bg-no-repeat bg-cover bg-comparison h-[400px]">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">

          {/* 기업 비교 선택창 부분 */}
          <CompanyComparisonWindow />

        </div>
      </div>
    </>
  );
}
