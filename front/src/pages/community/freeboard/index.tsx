import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import BoardNavBar from "@/components/community/BoardNavBar";
import { useRouter } from "next/router";

export default function FreeBoard() {
  return (
    <div className="relative">
      <NavBar />
      <QuickMenu />
      <BoardNavBar />
      <div>여긴 자유 게시판</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
      <div className="my-[500px]">스크롤 생성용 게시물</div>
    </div>
  );
}
