import Image from "next/image";
import AnalysisResult from "./AnalysisResult";
import { Tooltip } from "@material-tailwind/react";
import React from "react";
import formatDescription from "@/utils/formatDescription";

interface Iprops {
  name: string,
  rate: string,
  description: string,
}

export default function AnalysisTitle({ name, rate, description }: Iprops) {

  return (
    <>
      <div className="flex justify-between text-16">
        <div className="flex py-4 px-7" >
          {name}
          <Tooltip content={formatDescription(description)} className="p-20 bg-opacity-90 bg-blue-600 w-[400px] rounded-5" placement="top-start" >
            <Image src="/info.svg" alt="info" width={30} height={30} className="self-center w-20 h-20 ml-8" data-tooltip-id={`info-${name}`} />
          </Tooltip>
        </div>
        <div className="py-4 px-7">
          <AnalysisResult rate={rate} />
        </div>
      </div>
    </>
  )
}

