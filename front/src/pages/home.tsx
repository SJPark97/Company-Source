import Banner from "@/components/home/Banner";
import BigCard from "@/components/home/BigCard";
import SmallCard from "@/components/home/SmallCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import { useState } from "react";

// const API_URL_1 = "http://192.168.31.142:8080/api/v1/analysis/101/234"
// const API_URL_2 = "http://192.168.31.142:8080/api/v1/analysis/103/234"

export default function Home() {
  const [tempCompanyInfo, setTempCompanyInfo] = useState({ id: 234, name: "잡탕마을" });

  return (
    <>
      <NavBar />
      <SearchBar />
      <Banner />
      <div className="mx-[10vw] flex  w-[80vw]">
        <div className="flex flex-col w-[70vw]">
          <div className="ml-[26px] text-30 font-bold">상장 기업</div>
          <div className="flex flex-wrap">
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
            <BigCard id={tempCompanyInfo.id} name={tempCompanyInfo.name} />
          </div>
        </div>
        <div className="flex flex-col w-[40vw]">
          <div className="font-bold text-30">주제별 List</div>
          <div className="flex flex-col">
            <SmallCard />
            <SmallCard />
            <SmallCard />
          </div>
        </div>
      </div>
    </>
  );
}



