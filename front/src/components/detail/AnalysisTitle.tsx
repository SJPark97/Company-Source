interface Iprops {
  name: string,
  info: string,
}

export default function AnalysisTitle({ name, info }: Iprops) {

  return (
    <div className="flex text-white text-28">
      <div className="flex w-screen px-10 bg-blue-500 py-7 rounded-tr-5 rounded-tl-5">
        <div>
          {name}
        </div>
      </div>
    </div>
  )
}