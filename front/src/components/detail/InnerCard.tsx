import tempOverview from "@/models/tempOverview"
import AnalysisResult from "./AnalysisResult"
import Link from "next/link"

interface Icard {
  analysisName?: string,
  rate?: string,
  title?: string,
  content?: string,
}

interface Iprops {
  cardItemList: Array<Icard>,
  type: "evaluation" | "corpInfo",
}

export default function InnerCard({ cardItemList, type }: Iprops) {

  return (
    <div className="flex flex-col p-10 mt-10 mb-40 border-gray-500 rounded-5 border-1 h-[350px]">
      {cardItemList.map((item) => {
        return item.title !== "산업코드" && (
          <div className="flex items-center justify-start h-full m-5">
            {type === "evaluation" ?
              <>
                <div className="min-w-[230px] text-16">{item.analysisName}</div>
                <AnalysisResult rate={item.rate} />
              </>
              :
              <>
                <div className="min-w-[100px] text-16">{item.title}</div>
                {item.title === "홈페이지" ?
                  <a className="text-blue-500" target="_blank" rel="noreferrer" href={`http://` + `${item.content}`}>{item.content}</a> :
                  <div className="text-gray-400 text-16">{item.content}</div>
                }
              </>
            }
          </div>
        )
      })}
    </div>
  )
}