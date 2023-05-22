import axios from "axios";
import { Context, useState, useEffect } from "react";
import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import BoardNavBar from "@/components/community/BoardNavBar";
import { SERVER_URL } from "@/utils/url";
import Image from "next/image";
import { useRouter } from "next/router";
import Link from "next/link";
import WriteButton from "@/components/community/WriteButton";
import BoardSearchBar from "@/components/community/BoardSearchBar";
import Head from "next/head";

export default function CorpBoardRecommend({ data }: { data: any }) {
  const router = useRouter();
  const [page, setPage] = useState<Array<number>>([]);
  const [prevPage, setPrevPage] = useState<string>("");
  const [nextPage, setNextPage] = useState<string>("");

  const writeButtonProps = router.pathname.split("/")[2];

  useEffect(() => {
    if (typeof router.query.recommend === "string") {
      const temp = parseInt(router.query.recommend);
      if (temp === 1) {
        setPrevPage((1).toString());
      } else {
        setPrevPage((temp - 1).toString());
      }

      if (temp === data.totalPage) {
        setNextPage(data.totalPage.toString());
      } else {
        setNextPage((temp + 1).toString());
      }
      if (temp <= 9) {
        const tempArr = [];
        for (var i = 1; i < 10; i++) {
          if (i > data.totalPage) {
            break;
          }
          tempArr.push(i);
        }
        setPage(tempArr);
      } else {
        const minPage = temp - (temp % 10);
        const tempArr: any = [];
        for (var i = minPage; i < minPage + 10; i++) {
          if (i > data.totalPage) {
            break;
          }
          tempArr.push(i);
        }
        setPage(tempArr);
      }
    }
  }, [router]);

  return (
    <>
      <Head>
        <title>컴퍼니소스 | 기업 게시판</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스(Company Source)의 기업 게시판 페이지 입니다."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/community/corpboard" />
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
        <div className="flex justify-center relaitve">
          <div className="absolute top-100">
            <BoardSearchBar />
          </div>
        </div>
        <BoardNavBar />
        <div className="flex flex-col whitespace-nowrap w-[1200px] mx-auto">
          <div className="flex py-10 font-bold border-gray-300 border-b-1">
            <div className="text-center w-70">번호</div>
            <div className="text-center w-[550px]">제목</div>
            <div className="text-center w-[200px]">닉네임</div>
            <div className="text-center w-100">추천</div>
            <div className="text-center w-100">조회</div>
            <div className="text-center w-[180px]">작성일</div>
          </div>
        </div>
        <div className="relative flex w-[1200px] mx-auto border-b-1 border-gray-300">
          <div className="flex flex-col">
            {data.data &&
              data.data.map((post: any) => (
                <Link
                  href={"/community/corpboard/detail/" + `${post.communityId}`}
                >
                  <div className="flex py-10">
                    <div className="text-center w-70">{post.communityId}</div>
                    <div className="flex w-[550px]">
                      <div className="mr-10 line-clamp-1">
                        {`${post.title}` + "  "}
                      </div>
                      <div className="font-bold text-brand">
                        [{post.commentCount}]
                      </div>
                    </div>
                    <div className="text-center w-[200px]">{post.userName}</div>
                    <div className="text-center w-100">{post.likesCount}</div>
                    <div className="text-center w-100">{post.viewCount}</div>
                    <div className="text-center w-[180px]">{post.date}</div>
                  </div>
                </Link>
              ))}
          </div>
        </div>
        <div className="relative flex items-center justify-center my-30">
          <Link href={"/community/corpboard/recommend/" + `${prevPage}`}>
            <Image
              src="/prev_page.png"
              alt="이전페이지"
              width={52}
              height={64}
              className="h-32 mx-20 w-26"
            />
          </Link>
          {page.map((page) => (
            <Link
              href={"/community/corpboard/recommend/" + `${page}`}
              key={"corpRecommend" + `${page}`}
            >
              <div
                className={
                  "mx-20 " +
                  `${router.query.recommend &&
                    typeof router.query.recommend === "string" &&
                    parseInt(router.query.recommend) === page
                    ? "font-bold text-24"
                    : null
                  }`
                }
              >
                {page}
              </div>
            </Link>
          ))}
          <Link href={"/community/corpboard/recommend/" + `${nextPage}`}>
            <Image
              src="/next_page.png"
              alt="이전페이지"
              width={52}
              height={64}
              className="h-32 mx-20 w-26"
            />
          </Link>
          <div className="absolute text-center ml-[1130px]">
            <WriteButton path={writeButtonProps} />
          </div>
        </div>
      </div>
    </>
  );
}

export async function getServerSideProps(context: any) {
  const { data } = await axios.get(SERVER_URL + `/community/corp`, {
    params: {
      page: context.query.recommend - 1,
      size: 10,
      sort: "likes",
    },
  });
  return {
    props: {
      data,
    },
  };
}
