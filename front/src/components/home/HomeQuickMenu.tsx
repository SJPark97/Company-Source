import { useState, useEffect, useRef } from "react";
import Link from "next/link";
import { useRouter } from "next/router";
import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import Image from "next/image";

interface corpObject {
  corpId: string;
  corpName: string;
  corpImg?: string;
  corpSize?: string;
  industyName: string;
}

export default function HomeQuickMenu() {
  const router = useRouter();
  const quickMenuRef = useRef<HTMLDivElement>(null);
  const [scrollY, setScrollY] = useState<number>(0);
  const [hotCorpList, setHotCorpList] = useState<Array<corpObject>>([]);

  const getHotCorpList = async () => {
    await axios
      .get(SERVER_URL + `/corp/hotcorp`, {
        params: {
          page: 0,
          size: 5,
        },
      })
      .then((res) => setHotCorpList(res.data.data));
  };

  useEffect(() => {
    getHotCorpList();
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
      className="flex flex-col items-center border-1 border-gray-200 rounded-tr-10 rounded-tl-10 absolute right-30 top-[100px] bg-white w-[100px] shadow-lg"
      ref={quickMenuRef}
    >
      <div className="bg-white font-bold rounded-tr-9 rounded-tl-9">Hot</div>
      <hr className="w-full items-center"></hr>
      {hotCorpList &&
        hotCorpList.map((corp, index) => (
          <Link
            href="/detail/[searchdetail]"
            as={`/detail/${corp.corpId}`}
            className="w-full"
            key={`Quick + ${corp.corpName}`}
          >
            <div className="bg-white items-center w-full h-[20%] flex flex-col">
              {corp.corpImg ? (
                <Image
                  src={corp.corpImg}
                  alt="기업 로고"
                  width={60}
                  height={0}
                  className="mx-auto rounded-5 mt-10 h-50"
                />
              ) : (
                <Image
                  src="/company_default.jpg"
                  alt="기업 로고"
                  width={60}
                  height={50}
                  className="mx-auto rounded-5 mt-10 h-50"
                />
              )}
              <div className="font-bold text-center text-13 mb-10 line-clamp-1">
                {corp.corpName}
              </div>
              <div className="text-13">{corp.industyName}</div>
              {index !== hotCorpList.length - 1 ? (
                <div className="border-1 w-full"></div>
              ) : null}
            </div>
          </Link>
        ))}
    </div>
  );
}
