import Banner from "@/components/home/Banner";
import BigCard from "@/components/home/BigCard";
import SmallCard from "@/components/home/SmallCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import { useState } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";

export default function Home() {
  const [tempCompanyInfo, setTempCompanyInfo] = useState({
    id: "77777777",
    name: "잡탕마을",
  });
  const [searchResult, setSearchResult] = useState<Array<typeof BigCard>>([]);
  const getData = async (keyWord: string) => {
    const { data } = await axios.get(SERVER_URL + `/corp/list/${keyWord}`);
    setSearchResult(data);
    console.log(data);
  };
  return (
    <>
      <NavBar />
      <SearchBar getData={getData} />
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
