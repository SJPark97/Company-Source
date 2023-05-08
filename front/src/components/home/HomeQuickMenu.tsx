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
      className="flex flex-col border-1 border-gray-200 rounded-tr-10 rounded-tl-10 rounded-br-10 rounded-bl-10 absolute right-30 top-[100px] bg-white"
      ref={quickMenuRef}
    >
      <div className="bg-white font-bold rounded-tr-9 rounded-tl-9 mx-auto">
        Hot
      </div>
      <hr></hr>
      {hotCorpList &&
        hotCorpList.map((corp) => (
          <div className="bg-white items-center w-[130px] h-[20%]">
            {corp.corpImg ? (
              <Image
                src={corp.corpImg}
                alt="기업 로고"
                width={60}
                height={50}
                className="mx-auto"
              />
            ) : (
              <Image
                src="/company_default.jpg"
                alt="기업 로고"
                width={50}
                height={50}
                className="mx-auto"
              />
            )}
            <div className="font-bold text-center text-13 mb-10">
              {corp.corpName}
            </div>
            <div className="text-13">{corp.industyName}</div>
            <hr></hr>
          </div>
        ))}
    </div>
  );
}
