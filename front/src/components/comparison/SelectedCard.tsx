import cardLocation from "@/models/cardLocation";
import companyInfo from "@/models/companyInfo";
import { openLeftModal, openRightModal } from "@/stores/comparison/controlModal";
import { RootState } from "@/stores/store";
import Image from "next/image";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function SelectedCard({ cardLocation }: cardLocation) {

  const dispatch = useDispatch();
  const [companyInfo, setCompanyInfo] = useState<companyInfo>({ corpId: "", corpImg: "", corpName: "", corpSize: "", indutyName: "", isSelected: false });
  const handleOpenModal = (whichLocation: "left" | "right") => {
    if (whichLocation === "left") {
      dispatch(openLeftModal())
    } else {
      dispatch(openRightModal())
    }
  }

  const leftCompanyInfo = useSelector((state: RootState) => {
    return state.leftSelectedCompany
  })
  const rightCompanyInfo = useSelector((state: RootState) => {
    return state.rightSelectedCompany
  })
  useEffect(() => {

    if (cardLocation === "left") {
      setCompanyInfo(leftCompanyInfo)
    } else {
      setCompanyInfo(rightCompanyInfo)
    }

  }, [leftCompanyInfo, rightCompanyInfo])

  return (
    <div className="flex flex-col justify-between whitespace-nowrap relative w-full h-full border-[#AAAAAA] border-solid border-2 rounded-5 border-opacity-20" >
      <div className="flex mx-20 mt-20">
        <Image src={companyInfo.corpImg} alt="company-icon-1" width={82} height={82} className="m-10 w-82 h-82" />
        <div className="flex flex-col ml-20 font-bold text-black text-24">
          <span>{companyInfo.corpName}</span>
          <div className="flex flex-col mx-5 my-10">
            <div className="flex">
              <Image src="/company-size.svg" alt="company-size" width={30} height={30} className="w-16 h-16" />
              <span className="ml-6 text-12 text-[#AAAAAA] whitespace-normal">{companyInfo.corpSize}</span>
              {/* <span className="-mt-2 mx-10 text-12 text-[#AAAAAA]">|</span> */}
            </div>
            <div className="flex mt-8">
              <Image src="/company-industry.svg" alt="company-industry" width={30} height={30} className="w-16 h-16" />
              <span className="ml-6 text-12 text-[#AAAAAA] whitespace-normal">{companyInfo.indutyName}</span>
            </div>
          </div>
        </div>
      </div>
      <div className="flex justify-center mx-20 mt-10 mb-20">
        <button className="bg-[#73D0F4] w-full text-white px-5 py-8 font-bold rounded-5 hover:bg-blue-500 hover:shadow-lg hover:shadow-blue-300 hover:duration-500" onClick={() => handleOpenModal(cardLocation)}>다른 기업 선택하기</button>
      </div>
    </div>
  )
}