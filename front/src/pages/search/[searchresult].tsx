import { useState } from "react";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import BigCard from "@/components/home/BigCard";

export default function searchresult() {
  const [keyWord, setKeyWord] = useState<string>();

  return (
    <>
      <NavBar />
      <SearchBar />
      <hr></hr>
      <div className="w-[100vw] bg-analysisBg">
        <div className="mx-[12vw] py-[3vh] font-bold text-20">
          {keyWord}로 검색한 결과 입니다.
        </div>
        <div className="flex flex-wrap mx-[10vw]">
          <BigCard id={234} name="네이버1" />
          <BigCard id={234} name="네이버2" />
          <BigCard id={234} name="네이버3" />
          <BigCard id={234} name="네이버4" />
          <BigCard id={234} name="네이버5" />
          <BigCard id={234} name="네이버6" />
          <BigCard id={234} name="네이버7" />
          <BigCard id={234} name="네이버8" />
          <BigCard id={234} name="네이버9" />
          <BigCard id={234} name="네이버10" />
          <BigCard id={234} name="네이버11" />
          <BigCard id={234} name="네이버12" />
          <BigCard id={234} name="네이버13" />
        </div>
      </div>
    </>
  );
}
