import Image from "next/image";
import AnalysisResult from "./AnalysisResult";
import { Tooltip } from "@material-tailwind/react";
import React from "react";
import formatDescription from "@/utils/formatDescription";

interface Iprops {
  name: string,
  description: string,
  rate?: string,
  width?: string,
}

export default function AnalysisTitle({ name, rate, description, width }: Iprops) {

  return (
    <>
      <div className="flex justify-between text-16">
        <div className="flex py-4 px-7" >
          {name}
          <Tooltip content={formatDescription(description + "\n\n자세히 보려면 아이콘을 클릭하세요.")} className={"p-20 bg-opacity-90 bg-blue-600 rounded-5 " + width} placement="top-start" >
            <Image src="/info.svg" alt="info" width={30} height={30} className="self-center w-20 h-20 ml-8 transition-transform duration-200 ease-out hover:transform hover:scale-[130%]" data-tooltip-id={`info-${name}`} />
          </Tooltip>
        </div>
        <div className="py-4 px-7">
          <AnalysisResult rate={rate} />
        </div>
      </div>
    </>
  )
}

