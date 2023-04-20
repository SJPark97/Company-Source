import Image from "next/image";

export default function SmallCard() {
  return (
    <div
      className="flex relative w-[400px] my-[10px] hover:scale-110
            "
    >
      <div className="absolute bg-brand h-[100%] w-[10px] rounded-tl-5 rounded-bl-5"></div>
      <Image
        src="/samsung_logo.png"
        alt="samsung_logo.png"
        width={200}
        height={259}
        className="shadow-[10px_20px_10px_-15px_rgba(0,0,0,0.3)] rounded-10 p-20"
      />
      <div className="my-auto mx-[10px]">
        삼성전자(SAMSUNG ELECTRONICS)는 대한민국에 본사를 두고 전자제품을 ...
      </div>
    </div>
  );
}
