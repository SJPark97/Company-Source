import Link from "next/link";
import { useState, useEffect } from "react";
import { parseCookies } from "nookies";
import { Router, useRouter } from "next/router";

export default function ({ path }: { path: string }) {
  const [boardName, setBoardName] = useState<string>("");
  const cookies = parseCookies();
  const accessToken = cookies["accessToken"];
  const router = useRouter();

  const handleWriteButtonClick = () => {
    if (accessToken) {
      router.push(
        { pathname: "/boardwrite", query: { type: "write", board: boardName } },
        "/boardwrite"
      );
    } else {
      router.push({ pathname: "/login", query: { redirect: "/boardwrite" } });
    }
  };

  useEffect(() => {
    if (path === "corpboard") {
      setBoardName("기업");
    } else if (path === "freeboard") {
      setBoardName("자유");
    }
  }, []);

  return (
    <>
      <button
        className="text-white bg-brand text-center w-70 h-40 rounded-10 px-10"
        onClick={handleWriteButtonClick}
      >
        글쓰기
      </button>
    </>
  );
}
