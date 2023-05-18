import BigCard from "@/components/home/BigCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import Head from "next/head";
import Image from "next/image";
import HomeQuickMenu from "@/components/home/HomeQuickMenu";
import GoodCorpList from "@/components/home/GoodCorpList";
import { CSSTransition } from "react-transition-group";
import { MoonLoader } from "react-spinners";
// import { clearInterval } from "timers";

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
  const [indutyCorpSubject, setIndutyCorpSubject] = useState<string>("");
  const [indutyCorpList, setIndutyCorpList] = useState<Array<corpInformation>>(
    []
  );
  const [topSalesCorpSubject, setTopSalesCorpSubject] = useState<string>("");
  const [topSalesCorpList, setTopSalesCorpList] = useState<
    Array<corpInformation>
  >([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const loaderRef = useRef(null);

  const [activeBlockIndex, setActiveBlockIndex] = useState(0);
  const blocks = [
    { subject: goodCorpSubject, corpList: goodCorpList },
    { subject: indutyCorpSubject, corpList: indutyCorpList },
    { subject: topSalesCorpSubject, corpList: topSalesCorpList },
  ];

  const getRandomCorpList = async (page: number) => {
    setLoading(true);
    await axios.get(SERVER_URL + `/corp/randcorp/${page}`).then((res: any) => {
      setCorpList([...corpList, ...res.data.data]);
    });
    setLoading(false);
  };

  // 평가 지표 좋은 기업 리스트 불러오는 함수
  const getGoodCorpList = async () => {
    const res = await axios.get(SERVER_URL + "/corp/goodresult", {
      params: {
        // page: Math.floor(Math.random() * (10 - 0 + 1)) + 0,
        page: 0,
        size: 5,
      },
    });
    setGoodCorpSubject(res.data.data.kind);
    setGoodCorpList(res.data.data.corps);
  };

  // 산업별 기업 리스트 불러오는 함수
  const getIndutyCorpList = async () => {
    const res = await axios.get(SERVER_URL + "/corp/induty");
    setIndutyCorpSubject(res.data.data.kind);
    setIndutyCorpList(res.data.data.corps);
  };

  // 매출순 기업 리스트 불러오는 함수
  const getTopSalesCorpList = async () => {
    const res = await axios.get(SERVER_URL + "/corp/sales", {
      params: {
        page: Math.floor(Math.random() * (10 - 0 + 1)) + 0,
        size: 5,
      },
    });
    setTopSalesCorpSubject(res.data.data.kind);
    setTopSalesCorpList(res.data.data.corps);
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
    getIndutyCorpList();
    getTopSalesCorpList();
  }, []);

  useEffect(() => {
    const timer = setInterval(() => {
      setActiveBlockIndex((prevIndex) => (prevIndex + 1) % 3);
    }, 3000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  const getData = async (keyWord: string | string[] | undefined) => { };
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
                약 15개의 분석 방법으로 분석했습니다.
              </div>
            </div>
            <SearchBar getData={getData} />
          </div>
          <Image
            src="/home_background.jpg"
            alt="홈 이미지"
            fill
            className="absolute"
            placeholder="blur"
            blurDataURL="/home_background.jpg"
            loading="lazy"
          />
        </div>
        <HomeQuickMenu />
        <div>
          <div className="flex flex-col w-full h-430px bg-analysisBg">
            {blocks.map((block, index) => (
              <CSSTransition
                key={"추천" + `${index}`}
                in={index === activeBlockIndex}
                timeout={{ enter: 500, exit: 0, appear: true ? 0 : 500 }}
                // className="animate-fadeIn"
                mountOnEnter
                unmountOnExit
              >
                <div
                  className={
                    "relative h-full ease-in-out" +
                    `${index !== activeBlockIndex ? " hidden" : ""}`
                  }
                >
                  {block.corpList.length === 5 ? (
                    <GoodCorpList
                      subject={block.subject}
                      corpList={block.corpList}
                    />
                  ) : null}
                </div>
              </CSSTransition>
            ))}
          </div>
        </div>

        <div className="mx-auto flex flex-col max-w-[1300px]">
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
          <div className="flex flex-col items-center">
            <MoonLoader
              color="#AAAAAA"
              loading={true}
              size={40}
              className="my-30"
            />
          </div>
          {!loading && (
            <div ref={loaderRef} className="absolute bottom-[400px] z-50"></div>
          )}
        </div>
      </div>
    </>
  );
}
