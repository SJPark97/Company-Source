import AnalysisResult from "./AnalysisResult";

interface Iprops {
  name: string,
  rate: string,
}

export default function AnalysisTitle({ name, rate }: Iprops) {

  return (
    <div className="flex justify-between text-16">
      <div className="px-10 py-7">
        {name}
      </div>
      <div className="px-10 py-7">
        <AnalysisResult rate={rate} />
      </div>
    </div>
  )
}