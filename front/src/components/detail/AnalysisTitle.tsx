interface Iprops {
  name: string
}

export default function AnalysisTitle({ name }: Iprops) {
  return (
    <div className="flex text-white text-28">
      <div className="w-screen px-10 bg-blue-500 py-7 rounded-tr-5 rounded-tl-5">
        {name}
      </div>
    </div>
  )
}