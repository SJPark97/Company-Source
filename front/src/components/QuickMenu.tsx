import { useState, useEffect, useRef } from "react";
import Link from "next/link";
import { useRouter } from "next/router";

export default function QuickMenu() {
  const router = useRouter();
  const quickMenuRef = useRef<HTMLDivElement>(null);
  const [scrollY, setScrollY] = useState<number>(0);

  useEffect(() => {
    const handleScroll = (): void => {
      setScrollY(window.scrollY);
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  useEffect(() => {
    if (quickMenuRef.current) {
      const quickMenuTop = quickMenuRef.current.getBoundingClientRect().top;
      quickMenuRef.current.style.transform = `translateY(${scrollY}px)`;
      quickMenuRef.current.style.transition = "transform 1s ease-out";
    }
  }, [scrollY]);

  return (
    <div
      className="flex flex-col border-1 border-gray-200 rounded-10 absolute right-30 top-[300px] bg-white"
      ref={quickMenuRef}
    >
      <Link href="/community/corpboard">
        <div
          className={
            "p-10 rounded-tl-10 rounded-tr-10 " +
            `${
              router.pathname === "/community/corpboard"
                ? "text-white bg-brand"
                : "text-gray-400"
            }`
          }
        >
          기업 게시판
        </div>
      </Link>
      <hr></hr>
      <Link href="/community/freeboard">
        <div
          className={
            "p-10 " +
            `${
              router.pathname === "/community/freeboard"
                ? "text-white bg-brand"
                : "text-gray-400"
            }`
          }
        >
          자유 게시판
        </div>
      </Link>
      <hr></hr>
      <div className="p-10  text-gray-400">미정 게시판</div>
    </div>
  );
}
