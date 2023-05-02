import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import BoardSearchBar from "@/components/community/BoardSearchBar";
import HotPost from "@/components/community/HotPost";
import Image from "next/image";
import Link from "next/link";

export default function Community() {
  return (
    <div className="relative">
      <NavBar />
      <div className="flex flex-col items-center">
        <div className="my-50">
          <BoardSearchBar />
        </div>
        <div>
          <Image src="/advertisement.png" alt="광고" width={1200} height={0} />
        </div>
      </div>
      <QuickMenu />
      <div className="flex mx-[10vw] justify-between mt-50">
        {/* 인기 게시판 */}
        <div className="flex flex-col w-[45%]">
          <div className="flex justify-between mb-10">
            <div className="font-bold">인기 게시판</div>
            <Link href="/community/hotboard">
              <div className="flex items-center">
                <div className="text-[#AAAAAA] mr-10">더보기</div>
                <Image
                  src="/more_arrow.png"
                  alt="더보기 버튼"
                  width={1}
                  height={1}
                  className="w-10 h-13"
                />
              </div>
            </Link>
          </div>
          <div className="border-t-3 border-b-3 border-black">
            <HotPost />
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
          </div>
        </div>

        {/* 자유 게시판 */}
        <div className="flex flex-col w-[45%]">
          <div className="flex justify-between mb-10">
            <div className="font-bold">자유 게시판</div>
            <Link href="/community/freeboard">
              <div className="flex items-center">
                <div className="text-[#AAAAAA] mr-10">더보기</div>
                <Image
                  src="/more_arrow.png"
                  alt="더보기 버튼"
                  width={1}
                  height={1}
                  className="w-10 h-13"
                />
              </div>
            </Link>
          </div>
          <div className="border-t-3 border-b-3 border-black">
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
            <div>아ㅣ럼니;아럼ㄴㅇㄹ</div>
          </div>
        </div>
      </div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
    </div>
  );
}
