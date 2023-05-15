import Router from "next/router";
import { useRouter } from "next/router";
import Image from "next/image";

export default function DetailModifyButton({
  title,
  content,
  communityId,
  board,
}: {
  title: string;
  content: string;
  communityId: string;
  board: string;
}) {
  const goToModify = (e: React.MouseEvent<HTMLDivElement>) => {
    Router.push(
      {
        pathname: "/boardwrite",
        query: {
          type: "modify",
          title: title,
          content: content,
          communityId: communityId,
          board,
        },
      },
      "/boardwrite"
    );
  };
  return (
    <div className="flex" onClick={goToModify}>
      <div className="w-20 h-20 cursor-pointer">
        <Image src="/modify.png" alt="수정" width={96} height={96} />
      </div>
      <button className="text-gray-400">수정</button>
    </div>
  );
}
