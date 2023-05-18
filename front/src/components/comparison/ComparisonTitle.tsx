import Image from "next/image";
import { Tooltip } from "@material-tailwind/react";
import React, { useEffect, useState } from "react";
import formatDescription from "@/utils/formatDescription";
import { useDispatch, useSelector } from "react-redux";
import { openInfoModal } from "@/stores/info/controlInfoDetail";
import InfoModal from "../InfoModal";
import { RootState } from "@/stores/store";

interface Iprops {
  name: string,
  description: string,
  analysisInfo: { [key: string]: string },
  width?: string,
}

export default function ComparisonTitle({ name, description, width, analysisInfo }: Iprops) {

  // const [open, setOpen] = useState<boolean>(false);
  // const handleClose = () => setOpen(false);
  // const handleOpen = () => setOpen(true);

  return (
    <>
      <div className="flex justify-between text-16">
        <div className="flex py-4 px-7" >
          {name}
          <Tooltip content={formatDescription(description)} className={"p-20 bg-opacity-90 bg-blue-600 rounded-5 " + width} placement="top-start" >
            {/* <Image onClick={handleOpen} src="/info.svg" alt="info" width={30} height={30} className="self-center w-20 h-20 ml-8 transition-transform duration-200 ease-out hover:transform hover:scale-[130%]" data-tooltip-id={`info-${name}`} /> */}
            <Image src="/info.svg" alt="info" width={30} height={30} className="self-center w-20 h-20 ml-8 transition-transform duration-200 ease-out hover:transform hover:scale-[130%]" data-tooltip-id={`info-${name}`} />
          </Tooltip>
          {/* <InfoModal analysisInfo={analysisInfo} open={open} handleClose={handleClose} handleOpen={handleOpen} /> */}
        </div>
      </div>
    </>
  )
}

