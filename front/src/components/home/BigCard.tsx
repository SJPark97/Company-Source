import Image from "next/image";
import Link from "next/link";

interface Iprops {
  id: string,
  name: string,
}

export default function BigCard({ id, name }: Iprops) {
  return (
    <>
      <Link href="/detail/[searchdetail]" as={`/detail/${id}`}>
        <div
          className="flex flex-col w-[190px] h-[300px]
          shadow-[0_35px_60px_-15px_rgba(0,0,0,0.3)] rounded-10
          mx-[26px] my-[26px] relative hover:scale-110"
        >
          <div className="absolute bg-brand w-[100%] h-[10px] rounded-tl-5 rounded-tr-5 top-0 left-0"></div>
          <Image
            src="/naver_logo_small.png"
            alt="naver.logo_small.png"
            width={200 - 52}
            height={0}
            className="mx-auto my-[50px]"
          />
          <div className="font-bold mx-[26px] mb-[26px] text-20">{name}</div>
          <div>
            <div className="text-gray-400 mx-[26px] w-[260-52px] mb-[25px] line-clamp-4">
              네이버는 대한민국 포털 사이트이다. 검색 엔진 등 포탈 서비스를
              중심으로 블로그, 카페, 포스트등의 커뮤니티 서비스를 비롯...
            </div>
          </div>
        </div>
      </Link>
    </>
  );
}
