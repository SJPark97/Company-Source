import Image from "next/image";
import { useEffect, useState } from "react";
import Legend from "./Legend";

interface Iprops {
  rate: string
}

interface IrateState {
  backgroundColor: string,
  textColor: string,
  imageSrc: string
}

export default function AnalysisInfo({ rate }: Iprops) {

  let rateState: IrateState = {
    backgroundColor: "",
    textColor: "",
    imageSrc: ""
  }

  if (rate === "불량") {
    rateState = { backgroundColor: "bg-red-400", textColor: "text-red-400", imageSrc: "/bad.png" }
  } else if (rate === "양호") {
    rateState = { backgroundColor: "bg-[#82ca9d]", textColor: "text-[#82ca9d]", imageSrc: "/good.png" }
  } else {
    rateState = { backgroundColor: "bg-yellow-400", textColor: "text-yellow-400", imageSrc: "/soso.png" }
  }

  return (
    <div className="flex justify-between mt-10 ml-10 text-white my-30 text-20">
      {/* rate 조건에 따라 배경 색깔 분리시키기 : 양호는 초록, 보통은 주황, 불량은 빨강 */}
      <div className={"flex " + rateState.backgroundColor + " py-5 rounded-5"}>
        <span className="px-10 py-2">평가</span>
        <div className="flex py-2 mr-5 bg-white rounded-5">
          <Image src={rateState.imageSrc} alt="evaluation" width={30} height={30} className="mx-5" />
          <span className={rateState.textColor + " mr-10"}>{rate}</span>
        </div>
      </div>
      <div className="text-black">
        <Legend />
      </div>
    </div >
  )
}