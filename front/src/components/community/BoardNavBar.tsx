import { useState, useEffect } from "react";
import Link from "next/link";
import { useRouter } from "next/router";

export default function BoardNavBar() {
  const router = useRouter();
  const [all, setAll] = useState<string>("");
  const [recommend, setRecommend] = useState<string>("");
  const routerArr = router.pathname.split("/");
  const [boardName, setBoardName] = useState<string>("");

  useEffect(() => {
    const arr = router.pathname.split("/");
    setAll("/" + arr[1] + "/" + arr[2]);
    setRecommend("/" + arr[1] + "/" + arr[2] + "/recommend");

    if (routerArr[2] === "corpboard") {
      setBoardName("기업");
    } else if (routerArr[2] === "freeboard") {
      setBoardName("자유");
    }
  }, []);
  return (
    <div className="flex flex-col items-center">
      <div className="w-[1200px]">
        <div className="text-24 font-bold my-50">{boardName} 게시판</div>
        <div className="flex bg-brand h-50 text-white items-center rounded-20 min-w-1228">
          <Link href={all}>
            <div
              className={
                "mx-30 text-white " + `${!routerArr[3] ? "font-bold" : null}`
              }
            >
              전체
            </div>
          </Link>
          <Link href={recommend}>
            <div
              className={
                "mx-30 text-white " +
                `${routerArr[3] === "recommend" ? "font-bold" : null}`
              }
            >
              추천순
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
}
