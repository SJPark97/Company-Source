interface Iprops {
  title: string,
  content: string,
  position: 'left' | 'right',
}

export default function LandingDiscription({ title, content, position }: Iprops) {
  return (
    <div className="flex flex-col">
      {position == 'left' && <div>
        <div className="text-[calc(2.1vw+1.8vh)] font-bold w-[40vw] mt-[35vh] ml-[10vw] whitespace-pre-line">{title}</div>
        <div className="text-[calc(1.6vw+1.2vh)] text-[#aaaaaa] font-bold w-[35vw] mt-[1vh] ml-[12vw]">{content}</div>
      </div>
      }

      {position == 'right' && <div>
        <div className="text-[calc(2.1vw+1.8vh)] font-bold w-[40vw] mt-[35vh] ml-[50vw] whitespace-pre-line">{title}</div>
        <div className="text-[calc(1.6vw+1.2vh)] text-[#aaaaaa] font-bold w-[35vw] mt-[1vh] ml-[52vw]">{content}</div>
      </div>
      }
    </div>
  )
}