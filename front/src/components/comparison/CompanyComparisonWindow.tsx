import CompanyComparisonCard from "@/components/comparison/CompanyComparisonCard";
import ComparisonModal from "@/components/comparison/ComparisonModal";
import { RootState } from "@/stores/store";
import { useRouter } from "next/router";
import { useSelector } from "react-redux";

export default function CompanyComparisonWindow() {

  const router = useRouter();
  const isLeftCardSelected = useSelector((state: RootState) => {
    return state.leftSelectedCompany.isSelected
  })
  const isRightCardSelected = useSelector((state: RootState) => {
    return state.rightSelectedCompany.isSelected
  })

  const leftCompanyId = useSelector((state: RootState) => {
    return state.leftSelectedCompany.corpId
  })
  const rightCompanyId = useSelector((state: RootState) => {
    return state.rightSelectedCompany.corpId
  })

  const handleOnclick = () => {
    if ((!isLeftCardSelected && !isRightCardSelected)) {
      alert("비교할 기업이 선택되지 않았습니다!")
    } else if (!isLeftCardSelected) {
      alert("왼쪽 기업도 선택해주세요!")
    } else if (!isRightCardSelected) {
      alert("오른쪽 기업도 선택해주세요!")
    } else {
      router.push(`/comparison/${leftCompanyId}/${rightCompanyId}`)
    }
  }

  return (
    <>
      <div className="flex flex-col bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
        <div className="flex justify-between min-h-[300px]">
          <div className="w-full mx-70 my-50">
            <CompanyComparisonCard cardLocation="left" />
            <ComparisonModal modalLocation="left" />
          </div>
          <div className="w-full mr-70 my-50">
            <CompanyComparisonCard cardLocation="right" />
            <ComparisonModal modalLocation="right" />
          </div>
        </div>
        <div className="flex justify-center">
          <button className="py-8 mx-20 mb-40 font-bold text-white bg-blue-600 text-20 px-30 rounded-5 hover:bg-blue-800 hover:shadow-lg hover:shadow-blue-300 hover:duration-500" onClick={() => handleOnclick()}>비교하기</button>
        </div>
      </div>
      <div className="mb-100"></div>
    </>
  )
}