import Image from "next/image";
import { useState, useEffect } from "react";

interface corpInformation {
  corpId: string;
  corpName: string;
  corpImg: string;
  indutyName: string;
  corpSize: string;
}

export default function RecommendCard({ corp }: { corp: corpInformation }) {
  const [hovered, setHovered] = useState(false);

  return (
    <div
      className={
        "relative w-[170px] h-[280px] bg-white border-3 border-brand rounded-10 transition duration-300 ease-in-out transform-gpu hover:scale-110 hover:shadow-md overflow-hidden"
      }
      onMouseEnter={() => setHovered(true)}
      onMouseLeave={() => setHovered(false)}
    >
      <div>
        {corp.corpImg ? (
          <div className="relative">
            <img
              src={corp.corpImg}
              alt="기업 로고 이미지"
              className="w-82 h-82 mx-auto my-20"
            />
            {/* <Image
              src={corp.corpImg}
              alt="기업 로고 이미지"
              // fill
              width={164}
              height={164}
              className="mx-auto w-[110px] h-[112px]"
              placeholder="blur"
              blurDataURL={corp.corpImg}
              loading="lazy"
            /> */}
          </div>
        ) : (
          <Image
            src="/company_default.jpg"
            alt="default 이미지"
            width={164}
            height={164}
            className="w-auto h-auto mx-auto"
            placeholder="blur"
            blurDataURL="/company_default.jpg"
            loading="lazy"
          />
        )}
      </div>
      <div className="px-20">
        <div className="font-bold text-center mt-10 line-clamp-1">
          {corp.corpName}
        </div>
        <div className="text-12 my-20 line-clamp-2">
          산업 분야 : {corp.indutyName}
        </div>
        <div className="text-12 line-clamp-1">
          기업 규모 : {corp.corpSize ? corp.corpSize : "정보 없음"}
        </div>
      </div>
      <div
        className={`bg-opacity-80 w-full h-full top-0 left-0 absolute ${
          hovered ? "visible" : "invisible"
        }`}
      >
        <span
          className={`absolute w-[20%] h-[200%] -top-[120px] -left-[200px] bg-white bg-opacity-60 blur-sm transform rotate-45 transition-all duration-1000 ${
            hovered ? "translate-x-[2000%]" : ""
          }`}
        ></span>
      </div>
    </div>
  );
}
