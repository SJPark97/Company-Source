import Image from "next/image";
import AnalysisResult from "./AnalysisResult";
import { Tooltip } from "@material-tailwind/react";
import React from "react";

interface Iprops {
  name: string,
  rate: string,
  description: string,
}

export default function AnalysisTitle({ name, rate, description }: Iprops) {

  const formatDescription = (text: string) => {
    return text.split('\n').map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  };

  return (
    <>
      <div className="flex justify-between text-16">
        <div className="flex py-4 px-7" >
          {name}
          <Tooltip content={formatDescription(description)} className="p-20 bg-blue-500 w-[400px] rounded-5" placement="bottom" >
            <Image src="/info.svg" alt="info" width={20} height={20} className="ml-8" data-tooltip-id={`info-${name}`} />
          </Tooltip>
        </div>
        <div className="py-4 px-7">
          <AnalysisResult rate={rate} />
        </div>
      </div>
    </>
  )
}

