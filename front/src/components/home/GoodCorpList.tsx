import Link from "next/link";
import RecommendCard from "./RecommendCard";

interface corpInformation {
  corpId: string;
  corpName: string;
  corpImg: string;
  indutyName: string;
  corpSize: string;
}

export default function GoodCorpList({
  subject,
  corpList,
}: {
  subject: string;
  corpList: Array<corpInformation>;
}) {
  console.log(corpList);
  return (
    <div className="w-[1200px] mx-auto my-50">
      <div className="text-24 font-bold">{subject} 양호 기업</div>
      <div className="flex justify-between mt-20">
        {corpList &&
          corpList.map((corp) => (
            <Link
              href={`/detail/` + `${corp.corpId}`}
              key={"추천기업" + `${corp.corpName}`}
            >
              <RecommendCard corp={corp} />
            </Link>
          ))}
      </div>
    </div>
  );
}
