import Image from "next/image";
import { useDispatch, useSelector } from "react-redux";
import { closeLeftModal, closeRightModal } from "@/stores/comparison/controlModal";
import companyInfo from "@/models/companyInfo";
import { RootState } from "@/stores/store";
import { selectLeftCard, setLeftCardCompany } from "@/stores/comparison/leftSelectedCompany";
import { selectRightCard, setRightCardCompany } from "@/stores/comparison/rightSelectedCompany";
import { useEffect, useState } from "react";


interface Iprops {
  corpImg: string,
  corpName: string,
  corpSize: string,
  indutyName: string,
}

export default function ModalInnerCard({ corpImg, corpName, corpSize, indutyName }: Iprops) {

  const dispatch = useDispatch();

  const isLeftModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isLeftOpen
  })
  const isRightModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isRightOpen
  })

  const companyInfo = {
    corpImg: corpImg,
    corpName: corpName,
    corpSize: corpSize,
    indutyName: indutyName
  }

  return (
    <div className="border-b-1 border-solid border-[#D9D9D9] flex justify-between">
      <div className="flex-none m-10 w-40 h-40">
        {corpImg ? (
          <Image
            src={corpImg}
            alt="기업 로고 이미지"
            width={82}
            height={82}
          />
        ) : (
          <Image
            src="/company_default.jpg"
            alt="default 이미지"
            width={82}
            height={82}
          />
        )}
      </div>
      <div className="grow mr-10 self-center">{corpName}</div>
      <button
        className="flex-none text-13 text-white bg-[#73D0F4] mr-10 self-center px-3 py-1 rounded-2"
        onClick={() => {
          // 추가하기 버튼 onClick 이벤트 : 모달창을 닫으면서 기업을 store에 추가해야함
          if (isLeftModalOpen) {
            dispatch(selectLeftCard());
            dispatch(setLeftCardCompany(companyInfo));
            dispatch(closeLeftModal());
          } else if (isRightModalOpen) {
            dispatch(selectRightCard());
            dispatch(setRightCardCompany(companyInfo));
            dispatch(closeRightModal());
          }
        }}>추가하기</button>
    </div >
  )
}