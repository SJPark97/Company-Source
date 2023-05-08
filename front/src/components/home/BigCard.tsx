import Image from "next/image";
import Link from "next/link";

interface Iprops {
  id: string;
  name: string;
  image?: string;
}

export default function BigCard({ id, name, image }: Iprops) {
  return (
    <>
      <Link href="/detail/[searchdetaipl]" as={`/detail/${id}`}>
        <div
          className="flex flex-col w-[190px] h-[190px]
          shadow-[0_35px_60px_-15px_rgba(0,0,0,0.3)] rounded-10
          mx-[26px] my-[26px] relative hover:transform hover:scale-110 transition-transform duration-200 ease-out bg-white"
        >
          <div className="absolute bg-brand w-[100%] h-[10px] rounded-tl-5 rounded-tr-5 top-0 left-0"></div>
          {image ? (
            <Image
              src={image}
              alt="naver.logo_small.ng"
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

          <div className="font-bold mx-auto mt-[30px] mb-[20px] text-15 line-clamp-1">
            {name}
          </div>
        </div>
      </Link>
    </>
  );
}
