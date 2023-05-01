import Banner from "@/components/home/Banner";
import BigCard from "@/components/home/BigCard";
import SmallCard from "@/components/home/SmallCard";
import NavBar from "@/components/NavBar";
import SearchBar from "@/components/SearchBar";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";

interface bigCard {
  corpId: string;
  corpName: string;
  corpImg: string;
}

export default function Home() {
  const [corpList, setCorpList] = useState<Array<bigCard>>([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const loaderRef = useRef(null);

  const getRandomCorpList = async (page: number) => {
    await axios
      .get(SERVER_URL + `/corp/randcorp/${page}`)
      .then((res) => setCorpList([...corpList, ...res.data]))
      .then(() => setLoading(false));
  };

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        const firstEntry = entries[0];
        console.log(entries);
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

  const getData = async (keyWord: string | string[] | undefined) => {};
  return (
    <>
      <NavBar />
      <SearchBar getData={getData} />
      <Banner />
      <div className="mx-[10vw] flex  w-[80vw]">
        <div className="flex flex-col w-[70vw]">
          <div className="ml-[26px] text-30 font-bold">상장 기업</div>
          <div className="flex flex-wrap">
            {corpList &&
              corpList.map((corp) => (
                <BigCard
                  id={corp.corpId}
                  name={corp.corpName}
                  image={corp.corpImg}
                />
              ))}
          </div>
          {loading && <div>loading 중 ...</div>}
          {!loading && <div ref={loaderRef}>loading more...</div>}
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
