import NavBar from "@/components/NavBar";
import QuickMenu from "@/components/QuickMenu";
import { Editor } from "@tinymce/tinymce-react";
import { useState } from "react";
import { useRouter } from "next/router";
import { createCorpAxios, createFreeAxios } from "@/utils/commnuity/api";
import { parseCookies } from "nookies";

export default function BoardWrite() {
  const [content, setContent] = useState<string>("");
  const [title, setTitle] = useState<string>("");
  const [temp, setTemp] = useState<string>("");
  const [boardType, setBoardType] = useState<string>("자유 게시판");
  const cookies = parseCookies();
  const router = useRouter();

  const boardTypeHandler = (e: React.ChangeEvent<HTMLSelectElement>) => {
    console.log(e.target.value);
    setBoardType(e.target.value);
  };

  const handleTitle = (e: any) => {
    console.log(title);
    setTitle(e.target.value);
  };

  const contentHandler = (content: string) => {
    console.log(content);
    setContent(content);
  };

  const createHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    const cookies = parseCookies();
    const myCookie = cookies.accessToken;
    if (boardType === "기업 게시판") {
      const res = await createCorpAxios(content, title, myCookie);
      if (res && res.status === 201) {
        router.push("/community/corpboard");
      }
    } else if (boardType === "자유 게시판") {
      const res = await createFreeAxios(content, title, myCookie);
      if (res && res.status === 201) {
        router.push("/community/freeboard");
      }
    }
  };

  return (
    <>
      <NavBar />
      <div className="z-50">
        <QuickMenu />
      </div>
      <div className="flex flex-col w-[1200px] mx-auto">
        <div className="text-20 font-bold my-50">게시물 작성</div>
        <form onSubmit={createHandler}>
          <div className="flex w-[1200px]">
            <select
              className="text-center appearance-none border-2 border-gray-200 focus:ring-0 rounded-5 py-2 mx-30 w-[100px] h-30"
              value={boardType}
              onChange={boardTypeHandler}
            >
              <option>기업 게시판</option>
              <option>자유 게시판</option>
            </select>
            <input
              placeholder="제목을 입력해 주세요."
              className="w-[1000px] mb-50 border-gray-200 border-2 rounded-5 px-5 py-3"
              onChange={handleTitle}
            />
          </div>
          <Editor
            apiKey="03a6amcyjwfpazpfdyc8hwnattyuvy9iz8hrib5lzq0jf4wk"
            initialValue=""
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
    </>
  );
}
