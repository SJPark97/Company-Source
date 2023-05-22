import NavBar from "@/components/NavBar";
import { SERVER_URL } from "@/utils/url";
import { GetServerSidePropsContext } from "next";
import axios from "axios";
import SearchResultHeader from "@/components/community/search/SearchResultHeader";
import PostThumbnail from "@/components/PostThumbnail";
import QuickMenu from "@/components/QuickMenu";
import Link from "next/link";
import { useEffect, useState } from "react";
import PageBox from "@/components/community/search/PageBox";
import { useRouter } from "next/router";
import Head from "next/head";

interface Post {
  commentCount: number;
  communityId: number;
  communityType: string;
  date: "string";
  likesCount: number;
  time: "string";
  title: "string";
  userName: "string";
  viewCount: "string";
}

export default function CommunitySearchResult({
  data,
  keyword,
}: {
  data: any;
  keyword: string;
}) {
  const router = useRouter();
  const [pages, setPages] = useState<Array<number>>([]);
  useEffect(() => {
    const tempArr = [];
    if (data.data.pageTotal <= 10) {
      for (var i = 1; i <= 10; i++) {
        if (i > data.data.pageTotal) {
          break;
        }
        tempArr.push(i);
      }
      setPages(tempArr);
    } else {
      const minPage = data.data.pageTotal - (data.data.pageTotal % 10);
      for (var i = minPage; i < minPage + 10; i++) {
        if (i > data.data.pageTotal) {
          break;
        }
        tempArr.push(i);
      }
      setPages(tempArr);
    }
  }, [router]);
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
      <NavBar />
      <QuickMenu />

      {/* body */}
      <div className="w-[1200px] mx-auto">
        <div className="border-b-2 text-24 py-50 border-brand">
          '<span className="font-bold">{keyword}</span>
          '으로 검색한 결과입니다.
        </div>
        <SearchResultHeader />

        {/* 게시물 목록 */}
        <div className="border-gray-400 border-b-1">
          {data.data.readAllCommunityResponses &&
            data.data.readAllCommunityResponses.map((post: Post) => (
              // Link설정 필요
              <Link
                href={`/community/${post.communityType}/detail/${post.communityId}`}
              >
                <PostThumbnail post={post} keyword={keyword} />
              </Link>
            ))}
        </div>
        <PageBox pages={pages} pageTotal={data.data.pageTotal} />
      </div>
    </>
  );
}

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const { data } = await axios.get(SERVER_URL + "/community/all/search", {
    params: {
      content: context.query.searchWord as string,
      type: context.query.searchType as string,
      page: parseInt(context.query.page as string) - 1,
      size: 10,
    },
  });
  return {
    props: {
      data,
      keyword: context.query.searchWord,
    },
  };
}
