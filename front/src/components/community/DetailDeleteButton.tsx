import { deleteCorpDetailAxios } from "@/utils/commnuity/api";
import Image from "next/image";
import { parseCookies } from "nookies";
import router from "next/router";

export default function DetailDeleteButton({
  communityId,
}: {
  communityId: string;
}) {
  const cookies = parseCookies();
  const deleteHandler = async () => {
    // 삭제 OK누르면
    if (confirm("게시물을 삭제 하시겠습니까?")) {
      await deleteCorpDetailAxios(communityId, cookies.accessToken);
      router.push("/community/corpboard");
    }
  };
  return (
    <>
      <div className="flex" onClick={deleteHandler}>
        <div className="w-20 h-20 cursor-pointer">
          <Image src="/delete.png" alt="삭제" width={96} height={96} />
        </div>
        <button className="text-gray-400">삭제</button>
      </div>
    </>
  );
}
