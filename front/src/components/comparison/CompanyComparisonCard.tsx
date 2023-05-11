import Image from "next/image";

export default function CompanyComparisonCard() {

  return (
    <div className="flex flex-col justify-center relative w-[380px] h-[170px] border-dashed border-2 rounded-5 hover:transform hover:scale-110 transition-transform duration-200 ease-out" >
      <div className="flex mx-40">
        <Image src="/company-icon-1.svg" alt="company-icon-1" width={60} height={60} className="p-10 border-2 border-black border-solid rounded-5 w-70 h-70 opacity-20" />
        <div className="self-center ml-40 text-[#AAAAAA] font-bold text-24">기업 추가하기</div>
      </div>
    </div>
  )
}