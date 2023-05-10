import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/router";
import Link from "next/link";
import { parseCookies, destroyCookie } from "nookies";
import { Tooltip } from "@material-tailwind/react";

export default function NavBar() {
  const router = useRouter();

  // 로그인 여부 state
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);

  // 닉네임 state
  const [nickName, setNickName] = useState<string>("");

  const logOutHandler = () => {
    destroyCookie(null, "accessToken");
    destroyCookie(null, "nickName");
    router.reload();
  };

  useEffect(() => {
    // NavBar가 렌더링 될때 로그인 여부를 감지해서 isLoggedIn 상태 변경
    const cookies = parseCookies();
    if (cookies.accessToken) {
      setIsLoggedIn(true);
    }
    if (cookies.nickName) {
      setNickName(cookies.nickName);
    }
  }, []);

  return (
    <>
      <div
        className={
          "flex justify-between items-center mx-[10vw] w-[80vw] " +
          `${router.pathname === "/" ? "absolute" : null}`
        }
      >
        <div className="flex flex-row items-center">
          <Link href="/">
            <div>
              {router.pathname === "/" || router.pathname === "/home" ? (
                <Image
                  src="/white_logo.png"
                  alt="white_logo.png"
                  width={123}
                  height={58}
                  className="w-auto mr-[3vw]"
                />
              ) : (
                <Image
                  src="/logo.png"
                  alt="logo.png"
                  width={123}
                  height={58}
                  className="w-auto mr-[3vw]"
                />
              )}
            </div>
          </Link>

          <Link href="/home">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/"
                    ? "text-white"
                    : `${
                        router.pathname === "/home"
                          ? "text-white font-bold"
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
                  router.pathname === "/" || router.pathname === "/home"
                    ? "text-white"
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

          <Link href="/community">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname.slice(0, 10) === "/community" // community 하위 라우터들을 모두 처리
                    ? "text-black font-bold"
                    : `${
                        router.pathname === "/" || router.pathname === "/home"
                          ? "text-white"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              커뮤니티
            </div>
          </Link>
        </div>

        {/* User Icon */}
        <div className="flex items-center">
          {router.pathname === "/" || router.pathname === "/home" ? (
            <Tooltip
              content={nickName}
              animate={{
                mount: { scale: 1, y: 0 },
                unmount: { scale: 0, y: -30 },
              }}
              className="p-[10px] bg-white text-black rounded-10"
            >
              <Link href={isLoggedIn ? "/mypage" : "/login"}>
                <Image
                  src="/white_user.png"
                  alt="white_user.png"
                  width={40}
                  height={40}
                />
              </Link>
            </Tooltip>
          ) : (
            <Tooltip
              content={nickName}
              animate={{
                mount: { scale: 1, y: 0 },
                unmount: { scale: 0, y: -30 },
              }}
              className="p-[10px] rounded-10"
            >
              <Link href={isLoggedIn ? "/mypage" : "/login"}>
                <Image src="/user.png" alt="user.png" width={40} height={40} />
              </Link>
            </Tooltip>
          )}
          {isLoggedIn ? (
            <div
              className={
                "ml-20 cursor-pointer " +
                `${
                  router.pathname !== "/" && router.pathname !== "/home"
                    ? "text-gray-400"
                    : "text-white"
                }`
              }
              onClick={logOutHandler}
            >
              로그아웃
            </div>
          ) : null}
        </div>
      </div>
    </>
  );
}
