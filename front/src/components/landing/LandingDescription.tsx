interface Iprops {
  title: string;
  content: string;
}

export default function LandingDiscription({ title, content }: Iprops) {
  return (
    <div className="flex flex-col items-center opacity-0 animate-fadeIn">
      <div className="text-36 font-bold w-[32vw] ml-[10vw] whitespace-pre-line tracking-tighter">
        {title}
      </div>
      <div className="text-28 text-[#aaaaaa] font-bold w-[30vw] mt-[1vh] ml-[10vw] whitespace-pre-line tracking-tighter">
        {content}
      </div>
    </div>
  );
}
