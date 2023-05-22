import { useState, useEffect } from "react";
import { SERVER_URL } from "@/utils/url";
import { useRouter } from "next/router";
import axios from "axios";
import NavBar from "@/components/NavBar";
import Image from "next/image";
import Link from "next/link";
import { parseCookies } from "nookies";
import {
  cancelLikeDetailAxios,
  createComment,
  getCorpBoardDetail,
  likeDetailAxios,
} from "@/utils/commnuity/api";
import CommentComponent from "@/components/community/CommentComponent";
import QuickMenu from "@/components/QuickMenu";
import DetailModifyButton from "@/components/community/DetailModifyButton";
import DetailDeleteButton from "@/components/community/DetailDeleteButton";
import Head from "next/head";

interface comment {
  commentGroup: number;
  commentId: number;
  content: string;
  nickname: string;
  parent: number;
}

export default function corpBoardDetail({
  data,
  accessToken,
}: {
  data: any;
  accessToken: string;
}) {
  const [boardName, setBoardName] = useState<string>("");
  const router = useRouter();
  const currentLocation = router.pathname.split("/")[2];
  const [isLiked, setIsLiked] = useState<boolean>(data.data.liked);
  const [commentList, setCommentList] = useState<Array<comment>>(
    data.data.comments
  );
  const [likesCount, setLikesCount] = useState(data.data.likesCount);
  const cookies = parseCookies();
  // 나의 글인지 여부
  const [isYourPost, setIsYourPost] = useState<boolean>(false);

  // 댓글 내용
  const [content, setContent] = useState<string>("");

  const likeHandler = async () => {
    // 로그인 여부 체크
    if (accessToken) {
      if (!isLiked) {
        await likeDetailAxios(accessToken, router.query.detail as string);
        setIsLiked(true);
        // 좋아요 갱신 위해 페이지 데이터 요청
        const res = await axios.get(
          SERVER_URL + `/community/corp/${router.query.detail}`
        );
        setLikesCount(res.data.data.likesCount);
      } else {
        await cancelLikeDetailAxios(accessToken, router.query.detail as string);
        setIsLiked(false);
        // 좋아요 갱신 위해 페이지 데이터 요청
        const res = await axios.get(
          SERVER_URL + `/community/corp/${router.query.detail}`
        );
        setLikesCount(res.data.data.likesCount);
      }
    } else {
      if (confirm("로그인 하시겠습니까?")) {
        router.push("/login");
      }
    }
  };

  // 로그인 안하고 댓글 작성창 누르면 실행되는 함수
  const goToLogin = () => {
    if (confirm("로그인 하시겠습니까?")) {
      router.push("/login");
    }
  };
  // 댓글 내용 Handler
  const commentHandler = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  // 부모 댓글 작성되는 함수
  const createMyComment = async (e: any) => {
    e.preventDefault();
    if (!content) {
      alert("내용을 입력해 주세요.");
      return;
    }
    await createComment(accessToken, router.query.detail as string, content);
    setContent("");
    const res = await getCorpBoardDetail(router.query.detail as string);
    if (res) {
      setCommentList(res.data.data.comments);
    }
  };

  // 하위 댓글 컴포넌트에서 삭제 및 수정 로직 실행되면 리스트 업데이트 하기 위한 함수
  const reloadComment = async () => {
    const res = await getCorpBoardDetail(router.query.detail as string);
    if (res) {
      setCommentList(res.data.data.comments);
    }
  };

  // 게시판 이름 계산
  useEffect(() => {
    if (router.pathname && currentLocation === "corpboard") {
      setBoardName("기업");
    } else if (router.pathname && currentLocation === "freeboard") {
      setBoardName("자유");
    }
  }, [router]);

  // 나의 글인지 확인
  useEffect(() => {
    // 조회수 올리기
    axios.post(SERVER_URL + `/community/add/view/` + `${router.query.detail}`);

    if (cookies.nickName === data.data.userName) {
      setIsYourPost(true);
    }
  }, []);

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
      <NavBar />
      <QuickMenu />
      {!data.data ? (
        <div className="text-center font-bold text-40 my-[300px]">
          없는 페이지 입니다.
        </div>
      ) : (
        <div className="flex flex-col w-[1200px] mx-auto">
          <Link href={"/community/" + `${currentLocation}`}>
            <div className="my-10 font-bold text-24 mt-50">
              {boardName} 게시판
            </div>
          </Link>
          <div className="flex items-center h-2 bg-brand rounded-20"></div>

          {/* 제목, 작성자, 작성시간 */}
          <div className="flex flex-col border-b-2 border-gray-400 py-15">
            <div className="mb-5 font-bold text-20">{data.data.title}</div>
            <div className="flex justify-between">
              <div className="flex">
                <div>{data.data.userName} | </div>
                <div className="ml-10">
                  {data.data.date} {data.data.time}
                </div>
              </div>

              {/* 게시물 좋아요수 */}
              <div className="flex">
                <div className="flex items-center ml-50">
                  <div className="w-20 h-auto">
                    <Image
                      src="/like_after.png"
                      alt="추천"
                      width={80}
                      height={72}
                    />
                  </div>
                  <div className="ml-5">{likesCount}</div>
                </div>
                {/* 게시물 조회수 */}
                <div className="flex items-center ml-50">
                  <div className="h-auto pt-3 w-25">
                    <Image
                      src="/view.png"
                      alt="조회수"
                      width={88}
                      height={60}
                    />
                  </div>
                  <div className="ml-5">{data.data.viewCount}</div>
                </div>
              </div>
            </div>
          </div>

          {/* 게시물 삭제 및 수정 버튼 */}
          {isYourPost ? (
            <div className="relative pt-20">
              <div className="absolute right-0 flex top-20">
                {/* 수정 버튼 */}
                <div className="mr-20">
                  <DetailModifyButton
                    title={data.data.title}
                    content={data.data.content}
                    communityId={data.data.communityId}
                    board={boardName}
                  />
                </div>
                <DetailDeleteButton communityId={data.data.communityId} />
              </div>
            </div>
          ) : null}

          <div className="py-50">
            <div
              dangerouslySetInnerHTML={{
                __html: data.data.content.replace(/\n/g, "<br>"),
              }}
            />
          </div>

          {/* Like Box */}
          <div className="flex justify-center border-b-2 border-gray-400 py-50">
            <div
              className="flex items-center p-10 border-2 shadow-2xl cursor-pointer border-brand rounded-10"
              onClick={likeHandler}
            >
              <Image
                src={!isLiked ? "/like_before.png" : "/like_after.png"}
                alt="좋아요"
                width={80}
                height={72}
                className="h-24 mr-10 w-28"
              />
              <div className="font-bold">좋아요</div>
            </div>
          </div>

          {/* Comment List */}
          <div className="mb-10">
            {commentList
              .filter((comment) => comment.parent === 1)
              .map((comment: comment) => (
                <div key={`${comment.commentId}` + "기업 댓글"}>
                  <CommentComponent
                    commentInformation={comment}
                    replyComments={commentList.filter(
                      (reply) =>
                        reply.parent === 0 &&
                        reply.commentGroup === comment.commentGroup
                    )}
                    reloadComment={reloadComment}
                  />
                </div>
              ))}
          </div>

          {/* Comment Create Box */}
          <div className="border-2 border-gray-400 h-180 rounded-10">
            <div className="p-20">댓글 쓰기</div>
            <form>
              <div className="flex px-20">
                {/* 토큰 있을때와 없을때 폼 분기처리 */}
                {accessToken ? (
                  <textarea
                    className="w-[1060px] border-2 border-gray-200 rounded-10 h-80 resize-none p-10 focus:outline-brand"
                    placeholder="댓글을 작성해 주세요."
                    spellCheck="false"
                    onChange={commentHandler}
                    value={content}
                    maxLength={255}
                  />
                ) : (
                  <div
                    className="w-[1060px] border-2 border-gray-200 rounded-10 h-80 bg-gray-100 p-10 text-gray-300 cursor-pointer"
                    onClick={goToLogin}
                  >
                    댓글을 작성하려면 로그인이 필요합니다.
                  </div>
                )}
                <div className="flex justify-center">
                  <div
                    className="flex items-center justify-center ml-20 text-center text-white cursor-pointer w-90 h-80 bg-brand rounded-10"
                    onClick={createMyComment}
                  >
                    등록
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export async function getServerSideProps(context: any) {
  const detailId = context.query.detail;
  const cookies = parseCookies(context);
  const accessToken = cookies.accessToken ?? null;

  const { data } = await axios.get(SERVER_URL + `/community/corp/${detailId}`, {
    headers: {
      Authorization: accessToken,
    },
  });

  return {
    props: {
      data,
      accessToken,
    },
  };
}
