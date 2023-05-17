import { RootState } from "@/stores/store"
import { useEffect, useState } from "react"
import { useSelector } from "react-redux"

export default function ComparisonStartMessage() {

  const corpAName = useSelector((state: RootState) => {
    return state.leftSelectedCompany.corpName
  })
  const corpBName = useSelector((state: RootState) => {
    return state.rightSelectedCompany.corpName
  })

  const [opacity, setOpacity] = useState<number>(100);

  const softRemover = () => {
    if (opacity > 96) {
      setTimeout(() => {
        setOpacity(opacity - 1);
      }, 100);
    } else if (opacity > 5)
      setTimeout(() => {
        setOpacity(opacity - 8);
      }, 50);
  }

  useEffect(() => {
    softRemover();
  }, [softRemover])

  return (
    <>
      <div className={"bg-white rounded-5 p-20 text-12 absolute bottom-50 left-[50%] -translate-x-[50%] -translate-y-[50%] " + opacity}>{corpAName} vs {corpBName}</div>
    </>
  )
}