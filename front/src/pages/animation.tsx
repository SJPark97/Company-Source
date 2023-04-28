// import useIntersectionObserver from "@/hooks/useIntersectionObserver";
import { useRef, useLayoutEffect, useState } from "react";

export default function Animation() {
  const [isInViewport1, setIsInViewport1] = useState(false);
  const [isInViewport2, setIsInViewport2] = useState(false);
  const [isInViewport3, setIsInViewport3] = useState(false);
  const [isInViewport4, setIsInViewport4] = useState(false);
  const [isInViewport5, setIsInViewport5] = useState(false);

  const ref1 = useRef<HTMLDivElement>(null);
  const ref2 = useRef<HTMLDivElement>(null);
  const ref3 = useRef<HTMLDivElement>(null);
  const ref4 = useRef<HTMLDivElement>(null);
  const ref5 = useRef<HTMLDivElement>(null);

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
      if (ref3.current) {
        const { top, bottom } = ref3.current.getBoundingClientRect();
        setIsInViewport3(top < window.innerHeight && bottom > 0);
      }
      if (ref4.current) {
        const { top, bottom } = ref4.current.getBoundingClientRect();
        setIsInViewport4(top < window.innerHeight && bottom > 0);
      }
      if (ref5.current) {
        const { top, bottom } = ref5.current.getBoundingClientRect();
        setIsInViewport5(top < window.innerHeight && bottom > 0);
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);
  // const ref = useRef<HTMLDivElement>(null);
  // const ref2 = useRef<HTMLDivElement>(null);
  // const ref3 = useRef<HTMLDivElement>(null);
  // const ref4 = useRef<HTMLDivElement>(null);
  // const ref5 = useRef<HTMLDivElement>(null);
  // const isInViewport = useIntersectionObserver(ref);
  // const isInViewport2 = useIntersectionObserver(ref2);
  // const isInViewport3 = useIntersectionObserver(ref3);
  // const isInViewport4 = useIntersectionObserver(ref4);
  // const isInViewport5 = useIntersectionObserver(ref5);

  return (
    <div className="bg-black flex flex-col items-center">
      <div className="mb-[500px]">
        <div
          className={`${isInViewport1 ? "animate-fadeIn text-white" : ""}`}
          ref={ref1}
        >
          애니메이션 연습 페이지
        </div>
      </div>
      <div className="mb-[500px]">
        <div
          className={`${isInViewport2 ? "animate-fadeIn text-white" : ""}`}
          ref={ref2}
        >
          애니메이션 연습 페이지2
        </div>
      </div>
      <div className="mb-[500px]">
        <div
          className={`${isInViewport3 ? "animate-fadeIn text-white" : ""}`}
          ref={ref3}
        >
          애니메이션 연습 페이지3
        </div>
      </div>
      <div className="mb-[500px]">
        <div
          className={`${isInViewport4 ? "animate-fadeIn text-white" : ""}`}
          ref={ref4}
        >
          애니메이션 연습 페이지
        </div>
      </div>
      <div className="mb-[500px]">
        <div
          className={`${isInViewport5 ? "animate-fadeIn text-white" : ""}`}
          ref={ref5}
        >
          애니메이션 연습 페이지
        </div>
      </div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
      <div className="text-white font-bold text-20">여긴 아니야</div>
    </div>
  );
}
