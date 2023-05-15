import {
  createReply,
  deleteComment,
  modifyComment,
} from "@/utils/commnuity/api";
import { SERVER_URL } from "@/utils/url";
import Image from "next/image";
import { useRouter } from "next/router";
import { parseCookies } from "nookies";
import { useEffect, useState } from "react";
import axios from "axios";

interface comment {
  commentGroup: number;
  commentId: number;
  content: string;
  nickname: string;
  parent: number;
}

export default function CommentComponent({
  commentInformation,
  // 아래 줄 테스트 추가
  replyComments,
  reloadComment,
}: {
  commentInformation: comment;
  // 아래 줄 테스트 추가
  replyComments: Array<comment>;
  reloadComment: Function;
}) {
  const router = useRouter();
  const [cookies, setCookies] = useState<any>({});
  const [isModify, setIsModify] = useState<boolean>(false);
  const [isReply, setIsReply] = useState<boolean>(false);
  const [modifyInputValue, setModifyInputValue] = useState<string>(
    commentInformation.content
  );
  const [replyInputValue, setReplyInputValue] = useState<string>("");
  // const [replyList, setReplyList] = useState<Array<comment>>([]);
  // 아래 줄 추가
  const [replyList, setReplyList] = useState<Array<comment>>([])
  console.log(replyList)

  useEffect(() => {
    setReplyList(replyComments)
  }, [replyComments])


  // 수정 버튼 누르면 실행되는 함수
  const modifyHandler = () => {
    // isModify 상태를 true로 바꾸면서 box보이게함
    setIsModify((prev) => !prev);
    setIsReply(false);
  };

  // 수정 폼 내의 InputValue 핸들러
  const modifyInputValueHandler = (
    e: React.ChangeEvent<HTMLTextAreaElement>
  ) => {
    setModifyInputValue(e.target.value);
  };

  const modifySubmit = async () => {
    // Back에 수정 요청 보내고
    await modifyComment(
      commentInformation.commentId,
      modifyInputValue,
      cookies.accessToken
    );
    // 상위 컴포넌트에서 댓글 목록 state변경 위해 상위 컴포넌트의 함수 호출
    reloadComment();
    // 수정 폼 닫기위해 false로 상태 변경
    setIsModify(false);
  };

  // 수정 창 닫기 버튼 누르면 실행되는 함수
  const cancelModifyHandler = () => {
    setIsModify(false);
  };

  // 삭제 버튼 누르면 실행되는 함수
  const deleteHandler = async () => {
    // Back에 삭제 요청 보내고
    await deleteComment(commentInformation.commentId, cookies.accessToken);
    // 상위 컴포넌트에서 댓글 목록 state변경 위해 상위 컴포넌트의 함수 호출
    reloadComment();
  };

  // 대댓글 버튼 누르면 실행되는 함수
  const replyHandler = () => {
    setIsReply((prev) => !prev);
    setIsModify(false);
  };

  // 대댓글 내용 Handler
  const replyInputValueHandler = (
    e: React.ChangeEvent<HTMLTextAreaElement>
  ) => {
    setReplyInputValue(e.target.value);
  };

  // 대댓글 닫는 핸들러
  const cancelReplyHandler = () => {
    setIsReply(false);
  };

  // 대댓글 등록 누를시 실행되는 함수
  const replySubmit = async () => {
    const communityId = router.query.detail as string;
    const res = await createReply(
      commentInformation.commentGroup,
      communityId,
      replyInputValue,
      cookies.accessToken
    );
    setReplyInputValue("");
    setIsReply((prev) => !prev);
    reloadComment()

    // 아래 줄 테스트 할때 주석처리함
    // getCommentList();
  };

  useEffect(() => {
    setCookies(parseCookies());
  }, []);

  return (
    <div className="flex flex-col border-t-1 border-b-1 border-gray-200 py-20 px-10">
      <div className="flex justify-between">
        <div className="font-bold">{commentInformation.nickname}</div>
        <div className="flex">
          {/* Comment Modify and Delete button */}
          {cookies.nickName &&
            cookies.nickName === commentInformation.nickname ? (
            <div className="flex">
              {/* Modify Button */}
              <div className="flex mr-20" onClick={modifyHandler}>
                <div className="w-20 h-20 cursor-pointer">
                  <Image src="/modify.png" alt="수정" width={96} height={96} />
                </div>
                <button className="text-gray-400">수정</button>
              </div>

              {/* Delete Button */}
              <div className="flex mr-20" onClick={deleteHandler}>
                <div className="w-20 h-20 cursor-pointer">
                  <Image src="/delete.png" alt="삭제" width={96} height={96} />
                </div>
                <button className="text-gray-400">삭제</button>
              </div>
            </div>
          ) : null}
          {/* Reply Button */}
          <div className="flex" onClick={replyHandler}>
            <div className="w-20 h-20 cursor-pointer">
              <Image src="/reply.png" alt="대댓글" width={96} height={96} />
            </div>
            <button className="text-gray-400">댓글</button>
          </div>
        </div>
      </div>

      <div>
        <div
          dangerouslySetInnerHTML={{
            __html: commentInformation.content.replace(/\n/g, "<br>"),
          }}
        />
      </div>

      {/* 대댓글 List */}
      {replyList
        ? replyList.map((reply) => (
          <div
            className="flex items-center my-10"
            key={"대댓글" + `${reply.commentId}`}
          >
            <div className="mr-10">
              <Image
                src="/reply_arrow.png"
                alt="대댓글 화살표"
                width={15}
                height={17}
              />
            </div>
            <div className="flex flex-col">
              <div className="font-bold">{reply.nickname}</div>
              <div>{reply.content}</div>
            </div>
          </div>
        ))
        : null}

      {/* 댓글 수정 폼 */}
      {
        // isModify는 사용자가 수정 버튼을 누르면 true가 된다.
        isModify ? (
          <div className="flex flex-col h-180 border-1 border-gray-400 rounded-10">
            <div className="flex justify-between items-center p-20">
              <div>댓글 수정</div>
              <div
                className="flex items-center cursor-pointer"
                onClick={cancelModifyHandler}
              >
                <div className="w-20 h-20">
                  <Image
                    src="/comment_xmark.png"
                    alt="수정 삭제 버튼"
                    width={96}
                    height={96}
                  />
                </div>
                <div className="text-gray-400">닫기</div>
              </div>
            </div>

            <div className="flex pb-20 px-20">
              <textarea
                className="w-[1060px] border-2 border-gray-200 rounded-10 resize-none focus:outline-brand"
                defaultValue={commentInformation.content}
                spellCheck="false"
                onChange={modifyInputValueHandler}
              />
              <div className="flex justify-center" onClick={modifySubmit}>
                <div className="flex items-center justify-center w-90 h-80 ml-20 text-center bg-brand rounded-10 text-white cursor-pointer">
                  수정
                </div>
              </div>
            </div>
          </div>
        ) : null
      }

      {/* Reply 폼 */}
      {isReply ? (
        <div className="flex flex-col h-180 border-1 border-gray-400 rounded-10">
          <div className="flex justify-between items-center p-20">
            <div>대댓글 작성</div>
            <div
              className="flex items-center cursor-pointer"
              onClick={cancelReplyHandler}
            >
              <div className="w-20 h-20">
                <Image
                  src="/comment_xmark.png"
                  alt="삭제 버튼"
                  width={96}
                  height={96}
                />
              </div>
              <div className="text-gray-400">닫기</div>
            </div>
          </div>

          <div className="flex pb-20 px-20">
            <textarea
              className="w-[1060px] border-2 border-gray-200 rounded-10 resize-none focus:outline-brand"
              defaultValue=""
              spellCheck="false"
              onChange={replyInputValueHandler}
            />
            <div className="flex justify-center" onClick={replySubmit}>
              <div className="flex items-center justify-center w-90 h-80 ml-20 text-center bg-brand rounded-10 text-white cursor-pointer">
                등록
              </div>
            </div>
          </div>
        </div>
      ) : null}
    </div>
  );
}