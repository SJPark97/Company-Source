import { useState, useEffect } from "react";
import Image from "next/image";
import Link from "next/link";
import { useRouter } from "next/router";
import { SERVER_URL } from "@/utils/url";
import axios from "axios";

interface autoComplete {
  corpId: string;
  corpName: string;
}

export interface Iprops {
  getData: (keyWord: string | undefined) => void;
}

export default function SearchBar({ getData }: Iprops) {
  const router = useRouter();
  const source = axios.CancelToken.source();

  const [searchWord, setSearchWord] = useState<string>("");
  const [isHaveInputValue, setIsHaveInputValue] = useState<boolean>(false);
  const [autoCompleteList, setAutoCompleteList] = useState<Array<autoComplete>>(
    []
  );

  // 검색 키워드 onChange 함수
  const onChangeSearchWordHandler = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchWord(e.target.value);
    if (e.target.value) {
      setIsHaveInputValue(true);
    } else if (!e.target.value) {
      setIsHaveInputValue(false);
    }
  };

  // 검색 onSubmit 함수
  const onSubmitSearchHandler = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    if (searchWord === undefined) {
      alert("유효하지 않은 검색어 입니다.");
      return;
    } else if ((searchWord as string).length <= 1) {
      alert("검색어를 2글자 이상으로 작성해주세요.");
      return;
    }

    getData(searchWord as string);
    setAutoCompleteList([]);
    router.push({
      pathname: "/search/[searchresult]",
      query: { searchresult: searchWord },
    });
  };

  // input태그 밖 누르면 자동완성창 닫는 Handler
  const onBlurInputHandler = () => {
    // 비동기 때문에 키워드가 onchange 때문에 handler순서가 안맞음 그래서 settimeout으로 100지연시켜서 닫히게함
    setTimeout(() => {
      setAutoCompleteList([]);
    }, 100);
  };

  // 자동완성 검색어 Highlight 해주는 함수
  const highlightSearchWord = (text: string, searchWord: string) => {
    if (!searchWord) return text;
    const regex = new RegExp(`(${searchWord})`, "gi");
    return text.split(regex).map((chunk, index) =>
      regex.test(chunk) ? (
        <span className="font-bold text-brand" key={index}>
          {chunk}
        </span>
      ) : (
        chunk
      )
    ) as React.ReactNode;
  };

  // 검색어 바뀔때마다 자동완성 리스트 갱신
  useEffect(() => {
    let canceled = false;
    if (searchWord) {
      axios
        .get(SERVER_URL + `/corp/autosearch/`, {
          params: {
            inputValue: searchWord,
          },
        })
        .then((res) => {
          if (!canceled) {
            if (res.status === 200) {
              setAutoCompleteList(res.data.data);
            }
          }
        })
    } else {
      setIsHaveInputValue(false);
      setAutoCompleteList([]);
    }
    return () => {
      canceled = true;
    };
  }, [searchWord]);

  useEffect(() => {
    if (router.query.searchresult) {
      setSearchWord(router.query.searchresult as string);
      setIsHaveInputValue(false);
    }
  }, [router]);

  return (
    <>
      <div className="flex justify-center my-[50px]">
        <div className="flex flex-col">
          <form onSubmit={onSubmitSearchHandler}>
            <div className="relative w-[60vw]">
              <span className="absolute mt-20 ml-15">
                <Image
                  src="/search.png"
                  alt="search.png"
                  width={20}
                  height={20}
                />
              </span>
              {/* box shadow 요소 순서는 순서대로 좌우, 상하, 그림자의 흐려짐 정도 범위, 그림자의 크기 */}
              <input
                onBlur={onBlurInputHandler}
                onChange={onChangeSearchWordHandler}
                defaultValue={searchWord}
                placeholder="기업을 검색해 보세요."
                className={
                  "border-brand w-[60vw] h-60 hover:shadow-whole shadow-brand focus:outline-none placeholder-gray-400 px-40 " +
                  `${autoCompleteList.length && isHaveInputValue
                    ? "rounded-tr-30 rounded-tl-30 border-t-2 border-l-2 border-r-2"
                    : "rounded-full border-brand border-2"
                  }`
                }
              />
            </div>
          </form>
          <div
            className={
              "bg-white z-50 border-brand " +
              `${autoCompleteList.length && isHaveInputValue ? "border-2" : ""}`
            }
          >
            {autoCompleteList.length > 0 &&
              isHaveInputValue &&
              autoCompleteList.map((item) => (
                <Link
                  href="/detail/[searchdetail]"
                  as={`/detail/${item.corpId}`}
                  key={"autocomplete" + `${item.corpName}`}
                >
                  <div className="relative flex p-10 border-1 hover:bg-gray-200">
                    <span className="absolute top-13 left-15">
                      <Image
                        src="/search.png"
                        alt="search.png"
                        width={20}
                        height={20}
                      />
                    </span>
                    <div className="text-black pl-30">
                      {highlightSearchWord(item.corpName, searchWord)}
                    </div>
                  </div>
                </Link>
              ))}
          </div>
        </div>
      </div>
    </>
  );
}
