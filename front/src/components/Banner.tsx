export default function Banner() {
  return (
    <div className="mb-[10vh]">
      <div className="flex flex-row justify-between bg-brand w-[100vw] h-[400px]">
        <div className="flex flex-col ml-[10vw]">
          <div className="text-white font-bold text-20  mt-[10vh]">
            지금 검색해 보세요.
          </div>
          <div className="text-white font-bold text-32  mt-[5vh]">
            코스피 상장 기업 정보를
          </div>
          <div className="text-white font-bold text-32  ">
            약 20개의 분석 방법으로 분석했습니다.
          </div>
        </div>
        <div className="bg-white w-[500px] h-[300px] mr-[10vw] mt-[5vh] rounded-lg">
          사진 들어갈 곳
        </div>
      </div>
    </div>
  );
}
