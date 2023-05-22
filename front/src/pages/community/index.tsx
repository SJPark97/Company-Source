import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import BoardSearchBar from "@/components/community/BoardSearchBar";
import CommunityLandingPost from "@/components/community/CommunityLandingPost";
import Image from "next/image";
import Link from "next/link";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import Head from "next/head";

interface post {
  commentCount: number;
  communityId: number;
  date: string;
  likesCount: number;
  time: string;
  title: string;
  userName: string;
  viewCount: number;
}

export default function Community({ data }: { data: any }) {
  return (
    <>
      <Head>
        <title>컴퍼니소스 | 커뮤니티</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스(Company Source)의 커뮤니티 페이지 입니다."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/community" />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
        <meta
          name="google-site-verification"
          content="0FzOO996BLTIEWFgwlmmYv-F1WmHiM6SrbwEbK9-p3k"
        />
      </Head>
      <div className="relative">
        <NavBar />
        <QuickMenu />

        <div className="flex flex-col items-center">
          <div className="mt-50">
            <BoardSearchBar />
          </div>
          <div className="mt-50">
            <Image src="/advertisement.png" alt="광고" width={1200} height={0} />
          </div>
        </div>

        <div className="flex w-[1300px] mx-auto justify-between mt-50 mb-50">
          <div className="flex flex-col w-[600px]">
            <div className="flex justify-between mb-10">
              <Link href="/community/corpboard">
                <div className="font-bold">기업 게시판</div>
              </Link>
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
            <div className="border-black border-t-3 border-b-3">
              {data.data.corpHot.map((corpPost: post) => (
                <Link
                  href={
                    "/community/corpboard/detail/" + `${corpPost.communityId}`
                  }
                  key={"landing corp" + `${corpPost.communityId}`}
                >
                  <CommunityLandingPost postInformation={corpPost} />
                </Link>
              ))}
            </div>
          </div>

          <div className="flex flex-col w-[600px]">
            <div className="flex justify-between mb-10">
              <Link href="/community/freeboard">
                <div className="font-bold">자유 게시판</div>
              </Link>
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

            <div className="border-black border-t-3 border-b-3">
              {data.data.freeHot.map((freePost: post) => (
                <Link
                  href={
                    "/community/freeboard/detail/" + `${freePost.communityId}`
                  }
                  key={"landing free" + `${freePost.communityId}`}
                >
                  <CommunityLandingPost postInformation={freePost} />
                </Link>
              ))}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export async function getServerSideProps(context: any) {
  const { data } = await axios.get(SERVER_URL + "/community/randing", {
    params: {
      standard: 1,
      pageCnt: 8,
    },
  });

  return {
    props: {
      data,
    },
  };
}
