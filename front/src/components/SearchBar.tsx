import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/router";

export interface Iprops {
  getData: (keyWord: string | string[] | undefined) => void;
}

export default function SearchBar({ getData }: Iprops) {
  const router = useRouter();
  const [searchWord, setSearchWord] = useState<
    string | string[] | undefined | null
  >(undefined);

  // 검색 키워드 onChange 함수
  const onChangeSearchWordHandler = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchWord(e.target.value);
  };

  // 검색 onSubmit 함수
  const onSubmitSearchHandler = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    if (searchWord === undefined) {
      alert("asdfsd");
      return;
    } else if ((searchWord as string).length <= 1) {
      alert("1글자 미만 X");
      return;
    }

    getData(searchWord as string);
    router.push({
      pathname: "/search/[searchresult]",
      query: { searchresult: searchWord },
    });
  };

  return (
    <>
      <div className="flex justify-center my-[50px]">
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
              value={
                searchWord === undefined
                  ? router.query.searchresult?.toString()
                  : searchWord?.toString()
              }
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
