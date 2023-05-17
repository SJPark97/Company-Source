interface Post {
  commentCount: number;
  communityId: number;
  date: "string";
  likesCount: number;
  time: "string";
  title: "string";
  userName: "string";
  viewCount: "string";
}

export default function PostThumbnail({
  post,
  keyword,
}: {
  post: Post;
  keyword: string;
}) {
  // 검색어 Highlight 해주는 함수
  const highlightSearchWord = (text: string, searchWord: string) => {
    if (!searchWord) return text;
    const regex = new RegExp(`(${searchWord})`, "gi");
    return text.split(regex).map((chunk, index) =>
      regex.test(chunk) ? (
        <span className="font-bold text-brand" key={index}>
          {chunk}
        </span>
      ) : (
        chunk
      )
    ) as React.ReactNode;
  };

  return (
    <div className="flex text-center py-10">
      <div className="w-70">{post.communityId}</div>
      <div className="flex w-[550px]">
        {/* <div className="line-clamp-1 mr-10">{post.title}</div> */}
        <div className="line-clamp-1 mr-10">
          {highlightSearchWord(post.title, keyword)}
        </div>
        <div className="font-bold text-brand">[{post.commentCount}]</div>
      </div>
      <div className="w-[200px]">{post.userName}</div>
      <div className="w-100">{post.likesCount}</div>
      <div className="w-100">{post.viewCount}</div>
      <div className="w-[180px]">{post.date}</div>
    </div>
  );
}
