import Image from "next/image";
import Title from "./Title";
import CompanyOverviewContent from "./CompanyOverviewContent";
import InnerCard from "./InnerCard";
import tempOverview from "@/models/tempOverview";

interface Iprops {
  companyId?: string,
  companyName?: string,
  companyOverviewInfo: any,
  evaluationSummary: [],
}

export default function CompanyOverview({ companyOverviewInfo, evaluationSummary }: Iprops) {
  console.log(companyOverviewInfo, "ejhfoiqjh")

  return (
    <>
      <div className="flex grow">
        <div className="flex flex-col ml-[3vw]">
          <div className="flex mt-20">
            <Image src="/evaluation.svg" alt="evaluation" width={24} height={24} />
            <span className="ml-10 text-20">평가요약</span>
          </div>
          <InnerCard cardItemList={evaluationSummary} type="evaluation" />
        </div>
        <div className="flex flex-col ml-[3vw] mr-[3vw] grow">
          <div className="flex mt-20">
            <Image src="/flag.svg" alt="flag" width={24} height={24} />
            <span className="ml-10 text-20">기업정보</span>
          </div>
          <InnerCard cardItemList={companyOverviewInfo.infoList} type="corpInfo" />
        </div>
      </div >
    </>
  )
}