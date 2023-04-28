import { useRef, useLayoutEffect, useState } from "react";

interface Iprops {
  title: string;
  content: string;
}

export default function LandingDiscription({ title, content }: Iprops) {
  const [isInViewport1, setIsInViewport1] = useState(false);
  const [isInViewport2, setIsInViewport2] = useState(false);

  const ref1 = useRef<HTMLDivElement>(null);
  const ref2 = useRef<HTMLDivElement>(null);

  useLayoutEffect(() => {
    const handleScroll = () => {
      if (ref1.current) {
        const { top, bottom } = ref1.current.getBoundingClientRect();
        setIsInViewport1(top < window.innerHeight && bottom > 0);
      }
      if (ref2.current) {
        const { top, bottom } = ref2.current.getBoundingClientRect();
        setIsInViewport2(top < window.innerHeight && bottom > 0);
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);
  return (
    <div className="flex flex-col items-center">
      <div
        className={
          "text-36 font-bold w-[32vw] ml-[10vw] whitespace-pre-line tracking-tighter " +
          `${isInViewport1 ? "animate-fadeIn" : ""}`
        }
        ref={ref1}
      >
        {title}
      </div>
      <div
        className={
          "text-28 text-[#aaaaaa] font-bold w-[30vw] mt-[1vh] ml-[10vw] whitespace-pre-line tracking-tighter " +
          `${isInViewport2 ? "animate-fadeIn" : ""}`
        }
        ref={ref2}
      >
        {content}
      </div>
    </div>
  );
}
