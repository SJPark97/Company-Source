import Image from "next/image";
import Link from "next/link";
import { useRouter } from "next/router";

// interface Iprops {
//   page: Array<number>;
// }

type Pages = Array<number>;

export default function PageBox({
  pages,
  pageTotal,
}: {
  pages: Pages;
  pageTotal: number;
}) {
  const router = useRouter();
  return (
    <div className="flex justify-center items-center">
      {/* 이번 페이지 버튼 */}
      {parseInt(router.query.page as string) > 1 && (
        <Link
          href={{
            pathname: "/community/searchresult",
            query: {
              searchType: router.query.searchType,
              searchWord: router.query.searchWord,
              page: parseInt(router.query.page as string) - 1,
            },
          }}
        >
          <Image
            src="/prev_page.png"
            alt="이전페이지"
            width={52}
            height={64}
            className="w-14 h-auto mx-20"
          />
        </Link>
      )}

      {/* 페이지 리스트 */}
      {pages &&
        pages.map((page) => (
          <Link
            href={{
              pathname: "/community/searchresult",
              query: {
                searchType: router.query.searchType,
                searchWord: router.query.searchWord,
                page: page,
              },
            }}
            key={"검색결과" + `${page}`}
          >
            <div
              className={
                "mx-20 " +
                `${
                  parseInt(router.query.page as string) === page
                    ? "font-bold text-24"
                    : null
                }`
              }
            >
              {page}
            </div>
          </Link>
        ))}
      {/* {pages.includes(pageTotal) ? null : ( */}
      {parseInt(router.query.page as string) === pageTotal ? null : (
        <Link
          href={{
            pathname: "/community/searchresult",
            query: {
              searchType: router.query.searchType,
              searchWord: router.query.searchWord,
              page: parseInt(router.query.page as string) + 1,
            },
          }}
        >
          <Image
            src="/next_page.png"
            alt="다음페이지"
            width={52}
            height={64}
            className="w-14 h-auto mx-20"
          />
        </Link>
      )}
    </div>
  );
}
