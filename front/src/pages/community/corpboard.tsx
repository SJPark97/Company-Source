import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";

export default function CorpBoard() {
  return (
    <div className="relative">
      <NavBar />
      <QuickMenu />
      <div className="mx-[10vw]">
        <div>여긴 인기 게시판</div>
        <div className="my-[500px]">스크롤 생성용 게시물</div>
        <div className="my-[500px]">스크롤 생성용 게시물</div>
        <div className="my-[500px]">스크롤 생성용 게시물</div>
        <div className="my-[500px]">스크롤 생성용 게시물</div>
      </div>
    </div>
  );
}
