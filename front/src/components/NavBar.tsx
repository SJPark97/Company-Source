import Image from "next/image";
import { useRouter } from "next/router";
import Link from "next/link";

export default function NavBar() {
  const router = useRouter();

  return (
    <>
      <div
        className={
          "flex justify-between items-center mx-[10vw] w-[80vw] " +
          `${router.pathname === "/" ? "absolute" : null}`
        }
      >
        <div className="flex flex-row items-center">
          <Link href="/home">
            {/* <div className="relative w-[123px] h-[58px] mx-[3vw]"> */}
            <div>
              {router.pathname === "/" ? (
                // <Image
                //   src="/white_logo.png"
                //   alt="white_logo.png"
                //   sizes="(max-width: 692px) 123px,
                //           (max-width: 300px) 123px
                //           123px"
                //   fill
                // />
                <Image
                  src="/white_logo.png"
                  alt="white_logo.png"
                  width={123}
                  height={58}
                  className="w-auto"
                />
              ) : (
                <Image
                  src="/logo.png"
                  alt="logo.png"
                  width={123}
                  height={58}
                  className="w-auto"
                />
              )}
            </div>
          </Link>

          <Link href="/analysis">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/"
                    ? "text-white font-bold"
                    : `${
                        router.pathname === "/analysis"
                          ? "text-black font-bold"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              기업 분석
            </div>
          </Link>

          <Link href="/comparison">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/"
                    ? "text-white font-bold"
                    : `${
                        router.pathname === "/comparison"
                          ? "text-black font-bold"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              기업 비교
            </div>
          </Link>
        </div>
        <div>
          <Image src="/user.png" alt="user.png" width={40} height={40} />
        </div>
      </div>
    </>
  );
}
