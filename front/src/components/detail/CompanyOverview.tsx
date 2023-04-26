import Image from "next/image";
import Title from "./Title";
import OverviewContent from "./OverviewContent";

interface Iprops {
  companyId?: string,
  companyName?: string,
}

export default function CompanyOverview({ companyName }: Iprops) {
  return (
    <>
      <div className="flex justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
        <div className="flex flex-col justify-center mx-[4vw]">
          <Image src="/samsung-detail.png" alt="logo" width={364} height={129} />
        </div>
        <div className="flex flex-col justify-center w-[45vw] p-30">
          <div className="h-auto bg-white rounded-10">
            <div className="mx-[2vw] my-20 text-28">{companyName}</div>
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
    </>
  )
}