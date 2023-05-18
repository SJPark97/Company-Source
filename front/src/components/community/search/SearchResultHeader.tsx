export default function SearchResultHeader() {
  return (
    <div className="flex font-bold text-center py-10 border-b-1 border-gray-300">
      <div className="w-70">번호</div>
      <div className="w-[550px]">제목</div>
      <div className="w-[200px]">닉네임</div>
      <div className="w-100">추천</div>
      <div className="w-100">조회</div>
      <div className="w-[180px]">작성일</div>
    </div>
  );
}
