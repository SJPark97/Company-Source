import Image from "next/image";

export default function HotPost() {
  return (
    <div className="flex justify-between">
      <div className="flex">
        <div className="mx-20 border-1 border-black">12344</div>
        <div className="border-1 border-black line-clamp-1">
          이것은 핫 게시물의 제목 헝라먼ㅇ리ㅏㅓㅁ ㄴㅇ리ㅏㅓ
        </div>
      </div>
      <div className="flex ml-20">
        <Image src="/like.png" alt="좋아요" width={27} height={24} />
        <div className="mr-20 border-1 border-black">3124</div>
      </div>
    </div>
  );
}
