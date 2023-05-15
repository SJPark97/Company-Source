import Image from "next/image";

interface corpPost {
  commentCount: number;
  communityId: number;
  date: string;
  likesCount: number;
  time: string;
  title: string;
  userName: string;
  viewCount: number;
}

export default function HotPost({
  postInformation,
}: {
  postInformation: corpPost;
}) {
  return (
    <div className="flex py-10 border-b-1">
      <div className="flex">
        <div className="w-100 text-center">{postInformation.communityId}</div>
        <div className="flex w-[400px]">
          <div className="line-clamp-1 mr-10">{postInformation.title}</div>
          <div className="font-bold text-brand">
            [{postInformation.commentCount}]
          </div>
        </div>
      </div>
      <div className="flex items-center text-center">
        <div className="flex justify-center w-30">
          <Image
            src="/like_after.png"
            alt="좋아요"
            width={80}
            height={72}
            className="w-18 h-16"
          />
        </div>
        <div className="text-center w-60">{postInformation.likesCount}</div>
      </div>
    </div>
  );
}
