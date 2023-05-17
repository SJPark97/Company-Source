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

export default function FreeBoardAll({ data }: { data: any }) {
  const router = useRouter();
  const [page, setPage] = useState<Array<number>>([]);

  const writeButtonProps = router.pathname.split("/")[2];

  useEffect(() => {
    if (!router.query.freeboard) {
    }
    const tempArr = [];
    for (var i = 1; i < 10; i++) {
      if (i > data.totalPage) {
        break;
      }
      tempArr.push(i);
    }
    setPage(tempArr);
  }, [router]);

  return (
    <>
      <NavBar />
      <QuickMenu />
      <div className="relaitve flex justify-center">
        <div className="absolute top-100">
          <BoardSearchBar />
        </div>
      </div>
      <BoardNavBar />
      <div className="flex flex-col whitespace-nowrap w-[1200px] mx-auto">
        <div className="flex font-bold py-10 border-b-1 border-gray-300">
          <div className="text-center w-70">번호</div>
          <div className="text-center w-[550px]">제목</div>
          <div className="text-center w-[200px]">닉네임</div>
          <div className="text-center w-100">추천</div>
          <div className="text-center w-100">조회</div>
          <div className="text-center w-[180px]">작성일</div>
        </div>
      </div>
      <div className="flex w-[1200px] mx-auto border-b-1 border-gray-300">
        <div className="flex flex-col">
          {data.data &&
            data.data.map((post: any) => (
              <Link
                href={"/community/freeboard/detail/" + `${post.communityId}`}
                key={"free" + `${post.communityId}`}
              >
                <div className="flex py-10">
                  <div className="text-center w-70">{post.communityId}</div>
                  <div className="flex w-[550px]">
                    <div className="line-clamp-1 mr-10">
                      {`${post.title}` + "  "}
                    </div>
                    <div className="text-brand font-bold">
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
      <div className="relative flex justify-center items-center my-30">
        <Link href="/community/freeboard">
          <Image
            src="/prev_page.png"
            alt="이전페이지"
            width={52}
            height={64}
            className="w-26 h-32 mx-20"
          />
        </Link>
        {page.map((item) => (
          <Link
            href={"/community/freeboard/" + `${item}`}
            key={"firstFreeAll" + `${item}`}
          >
            <div
              className={
                "mx-20 " + `${item === 1 ? "font-bold text-24" : null}`
              }
            >
              {item}
            </div>
          </Link>
        ))}
        <Link href="/community/freeboard/2">
          <Image
            src="/next_page.png"
            alt="이전페이지"
            width={52}
            height={64}
            className="w-26 h-32 mx-20"
          />
        </Link>
        <div className="absolute text-center ml-[1130px]">
          <WriteButton path={writeButtonProps} />
        </div>
      </div>
    </>
  );
}

export async function getServerSideProps(context: any) {
  const { data } = await axios.get(SERVER_URL + `/community/free`, {
    params: {
      page: 0,
      size: 10,
      sort: "all",
    },
  });
  return {
    props: {
      data,
    },
  };
}
