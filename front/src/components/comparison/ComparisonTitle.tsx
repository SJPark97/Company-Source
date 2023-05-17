import Image from "next/image";
import { Tooltip } from "@material-tailwind/react";
import React from "react";
import formatDescription from "@/utils/formatDescription";
import { useDispatch } from "react-redux";
import { openInfoModal } from "@/stores/info/controlInfoDetail";
import InfoModal from "../InfoModal";

interface Iprops {
  name: string,
  description: string,
  width?: string,
}

export default function ComparisonTitle({ name, description, width }: Iprops) {

  const dispatch = useDispatch();

  return (
    <>
      <div className="flex justify-between text-16">
        <div className="flex py-4 px-7" >
          {name}
          <Tooltip content={formatDescription(description + "\n\n자세히 보려면 아이콘을 클릭하세요.")} className={"p-20 bg-opacity-90 bg-blue-600 rounded-5 " + width} placement="top-start" >
            <Image onClick={() => { dispatch(openInfoModal()) }} src="/info.svg" alt="info" width={30} height={30} className="self-center w-20 h-20 ml-8 transition-transform duration-200 ease-out hover:transform hover:scale-[130%]" data-tooltip-id={`info-${name}`} />
          </Tooltip>
          <InfoModal />
        </div>
      </div>
    </>
  )
}

