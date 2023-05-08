import { useState } from "react";

export default function BoardSearchBar() {
  const [searchType, setSearchType] = useState<string>("");
  const [searchWord, setSearchWord] = useState<string | undefined | null>("");

  const searchTypeHandler = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSearchType(e.target.value);
  };

  const searchWordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchWord(e.target.value);
  };

  return (
    <>
      <div className="flex border-3 border-brand h-60 w-[600px]">
        <div className="relative inline-flex">
          <select
            className="text-center appearance-none border-0 outline-none focus:ring-0 py-2 pr-40 w-[150px]"
            value={searchType}
            onChange={searchTypeHandler}
          >
            <option>제목+내용</option>
            <option>작성자</option>
            <option>제목</option>
          </select>
          <div className="absolute inset-y-0 right-0 flex items-center pointer-events-none bg-brand">
            <svg
              className="w-40 h-40 fill-current text-white"
              viewBox="0 0 20 20"
            >
              <path
                fillRule="evenodd"
                d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                clipRule="evenodd"
              />
            </svg>
          </div>
        </div>
        <div className="w-full">
          <form className="h-full">
            <input
              type="text"
              className="w-full h-full outline-none pl-10"
              onChange={searchWordHandler}
            />
          </form>
        </div>
      </div>
    </>
  );
}
