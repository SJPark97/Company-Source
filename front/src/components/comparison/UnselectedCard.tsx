import cardLocation from "@/models/cardLocation";
import { openLeftModal, openRightModal } from "@/stores/comparison/controlModal";
import Image from "next/image"
import { useDispatch } from "react-redux"

export default function UnselectedCard({ cardLocation }: cardLocation) {

  const dispatch = useDispatch();

  const handleOpenModal = (whichLocation: "left" | "right") => {
    if (whichLocation === "left") {
      dispatch(openLeftModal())
    } else {
      dispatch(openRightModal())
    }
  }

  return (
    <button onClick={() => { handleOpenModal(cardLocation) }} className="flex flex-col whitespace-nowrap justify-center relative w-full h-full border-dashed border-2 rounded-5 hover:transform hover:scale-110 transition-transform duration-200 ease-out" >
      <div className="flex mx-40">
        <Image src="/company-icon-1.svg" alt="company-icon-1" width={60} height={60} className="p-10 border-2 border-[#AAAAAA] border-dashed rounded-5 w-70 h-70 opacity-20" />
        <div className="self-center ml-40 text-[#AAAAAA] font-bold text-24">기업 추가하기</div>
      </div>
    </button>
  )
}