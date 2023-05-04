import Image from "next/image";

interface Iprops {
  rate?: string
}

interface IrateState {
  textColor: string,
  imageSrc?: string,
  opacity?: string,
}

export default function AnalysisResult({ rate }: Iprops) {

  let rateState: IrateState = {
    textColor: "",
    imageSrc: "",
    opacity: "",
  }

  if (rate === "불량" || rate === "부실") {
    rateState = { textColor: "text-red-400", imageSrc: "/bad.png" }
  } else if (rate === "양호" || rate === "정상") {
    rateState = { textColor: "text-[#82ca9d]", imageSrc: "/good.png" }
  } else if (rate === "보통" || rate === "보류") {
    rateState = { textColor: "text-yellow-600", imageSrc: "/soso.png" }
  } else if (rate === "정보없음") {
    rateState = { textColor: "text-gray-500", imageSrc: "/cross_circle.svg" }
  } else {
    rateState = { textColor: "text-black", imageSrc: "/what.png" }
  }

  return (
    <div className="flex h-24 text-16">
      {rateState.imageSrc ? < Image src={rateState.imageSrc} alt="evaluation" width={24} height={24} className="mr-5 " /> : null}
      <span className={rateState.textColor + " min-w-36 whitespace-nowrap"}>{rate}</span>
    </div >
  )
}