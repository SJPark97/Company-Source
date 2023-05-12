import Image from "next/image";

export default function HotPost() {
  return (
    <div className="flex justify-between py-10 border-b-1">
      <div className="flex">
        <div className="mx-20">12344</div>
        <div className="line-clamp-1">
          이것은 핫 게시물의 제목 헝라먼ㅇ리ㅏㅓㅁ ㄴㅇ리ㅏㅓ
        </div>
      </div>
      <div className="flex ml-20 items-center">
        <Image
          src="/view.png"
          alt="조회수"
          width={88}
          height={60}
          className="w-22 h-15 pt-3 pr-5"
        />
        <div className="mr-20">3124</div>
      </div>
    </div>
  );
}
