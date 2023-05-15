import NavBar from "@/components/NavBar";
import CompanyComparisonCard from "@/components/comparison/CompanyComparisonCard";
import ComparisonModal from "@/components/comparison/ComparisonModal";
import { RootState } from "@/stores/store";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

export default function comparison() {

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
      <NavBar />
      <div className="flex flex-col bg-no-repeat bg-cover bg-comparison h-[400px]">
        <div className="bg-gradient-to-b from-[rgba(255,255,255,0)] to-[rgba(255,255,255,1)] h-[400px]">

          {/* 기업 비교 선택창 부분 */}
          <div className="flex flex-col bg-white border-gray-500 rounded-5 mt-100 mx-[13vw] border-1">
            <div className="flex justify-between min-h-[300px]">
              <div className="mx-70 my-50 w-full">
                <CompanyComparisonCard cardLocation="left" />
                <ComparisonModal modalLocation="left" />
              </div>
              <div className="mr-70 my-50 w-full">
                <CompanyComparisonCard cardLocation="right" />
                <ComparisonModal modalLocation="right" />
              </div>
            </div>
            <div className="flex justify-center">
              <button className="bg-blue-600 text-white font-bold text-20 px-30 py-8 rounded-5 mx-20 mb-40 hover:bg-blue-800 hover:shadow-lg hover:shadow-blue-300 hover:duration-500" onClick={() => handleOnclick()}>비교하기</button>
            </div>
          </div>
          <div className="mb-100"></div>

        </div>
      </div>
    </>
  );
}
