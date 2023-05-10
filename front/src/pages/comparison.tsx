import NavBar from "@/components/NavBar";
import Image from "next/image";

export default function Comparison() {
  return (
    <div className="flex flex-col items-center">
      {/* <NavBar /> */}
      <Image
        src="/service_waiting.jpg"
        alt="서비스 준비 페이지"
        width={700}
        height={1}
      />
      <div className="text-40 font-bold">서비스 준비중입니다.</div>
    </div>
  );
}
