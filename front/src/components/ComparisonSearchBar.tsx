import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import Image from "next/image";
import { useState, useEffect, useRef, MutableRefObject } from "react";
import ModalInnerCard from "./comparison/ModalInnerCard";
import companyInfo from "@/models/companyInfo";
import { MoonLoader } from "react-spinners";
import { useSelector } from "react-redux";
import { RootState } from "@/stores/store";

export default function ComparisonSearchBar() {

  const [searchWord, setSearchWord] = useState<string>("");
  // const [autoCompleteList, setAutoCompleteList] = useState<Array<autoComplete>>
  const [companyList, setCompanyList] = useState<Array<companyInfo>>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [searchBarShadow, setSearhBarShadow] = useState<string>("");

  const isLeftModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isLeftOpen
  })
  const isRightModalOpen = useSelector((state: RootState) => {
    return state.controlModal.isRightOpen
  })

  const inputRef = useRef<HTMLInputElement>(null);

  const getCompanyInfo = async (inputValue: string) => {
    setLoading(true);

    await axios.get(SERVER_URL + `/corp/list/`, {
      params: { inputValue: inputValue },
    })
      .then((response) => {
        setCompanyList(response.data.data)
        setLoading(false);
      })
  }

  const onChangeSearchWordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchWord(e.target.value);
  };


  useEffect(() => {
    if (searchWord) {
      console.log(searchWord)
      getCompanyInfo(searchWord)
      // console.log(companyList)
    } else {
      setCompanyList([])
    }
  }, [searchWord])

  useEffect(() => {
    if (inputRef.current !== null) {
      inputRef.current.focus()
    }
  }, [isLeftModalOpen, isRightModalOpen])

  return (
    <>
      <div className={"flex min-h-40 px-40 mx-40 mt-40 mb-10 placeholder-gray-400 border-2 rounded-full w-[400px] border-brand " + searchBarShadow}>
        <Image
          src="/search.png"
          alt="search.png"
          width={20}
          height={20}
          className="self-center w-20 h-20 mr-10"
        />
        <input
          onFocus={() => setSearhBarShadow("shadow-wholeShadow shadow-blue-200")}
          onBlur={() => setSearhBarShadow("")}
          ref={inputRef}
          value={searchWord}
          onChange={onChangeSearchWordHandler}
          placeholder="기업을 검색해 보세요."
          className="w-full border-none outline-none"
        />
      </div>
      <div className='text-black text-12'>
        Tip.
        <span className='ml-5 text-[#AAAAAA]'>
          검색된 기업의 특정 분석 지표가 존재하지 않을 수도 있습니다.
        </span>
      </div>
      <div className='m-15' />
      <div className='self-start my-10 ml-40 font-bold'>검색결과  |
        <span className="font-normal"> {companyList && searchWord ? companyList.length : "0"} 건</span>
      </div>
      <div className='w-[86%] bg-black min-h-3 -z-20'></div>

      {/* 카드 배치될 곳 flex-col */}
      {loading ?
        <span className="flex self-start ml-50 mt-10 text-[#AAAAAA]">
          <MoonLoader
            color="#AAAAAA"
            loading={loading}
            size={15}
            className="mt-3 mr-8"
          />
          검색중...
        </span>
        :
        companyList && searchWord ?
          <div className='flex-col w-[80%] overflow-auto'>
            {companyList && companyList.map((item, index) => {
              return (
                <ModalInnerCard
                  key={index}
                  corpId={item.corpId}
                  corpImg={item.corpImg ? item.corpImg : "/company_default.jpg"}
                  corpName={item.corpName}
                  corpSize={item.corpSize ? item.corpSize : "정보 없음"}
                  indutyName={item.indutyName}
                />
              )
            })}
          </div>
          : <span className="self-start ml-50 mt-10 text-[#AAAAAA]">검색 결과가 없습니다.</span>}
      <div className="m-20"></div>
    </>
  );
}
