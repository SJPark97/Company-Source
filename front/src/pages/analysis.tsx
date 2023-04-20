import { useState } from "react";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import BigCard from "@/components/home/BigCard";

export default function Analysis() {

  const [keyWord, setKeyWord] = useState<string>()

  return (
    <>
      <NavBar />
      <SearchBar />
      <hr></hr>
      <div className="w-[100vw] bg-analysisBg">
        <div className="mx-[12vw] py-[3vh] font-bold text-20">{keyWord}로 검색한 결과 입니다.</div>
        <div className="flex flex-wrap mx-[10vw]">
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
          <BigCard />
        </div>
      </div>
    </>
  );
}
