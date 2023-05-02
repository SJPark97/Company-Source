import tempOverview from "@/models/tempOverview"
import AnalysisResult from "./AnalysisResult"
import Link from "next/link"

interface Ianalysis {
  analysisName: string,
  rate: string
}

interface Iprops {
  imageSrc?: string,
  cardItemList: any,
  type: "evaluation" | "corpInfo",
}

export default function InnerCard({ cardItemList, type }: Iprops) {

  return (
    <div className="flex flex-col p-10 mt-10 mb-40 border-gray-500 rounded-5 border-1">
      {cardItemList.map((item: any) => {
        return (
          <div className="flex justify-start m-8 text-20">
            {type === "evaluation" ?
              <>
                <div className="min-w-[250px]">{item.analysisName}</div>
                <AnalysisResult rate={item.rate} />
              </>
              :
              <>
                <div className="min-w-[100px]">{item.title}</div>
                {item.title === "홈페이지" ?
                  <a className="text-blue-500" target="_blank" rel="noreferrer" href={`http://` + `${item.content}`}>{item.content}</a> :
                  <div className="text-gray-400">{item.content}</div>
                }
              </>
            }
          </div>
        )
      })}
    </div>
  )
}