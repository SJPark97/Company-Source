import Image from "next/image";

export default function SearchBar() {
  const searchHandler = (e: any) => {
    console.log(e.target.value);
  };

  return (
    <>
      <div className="flex justify-center my-[5vh]">
        <form>
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
              onChange={searchHandler}
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
