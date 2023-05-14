import { deleteComment, modifyComment } from "@/utils/commnuity/api";
import Image from "next/image";
import { parseCookies } from "nookies";
import { useEffect, useState } from "react";

interface Comment {
    commentGroup: number,
    commentId: number,
    content: string,
    nickname: string,
    parent: number,
}

export default function CommentComponent({ commentInformation, reloadComment }: { commentInformation: Comment, reloadComment: Function }) {

    const [cookies, setCookies] = useState<any>({})
    const [isModify, setIsModify] = useState<boolean>(false)
    const [modifyInputValue, setModifyInputValue] = useState(commentInformation.content)

    useEffect(() => {
        setCookies(parseCookies())
    }, [])

    // 수정 버튼 누르면 실행되는 함수
    const modifyHandler = () => {
        // isModify 상태를 true로 바꾸면서 box보이게함
        setIsModify(true)
    }

    // 수정 폼 내의 InputValue 핸들러
    const modifyInputValueHandler = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        console.log(e.target.value)
        setModifyInputValue(e.target.value)
    }

    const modifySubmit = async () => {
        // Back에 수정 요청 보내고
        await modifyComment(commentInformation.commentId, modifyInputValue, cookies.accessToken)
        // 상위 컴포넌트에서 댓글 목록 state변경 위해 상위 컴포넌트의 함수 호출
        reloadComment()
        // 수정 폼 닫기위해 false로 상태 변경
        setIsModify(false)
    }

    // 수정 창 닫기 버튼 누르면 실행되는 함수
    const cancelModifyHandler = () => {
        setIsModify(false)
    }


    // 삭제 버튼 누르면 실행되는 함수
    const deleteHandler = async () => {
        // Back에 삭제 요청 보내고
        await deleteComment(commentInformation.commentId, cookies.accessToken)
        // 상위 컴포넌트에서 댓글 목록 state변경 위해 상위 컴포넌트의 함수 호출
        reloadComment()
    }

    return (
        <div className="flex flex-col border-t-1 border-b-1 border-gray-200 py-20 px-10">
            <div className="flex justify-between pb-10">
                <div className="font-bold">
                    {commentInformation.nickname}
                </div>

                {/* Comment Modify and Delete button */}
                {cookies.nickName && cookies.nickName === commentInformation.nickname ?
                    (<div className="flex">

                        {/* Modify Button */}
                        <div className="flex mr-20" onClick={modifyHandler}>
                            <div className="w-20 h-20 cursor-pointer">
                                <Image
                                    src="/comment_modify.png"
                                    alt="댓글 수정"
                                    width={96}
                                    height={96}
                                />
                            </div>
                            <button className="text-gray-400">
                                수정
                            </button>
                        </div>

                        {/* Delete Button */}
                        <div className="flex" onClick={deleteHandler}>
                            <div className="w-20 h-20 cursor-pointer">

                                <Image src="/comment_delete.png"
                                    alt="댓글 삭제"
                                    width={96}
                                    height={96}
                                />
                            </div>
                            <button className="text-gray-400">
                                삭제
                            </button>
                        </div>
                    </div>) : null}
            </div>
            <div>{commentInformation.content}</div>

            {/* 댓글 수정 폼 */}
            {
                // isModify는 사용자가 수정 버튼을 누르면 true가 된다.
                isModify ? (
                    <div className="flex flex-col h-180 border-1 border-gray-400 rounded-10">
                        <div className="flex justify-between items-center p-20">
                            <div>댓글 수정</div>
                            <div className="flex items-center cursor-pointer" onClick={cancelModifyHandler}>
                                <div className="w-20 h-20">
                                    <Image src="/comment_modify_xmark.png" alt="수정 삭제 버튼"
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
                )
                    :
                    null}

        </div>
    )
}