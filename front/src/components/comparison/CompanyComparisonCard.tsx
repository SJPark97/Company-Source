import { useSelector } from "react-redux";
import { RootState } from "@/stores/store";
import UnselectedCard from "./UnselectedCard";
import SelectedCard from "./SelectedCard";
import cardLocation from "@/models/cardLocation";


export default function CompanyComparisonCard({ cardLocation }: cardLocation) {

  const isLeftCardSelected = useSelector((state: RootState) => {
    return state.leftSelectedCompany.isSelected
  })
  const isRightCardSelected = useSelector((state: RootState) => {
    return state.rightSelectedCompany.isSelected
  })


  if (cardLocation === "left" && !isLeftCardSelected) {
    return (
      <>
        <UnselectedCard cardLocation="left" />
      </>
    )
  } else if (cardLocation === "right" && !isRightCardSelected) {
    return (
      <>
        <UnselectedCard cardLocation="right" />
      </>
    )
  } else if (cardLocation === "left" && isLeftCardSelected) {
    return (
      <>
        <SelectedCard cardLocation="left" />
      </>
    )
  } else {
    return (
      <>
        <SelectedCard cardLocation="right" />
      </>
    )
  }

}

// 선택 됬을 때 디자인
{/* <div className="flex flex-col justify-center relative w-[380px] h-[170px] border-black border-solid border-2 rounded-5" >
        <div className="flex mx-40">
          <Image src="/samsung-detail.png" alt="company-icon-1" width={82} height={82} className="p-10 w-70 h-70" />
          <div className="flex flex-col ml-40 text-black font-bold text-24">
            <span>삼성전자</span>
            <div className="flex mx-5 my-10">
              <Image src="/company-size.svg" alt="company-size" width={30} height={30} className="w-16 h-16" />
              <span className="ml-6 text-12 text-[#AAAAAA]">기업형태</span>
              <span className="-mt-2 mx-10 text-12 text-[#AAAAAA]">|</span>
              <Image src="/company-industry.svg" alt="company-industry" width={30} height={30} className="w-16 h-16" />
              <span className="ml-6 text-12 text-[#AAAAAA]">산업형태</span>
            </div>
          </div>
        </div>
      </div> */}

// 선택 안됬을 때 디자인
{/* <button onClick={() => { dispatch(openModal()) }} className="flex flex-col justify-center relative w-[380px] h-[170px] border-dashed border-2 rounded-5 hover:transform hover:scale-110 transition-transform duration-200 ease-out" >
        <div className="flex mx-40">
          <Image src="/company-icon-1.svg" alt="company-icon-1" width={60} height={60} className="p-10 border-2 border-black border-solid rounded-5 w-70 h-70 opacity-20" />
          <div className="self-center ml-40 text-[#AAAAAA] font-bold text-24">기업 추가하기</div>
        </div>
      </button> */}

