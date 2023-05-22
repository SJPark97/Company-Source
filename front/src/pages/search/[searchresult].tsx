import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import BigCard from "@/components/home/BigCard";
import { SERVER_URL } from "@/utils/url";
import { useRouter } from "next/router";
import HomeQuickMenu from "@/components/home/HomeQuickMenu";
import Head from "next/head";

type bigCard = {
  corpId: string;
  corpName: string;
  corpImg: string;
};

export default function searchresult() {
  const [searchResult, setSearchResult] = useState<Array<bigCard>>([]);
  const getData = async (keyWord: string | undefined) => {
    const { data } = await axios.get(SERVER_URL + `/corp/list/`, {
      params: {
        inputValue: keyWord,
      },
    });
    setSearchResult(data.data);
  };

  const router = useRouter();
  useEffect(() => {
    if (router !== undefined) {
      getData(router.query.searchresult as string);
    }
  }, [router]);

  return (
    <>
      <Head>
        <title>컴퍼니소스 | 기업 검색 결과</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content={`컴퍼니소스에서 "${searchResult ? router.query && router.query.searchresult : "기업"
            }"으로 검색한
          결과입니다.}`}
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content={`https://company-source.com/${searchResult ? router.query && router.query.searchresult : ""}`} />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
        <meta
          name="google-site-verification"
          content="0FzOO996BLTIEWFgwlmmYv-F1WmHiM6SrbwEbK9-p3k"
        />
      </Head>
      <div className="relative z-0 h-screen bg-analysisBg">
        <div className="h-[250px] bg-white">
          <NavBar />
          <HomeQuickMenu />
          <SearchBar getData={getData} />
        </div>
        <hr></hr>
        <div className="w-[100vw]">
          {searchResult ? (
            <div className="mx-[12vw] py-[3vh] font-bold text-20">
              '{router.query && router.query.searchresult}' 으로 검색한
              결과입니다.
            </div>
          ) : (
            <div className="mx-[12vw] py-[3vh] font-bold text-20">
              '{router.query && router.query.searchresult}' 검색결과가 없습니다.
            </div>
          )}

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
    </>
  );
}
