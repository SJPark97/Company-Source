interface Iprops {
  corpAName: string,
  corpBName: string
}

export default function ComparisonLegend({ corpAName, corpBName }: Iprops) {
  return (
    <>
      <div className="flex justify-center mb-20">
        <div className="flex items-center">
          <div className="bg-[#82ca9d] w-10 h-8" />
          <span className="ml-5 mr-10 text-12">{corpAName}</span>
        </div>
        <div className="flex items-center">
          <div className="w-10 h-8 bg-[#8884d8]" />
          <span className="ml-5 mr-10 text-12">{corpBName}</span>
        </div>
      </div>
    </>
  )
}