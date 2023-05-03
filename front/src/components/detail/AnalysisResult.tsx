import Image from "next/image";

interface Iprops {
  rate?: string
}

interface IrateState {
  textColor: string,
  imageSrc?: string
}

export default function AnalysisResult({ rate }: Iprops) {

  let rateState: IrateState = {
    textColor: "",
    imageSrc: ""
  }

  if (rate === "불량") {
    rateState = { textColor: "text-red-400", imageSrc: "/bad.png" }
  } else if (rate === "양호") {
    rateState = { textColor: "text-[#82ca9d]", imageSrc: "/good.png" }
  } else if (rate === "보통") {
    rateState = { textColor: "text-yellow-400", imageSrc: "/soso.png" }
  } else if (rate === "정보없음") {
    rateState = { textColor: "text-gray-500" }
  }

  return (
    <div className="flex h-24 min-w-50 text-16">
      {rateState.imageSrc ? < Image src={rateState.imageSrc} alt="evaluation" width={24} height={24} className="mr-5" /> : null}
      <span className={rateState.textColor + " mr-10"}>{rate}</span>
    </div >
  )
}