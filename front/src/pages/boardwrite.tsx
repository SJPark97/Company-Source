import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import { Editor } from "@tinymce/tinymce-react";
import { useState } from "react";
import { useRouter } from "next/router";
import {
  createCorpAxios,
  createFreeAxios,
  modifyCorpAxios,
  modifyFreeAxios,
} from "@/utils/commnuity/api";
import { parseCookies } from "nookies";

export default function BoardWrite() {
  const router = useRouter();
  const [content, setContent] = useState<string | string[] | undefined>(
    router.query.content
  );
  const [title, setTitle] = useState<string>("");
  const cookies = parseCookies();

  const handleTitle = (e: any) => {
    setTitle(e.target.value);
  };

  const contentHandler = (content: string) => {
    setContent(content);
  };

  const createHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!title) {
      alert("제목을 입력해 주세요.");
      return;
    }
    if (!content) {
      alert("내용을 입력해 주세요.");
      return;
    }
    if (router.query.board === "기업") {
      const res = await createCorpAxios(
        content as string,
        title,
        cookies.accessToken
      );
      const communityId = parseInt(res?.data.data);
      if (res && res.status === 201) {
        router.replace(
          `/community/corpboard/detail/${communityId}`,
          `/community/corpboard/detail/${communityId}`,
          { shallow: true }
        );
      }
    } else if (router.query.board === "자유") {
      const res = await createFreeAxios(
        content as string,
        title,
        cookies.accessToken
      );
      const communityId = parseInt(res?.data.data);
      if (res && res.status === 201) {
        router.replace(
          `/community/freeboard/detail/${communityId}`,
          `/community/freeboard/detail/${communityId}`,
          { shallow: true }
        );
      }
    }
  };

  const modifyHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (router.query.board === "기업") {
      const res = await modifyCorpAxios(
        content as string,
        router.query.communityId as string,
        router.query.title as string,
        cookies.accessToken
      );
      router.replace(
        `/community/corpboard/detail/${router.query.communityId}`,
        `/community/corpboard/detail/${router.query.communityId}`,
        { shallow: true }
      );
    } else if (router.query.board === "자유") {
      const res = await modifyFreeAxios(
        content as string,
        router.query.communityId as string,
        router.query.title as string,
        cookies.accesstoken
      );
      router.replace(
        `/community/freeboard/detail/${router.query.communityId}`,
        `/community/freeboard/detail/${router.query.communityId}`,
        { shallow: true }
      );
    }
  };

  return (
    <>
      <NavBar />
      <div className="z-50">
        <QuickMenu />
      </div>
      {/* 쿼리 없을때 : 글쓰기나 글 수정 버튼을 눌러서 들어온게 아닌경우 */}
      {!router.query.type ? (
        <div>올바른 접근이 아닙니다.</div>
      ) : (
        <div className="flex flex-col w-[1200px] mx-auto">
          <div className="text-20 font-bold my-50">게시물 작성</div>
          <form
            onSubmit={
              router.query.type === "write" ? createHandler : modifyHandler
            }
          >
            <div className="flex w-[1200px]">
              <input
                placeholder="제목을 입력해 주세요."
                className="w-[1200px] mb-50 border-gray-200 border-2 rounded-5 px-5 py-3"
                onChange={handleTitle}
                defaultValue={router.query.title}
              />
            </div>
            <Editor
              apiKey="03a6amcyjwfpazpfdyc8hwnattyuvy9iz8hrib5lzq0jf4wk"
              initialValue={
                router.query.type === "modify"
                  ? Array.isArray(router.query.content)
                    ? router.query.content[0]
                    : router.query.content
                  : ""
              }
              init={{
                height: 500,
                menubar: true,
                plugins: [
                  "advlist autolink lists link image",
                  "charmap print preview anchor help",
                  "searchreplace visualblocks code",
                  "insertdatetime media paste wordcount",
                ],
                toolbar:
                  "undo redo | formatselect | bold italic | \
            alignleft aligncenter alignright | \
            bullist numlist outdent indent image",
                placeholder: "내용을 입력해 주세요.",
                resize: false,
              }}
              onEditorChange={contentHandler}
            />
            <div className="relative">
              <button className="absolute text-white bg-brand text-center w-70 h-40 rounded-10 px-10 ml-[1130px] top-30">
                글쓰기
              </button>
            </div>
          </form>
          <div className="relative"></div>
        </div>
      )}
    </>
  );
}
