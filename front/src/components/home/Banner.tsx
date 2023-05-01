export default function Banner() {
  return (
    <div className="mb-[10vh]">
      <div className="flex flex-row justify-between w-[100%] h-[500px] bg-cover bg-[url('/carousel3.jpg')]">
        <div className="flex flex-col ml-[10vw] animate-fadeInRight">
          <div
            className="text-white font-bold mt-[10vh] lg:text-22 xl:text-25 2xl:text-28 text-shadow"
            style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
          >
            지금 검색해 보세요.
          </div>
          <div
            className="text-white font-bold mt-[5vh] lg:text-26 xl:text-29 2xl:text-32 text-shadow"
            style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
          >
            코스피 상장 기업 정보를
          </div>
          <div
            className="text-white font-bold lg:text-26 xl:text-29 2xl:text-32"
            style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
          >
            약 20개의 분석 방법으로 분석했습니다.
          </div>
        </div>
      </div>
    </div>
  );
}
