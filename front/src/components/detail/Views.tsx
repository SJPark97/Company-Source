interface Iprops {
  total: string,
  yesterday: string,
}

export default function Views({ total, yesterday }: Iprops) {
  return (
    <div className="self-center">
      yesterday<span className="ml-10 text-gray-400">{yesterday}</span>
      <span className="mx-15">|</span>
      total<span className="ml-10 text-gray-400">{total}</span>
    </div>
  )
}