import { useState, useEffect } from "react";
import { SERVER_URL } from "@/utils/url";
import { useRouter } from "next/router";
import axios from "axios";
import NavBar from "@/components/NavBar";

export default function corpBoardDetail({ data }: { data: any }) {
  const [boardName, setBoardName] = useState<string>("");
  console.log(data);
  const router = useRouter();
  const currentLocation = router.pathname.split("/")[2];
  console.log(currentLocation);

  useEffect(() => {
    if (router.pathname && currentLocation === "corpboard") {
      setBoardName("기업");
    } else if (router.pathname && currentLocation === "freeboard") {
      setBoardName("자유");
    }
  }, [router]);

  return (
    <>
      <NavBar />
      {!data.data ? (
        <div>잘못된 접근입니다.</div>
      ) : (
        <div className="flex flex-col w-[1200px] mx-auto">
          <div className="text-24 mt-50 my-10 font-bold">
            {boardName} 게시판
          </div>
          <div className="flex bg-brand h-2 rounded-20 items-center"></div>

          {/* 제목, 작성자, 작성시간 */}
          <div className="flex flex-col border-gray-400 border-b-2 py-15">
            <div className="text-20 font-bold mb-5">{data.data.title}</div>
            <div className="flex">
              <div>{data.data.userName} | </div>
              <div className="ml-10">
                {data.data.date} {data.data.time}
              </div>
            </div>
          </div>
          <div className="my-50">{data.data.content}</div>
        </div>
      )}
    </>
  );
}

export async function getServerSideProps(context: any) {
  const detailId = context.query.detail;
  console.log(detailId);
  const { data } = await axios.get(SERVER_URL + `/community/corp/${detailId}`);

  return {
    props: {
      data,
    },
  };
}
