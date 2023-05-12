import Image from "next/image";
import { useDispatch, useSelector } from "react-redux";
import { closeModal } from "@/stores/comparison/controlModal";
import companyInfo from "@/models/companyInfo";
import { RootState } from "@/stores/store";
import { selectLeftCard } from "@/stores/comparison/leftSelectedCompany";
import { selectRightCard } from "@/stores/comparison/rightSelectedCompany";


interface Iprops {
  corpImg: string,
  companyName: string,
  corpSize: string,
  indutyName: string,
}

export default function ModalInnerCard({ corpImg, companyName, corpSize, indutyName }: Iprops) {

  const dispatch = useDispatch();
  const isLeftCardSelected = useSelector((state: RootState) => {
    return state.leftSelectedCompany.isSelected
  })
  const isRightCardSelected = useSelector((state: RootState) => {
    return state.rightSelectedCompany.isSelected
  })

  const payload: companyInfo = {
    corpImg: corpImg,
    corpName: companyName,
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
      <div className="grow mr-10 self-center">{companyName}</div>
      <button
        className="flex-none text-13 text-white bg-[#73D0F4] mr-10 self-center px-3 py-1 rounded-2"
        onClick={() => {
          // 모달 닫으면서 선택된 기업을 카드에 넣어줘야함
          dispatch(closeModal());
          if (!(isLeftCardSelected && isRightCardSelected)) {
            console.log("왼쪽, 오른쪽 다 선택 안됨")
            dispatch(selectLeftCard())
          } else if (isLeftCardSelected) {
            console.log("왼쪽은 이미 선택되어있음")
            dispatch(selectRightCard())
          } else if (isRightCardSelected) {
            console.log("오른쪽은 이미 선택되어있음")
            dispatch(selectLeftCard)
          } else {
            console.log("둘 다 선택됨")
          }
        }}>추가하기</button>
    </div >
  )
}