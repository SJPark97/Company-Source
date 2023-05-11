import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import BigCard from "@/components/home/BigCard";
import { SERVER_URL } from "@/utils/url";
import { useRouter } from "next/router";
import HomeQuickMenu from "@/components/home/HomeQuickMenu";

type bigCard = {
  corpId: string;
  corpName: string;
  corpImg: string;
};

export default function searchresult() {
  const [searchResult, setSearchResult] = useState<Array<bigCard>>([]);
  const getData = async (keyWord: string | undefined) => {
    const { data } = await axios.get(SERVER_URL + `/corp/list/${keyWord}`);
    setSearchResult(data.data);
  };

  const router = useRouter();
  useEffect(() => {
    if (router !== undefined) {
      getData(router.query.searchresult as string);
    }
  }, [router]);

  return (
    <div className="relative">
      <div className="h-[250px] bg-white">
        <NavBar />
        <HomeQuickMenu />
        <SearchBar getData={getData} />
      </div>
      <hr></hr>
      <div className="w-[100vw] bg-analysisBg">
        <div className="mx-[12vw] py-[3vh] font-bold text-20">
          '{router.query && router.query.searchresult}' 으로 검색한 결과입니다.
        </div>
        <div className="flex flex-wrap mx-[10vw]">
          {searchResult &&
            searchResult.map((corp) => (
              <BigCard
                id={corp.corpId}
                name={corp.corpName}
                image={corp.corpImg}
                key={"search" + `${corp.corpName}`}
              />
            ))}
        </div>
      </div>
    </div>
  );
}
