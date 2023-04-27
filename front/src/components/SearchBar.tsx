import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/router";

export interface Iprops {
  getData: () => void;
}

export default function SearchBar({ getData }: any) {
  const router = useRouter();
  const [searchWord, setSearchWord] = useState<string | string[] | undefined>(
    ""
  );
  // 검색 키워드 onChange 함수
  const onChangeSearchWordHandler = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchWord(e.target.value);
  };

  // 검색 onSubmit 함수
  const onSubmitSearchHandler = (e: any): void => {
    e.preventDefault();
    getData(searchWord);
    localStorage.setItem("searchWord", searchWord as string);
    router.push({
      pathname: "/search/[searchresult]",
      query: { searchresult: searchWord },
    });
  };

  useEffect(() => {
    if (router !== undefined) {
      // console.log(router.query.searchresult);
      // setSearchWord(router.query.searchresult);
      localStorage.getItem("searchWord");
    }
  }, [router]);
  return (
    <>
      <div className="flex justify-center my-[5vh]">
        <form onSubmit={onSubmitSearchHandler}>
          <div className="relative w-[60vw]">
            <span className="absolute ml-15 mt-20">
              <Image
                src="/search.png"
                alt="search.png"
                width={20}
                height={20}
              />
            </span>
            {/* box shadow 요소 순서는 순서대로 좌우, 상하, 그림자의 흐려짐 정도 범위, 그림자의 크기 */}
            <input
              onChange={onChangeSearchWordHandler}
              placeholder="기업을 검색해 보세요."
              className="border-2 border-brand rounded-full w-[60vw] h-60
                        hover:shadow-whole shadow-brand
                        focus:outline-none placeholder-gray-400
                        px-40"
            />
          </div>
        </form>
      </div>
    </>
  );
}
