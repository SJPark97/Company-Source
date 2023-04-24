interface Iprops {
  title: string,
  content: string
}

export default function OverviewContent({ title, content }: Iprops) {
  return (
    <div className="flex">
      <div className="mx-10 text-20">{title}</div>
      <div className="mx-10 text-[#AAAAAA] text-18">{content}</div>
    </div>
  )
}