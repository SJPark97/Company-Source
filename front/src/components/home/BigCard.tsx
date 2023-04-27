import Image from "next/image";
import Link from "next/link";

interface Iprops {
  id: string;
  name: string;
  image: string;
}

export default function BigCard({ id, name, image }: Iprops) {
  return (
    <>
      <Link href="/detail/[searchdetaipl]" as={`/detail/${id}`}>
        <div
          className="flex flex-col w-[190px] h-[300px]
          shadow-[0_35px_60px_-15px_rgba(0,0,0,0.3)] rounded-10
          mx-[26px] my-[26px] relative hover:scale-110 bg-white"
        >
          <div className="absolute bg-brand w-[100%] h-[10px] rounded-tl-5 rounded-tr-5 top-0 left-0"></div>
          {image ? (
            <Image
              src={image}
              alt="naver.logo_small.ng"
              // width={200 - 52}
              width={100}
              height={0}
              className="mx-auto mt-[20px] h-[100]"
              placeholder="blur"
              blurDataURL={image}
            />
          ) : (
            <Image
              src="/company_default.jpg"
              alt="default_image"
              width={190}
              height={0}
              className="mx-auto h-[120px] rounded-tr-10 rounded-tl-10"
              placeholder="blur"
              blurDataURL="/company_default.jpg"
            />
          )}

          <div className="font-bold mx-[26px] mt-[30px] mb-[20px] text-15 line-clamp-1">
            {name}
          </div>
          <div>
            <div className="text-gray-400 mx-[26px] w-[260-52px] mt-[10px] line-clamp-4 text-12">
              네이버는 대한민국 포털 사이트이다. 검색 엔진 등 포탈 서비스를
              중심으로 블로그, 카페, 포스트등의 커뮤니티 서비스를 비롯...
            </div>
          </div>
        </div>
      </Link>
    </>
  );
}
