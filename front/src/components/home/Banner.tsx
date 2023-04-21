import ChartCarousel from "./ChartCarousel";

export default function Banner() {
  return (
    <div className="mb-[10vh]">
      <div className="flex flex-row justify-between bg-brand w-[100vw] h-[400px]">
        <div className="flex flex-col ml-[10vw]">
          <div className="text-white font-bold mt-[10vh] lg:text-22 xl:text-25 2xl:text-28">
            지금 검색해 보세요.
          </div>
          <div className="text-white font-bold mt-[5vh] lg:text-26 xl:text-29 2xl:text-32">
            코스피 상장 기업 정보를
          </div>
          <div className="text-white font-bold lg:text-26 xl:text-29 2xl:text-32  ">
            약 20개의 분석 방법으로 분석했습니다.
          </div>
        </div>
        <div className="bg-white w-[500px] h-[300px] mr-[10vw] my-auto rounded-lg">
          <ChartCarousel />
        </div>
      </div>
    </div>
  );
}
