import BigCard from "@/components/home/BigCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import Head from "next/head";
import Image from "next/image";
import HomeQuickMenu from "@/components/home/HomeQuickMenu";
import FirstSubject from "@/components/home/GoodCorpList";
import { get } from "http";
import GoodCorpList from "@/components/home/GoodCorpList";

interface bigCard {
  corpId: string;
  corpName: string;
  corpImg: string;
}

interface corpInformation {
  corpId: string;
  corpName: string;
  corpImg: string;
  indutyName: string;
  corpSize: string;
}

export default function Home() {
  const [corpList, setCorpList] = useState<Array<bigCard>>([]);
  const [goodCorpSubject, setGoodCorpSubject] = useState<string>("");
  const [goodCorpList, setGoodCorpList] = useState<Array<corpInformation>>([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const loaderRef = useRef(null);

  const getRandomCorpList = async (page: number) => {
    setLoading(true);
    await axios.get(SERVER_URL + `/corp/randcorp/${page}`).then((res: any) => {
      setCorpList([...corpList, ...res.data.data]);
    });
    setLoading(false);
  };

  const getGoodCorpList = async () => {
    const res = await axios.get(SERVER_URL + "/corp/goodresult", {
      params: {
        page: 1,
        size: 5,
      },
    });
    setGoodCorpSubject(res.data.data.kind);
    setGoodCorpList(res.data.data.corps);
  };

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        const firstEntry = entries[0];
        if (firstEntry.isIntersecting && !loading) {
          setPage((prevPage) => prevPage + 1);
        }
      },
      { threshold: 1 }
    );
    if (loaderRef.current) {
      observer.observe(loaderRef.current);
    }

    return () => {
      if (loaderRef.current) {
        observer.unobserve(loaderRef.current);
      }
    };
  }, [loading]);

  // 페이지 바뀌면
  useEffect(() => {
    setLoading(true);
    getRandomCorpList(page);
  }, [page]);

  useEffect(() => {
    getGoodCorpList();
  }, []);

  const getData = async (keyWord: string | string[] | undefined) => {};
  return (
    <>
      <Head>
        <title>컴퍼니소스 기업검색</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta name="description" content="컴퍼니소스 기업검색 페이지입니다." />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/home" />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
      </Head>
      <div className="relative">
        <div className="relative h-[400px]">
          <div className="absolute z-50">
            <NavBar />
            <div className="flex flex-col items-center mt-[50px]">
              <div
                className="font-bold text-white lg:text-26 xl:text-29 2xl:text-32 text-shadow animate-fadeIn"
                style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
              >
                코스피 상장 기업 정보를
              </div>
              <div
                className="font-bold text-white lg:text-26 xl:text-29 2xl:text-32 text-shadow animate-fadeIn"
                style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
              >
                약 10개의 분석 방법으로 분석했습니다.
              </div>
            </div>
            <SearchBar getData={getData} />
          </div>
          <Image
            src="/home_background.jpg"
            alt="홈 이미지"
            fill
            className="absolute"
          />
        </div>
        <HomeQuickMenu />

        {/* 추천 div */}
        <div className="flex flex-col w-[full] h-[430px] bg-analysisBg">
          {/* 평가별 좋은 기업 추천 */}
          <div>
            <GoodCorpList subject={goodCorpSubject} corpList={goodCorpList} />
          </div>
        </div>

        <div className="mx-auto flex max-w-[1300px]">
          <div className="flex flex-wrap justify-around">
            {corpList &&
              corpList.map((corp) => (
                <BigCard
                  id={corp.corpId}
                  name={corp.corpName}
                  image={corp.corpImg}
                  key={"home" + `${corp.corpName}`}
                />
              ))}
          </div>
          {loading && <div></div>}
          {!loading && (
            <div ref={loaderRef} className="absolute bottom-[400px]"></div>
          )}
        </div>
      </div>
    </>
  );
}
