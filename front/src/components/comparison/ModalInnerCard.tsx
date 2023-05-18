import Image from "next/image";
import { useDispatch, useSelector } from "react-redux";
import { closeLeftModal, closeRightModal } from "@/stores/comparison/controlModal";
import companyInfo from "@/models/companyInfo";
import { RootState } from "@/stores/store";
import { selectLeftCard, setLeftCardCompany } from "@/stores/comparison/leftSelectedCompany";
import { selectRightCard, setRightCardCompany } from "@/stores/comparison/rightSelectedCompany";
import { useEffect, useState } from "react";


interface Iprops {
  corpId: string,
  corpImg: string,
  corpName: string,
  corpSize: string,
  indutyName: string,
}

export default function ModalInnerCard({ corpId, corpImg, corpName, corpSize, indutyName }: Iprops) {

  const dispatch = useDispatch();

  const isLeftModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isLeftOpen
  })
  const isRightModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isRightOpen
  })

  const leftCompanyName = useSelector((state: RootState) => {
    return state.leftSelectedCompany.corpName
  })
  const rightCompanyName = useSelector((state: RootState) => {
    return state.rightSelectedCompany.corpName
  })

  const companyInfo = {
    corpId: corpId,
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
          // 같은 기업 선택 시 예외처리
          if (corpName === leftCompanyName || corpName === rightCompanyName) {
            return alert("이미 선택된 기업입니다. 다른 기업을 선택해주세요.")
          }

          // 추가하기 : 모달창을 닫으면서 기업을 store에 추가해야함
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