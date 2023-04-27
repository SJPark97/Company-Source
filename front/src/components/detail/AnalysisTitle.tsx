interface Iprops {
  name: string,
  rate: string,
}

export default function AnalysisTitle({ name, rate }: Iprops) {
  return (
    <div className="flex text-white text-28">
      <div className="flex w-screen px-10 bg-blue-500 py-7 rounded-tr-5 rounded-tl-5">
        <div>
          {name}
        </div>
        {/* rate 조건에 따라 배경 색깔 분리시키기 : 양호는 초록, 보통은 주황, 불량은 빨강 */}
        <div className="bg-red-400 mx-[1vw] px-[1vw] rounded-5">
          {rate}
        </div>
      </div>
    </div>
  )
}