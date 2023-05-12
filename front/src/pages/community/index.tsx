import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import BoardSearchBar from "@/components/community/BoardSearchBar";
import CommunityFreePost from "@/components/community/CommunityFreePost";
import CommunityCorpPost from "@/components/community/CommunityCorpPost";
import Image from "next/image";
import Link from "next/link";
import { SERVER_URL } from "@/utils/url";

export default function Community() {
  return (
    // 준비중 페이지
    // <div className="flex flex-col items-center">
    //   <NavBar />
    //   <Image
    //     src="/service_waiting.jpg"
    //     alt="서비스 준비 페이지"
    //     width={700}
    //     height={1}
    //   />
    //   <div className="text-40 font-bold">서비스 준비중입니다.</div>
    // </div>

    // 개발중 페이지
    <div className="relative">
      <NavBar />
      <QuickMenu />

      <div className="flex flex-col items-center">
        <div className="my-50">
          <BoardSearchBar />
        </div>
        <div>
          <Image src="/advertisement.png" alt="광고" width={1200} height={0} />
        </div>
      </div>

      <div className="flex mx-[180px] justify-between mt-50 mb-50">
        <div className="flex flex-col w-[45%]">
          <div className="flex justify-between mb-10">
            <div className="font-bold">기업 게시판</div>
            <Link href="/community/corpboard">
              <div className="flex items-center">
                <div className="text-[#AAAAAA] mr-10">더보기</div>
                <Image
                  src="/more_arrow.png"
                  alt="더보기 버튼"
                  width={95}
                  height={119}
                  className="w-10 h-13"
                />
              </div>
            </Link>
          </div>
          <div className="border-t-3 border-b-3 border-black">
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
            <CommunityCorpPost />
          </div>
        </div>

        <div className="flex flex-col w-[45%]">
          <div className="flex justify-between mb-10">
            <div className="font-bold">자유 게시판</div>
            <Link href="/community/freeboard">
              <div className="flex items-center">
                <div className="text-[#AAAAAA] mr-10">더보기</div>
                <Image
                  src="/more_arrow.png"
                  alt="더보기 버튼"
                  width={95}
                  height={119}
                  className="w-10 h-13"
                />
              </div>
            </Link>
          </div>

          <div className="border-t-3 border-b-3 border-black">
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
            <CommunityFreePost />
          </div>
        </div>
      </div>
    </div>
  );
}

// export async function getServerSideProps(context: any) {
//   const { data } = await axios.get(SERVER_URL + '/community/corp', {
//     params: {
//       page: 0,
//       size: 10,
//       sort: "all",
//     }
//   })
// }
