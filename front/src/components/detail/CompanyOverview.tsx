import Image from "next/image";
import Title from "./Title";
import CompanyOverviewContent from "./CompanyOverviewContent";

interface Iprops {
  companyId?: string,
  companyName?: string,
  companyOverviewInfo: any
}

export default function CompanyOverview({ companyOverviewInfo }: Iprops) {
  return (
    <>
      <div className="flex justify-between bg-blue-background h-auto mx-[11vw] rounded-10">
        <div className="flex flex-col justify-center mx-[4vw]">
          <Image src="/samsung-detail.png" alt="logo" width={364} height={129} />
        </div>
        <div className="flex flex-col justify-center w-[45vw] p-30">
          <div className="h-auto bg-white rounded-10">
            <div className="mx-[2vw] my-20 text-28">{companyOverviewInfo.corpName}</div>
            <div className="flex flex-wrap justify-between m-20">
              {/* API랑 연결하면 map으로 바꿔줄 부분 */}
              <CompanyOverviewContent title="기업형태" content={companyOverviewInfo.corpSize} />
              <CompanyOverviewContent title="산업코드" content={companyOverviewInfo.indutyCode} />
              <CompanyOverviewContent title="산업종류" content={companyOverviewInfo.indutyName} />
              <CompanyOverviewContent title="홈페이지" content={companyOverviewInfo.homepage} />
              <CompanyOverviewContent title="사원 수" content={companyOverviewInfo.employees} />
              <CompanyOverviewContent title="설립일" content={companyOverviewInfo.foundationDate} />
              <CompanyOverviewContent title="주소" content={companyOverviewInfo.address} />
              <CompanyOverviewContent title="어제 조회 수" content={companyOverviewInfo.yesterdayView} />
              <CompanyOverviewContent title="오늘 조회 수" content={companyOverviewInfo.totalView} />
            </div>
          </div>
        </div>
      </div>
    </>
  )
}