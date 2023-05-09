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
      <Link href="/detail/[searchdetail]" as={`/detail/${id}`}>
        <div
          className="flex flex-col w-[190px] h-[190px]
          shadow-[0_35px_60px_-15px_rgba(0,0,0,0.3)] rounded-10
          mx-[26px] my-[26px] relative hover:transform hover:scale-110 transition-transform duration-200 ease-out bg-white"
        >
          <div className="bg-brand w-[100%] h-[10px] rounded-tl-5 rounded-tr-5"></div>
          {image ? (
            <div className="relative">
              <Image
                src={image}
                alt="naver.logo_small.ng"
                // fill
                width={164}
                height={164}
                className="mx-auto w-[110px] h-[126px]"
                placeholder="blur"
                blurDataURL={image}
              />
            </div>
          ) : (
            <Image
              src="/company_default.jpg"
              alt="default_image"
              width={164}
              height={164}
              className="mx-auto w-auto h-auto"
              placeholder="blur"
              blurDataURL="/company_default.jpg"
            />
          )}

          <div className="font-bold mx-auto mt-[10px] mb-[20px] text-15 line-clamp-1">
            {name}
          </div>
        </div>
      </Link>
    </>
  );
}
