import Image from "next/image";

export default function ComparisonSearchBar() {

  return (
    <>
      <div className="flex h-40 px-40 mx-40 mt-40 mb-10 placeholder-gray-400 border-2 rounded-full w-[400px] border-brand">
        <Image
          src="/search.png"
          alt="search.png"
          width={20}
          height={20}
          className="self-center w-20 h-20 mr-10"
        />
        <input
          onChange={() => { }}
          placeholder="기업을 검색해 보세요."
          className="w-full border-none outline-none"
        />
      </div>
      {/* box shadow 요소 순서는 순서대로 좌우, 상하, 그림자의 흐려짐 정도 범위, 그림자의 크기 */}
    </>
  );
}
