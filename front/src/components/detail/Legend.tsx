export default function Legend() {
  return (
    <>
      <div className="flex justify-center mb-20">
        <div className="flex items-center">
          <div className="bg-[#8884d8] w-10 h-8" />
          <span className="ml-5 mr-10 text-12">산업평균</span>
        </div>
        <div className="flex items-center">
          <div className="w-10 h-8 bg-[#fef33f]" />
          <span className="ml-5 mr-10 text-12">보통</span>
        </div>
        <div className="flex items-center">
          <div className="w-10 h-8 bg-[#ff0000]" />
          <span className="ml-5 mr-10 text-12">불량</span>
        </div>
        <div className="flex items-center">
          <div className="bg-[#82ca9d] w-10 h-8" />
          <span className="ml-5 text-12">양호</span>
        </div>
      </div>
    </>
  )
}