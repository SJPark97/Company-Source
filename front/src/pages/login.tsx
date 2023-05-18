import { useState, useEffect } from "react";
import Link from "next/link";
import { parseCookies, setCookie, destroyCookie } from "nookies";
import { loginAxios } from "@/utils/user/api";
import { useRouter } from "next/router";
import Head from "next/head";

export default function Login() {
  const router = useRouter();
  const [id, setId] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [failMessage, setFailMessage] = useState<boolean>(false);
  const [isRedirect, setIsRedirect] = useState<string>("");

  const idHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setId(e.target.value);
  };

  const passwordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const loginHandler = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!id) {
      setFailMessage(true);
      return;
    }
    if (!password) {
      setFailMessage(true);
    }
    const res = await loginAxios(id, password);
    // 로그인 성공해서 'data'키가 있으면 쿠키 저장
    if (res) {
      const cookies = parseCookies();
      setCookie(null, "accessToken", res.token, {
        maxAge: 60 * 60 * 24,
        secure: true,
        path: "/",
      });
      setCookie(null, "nickName", res.nickname, {
        maxAge: 60 * 60 * 24,
        secure: true,
        path: "/",
      });
      router.back();
      // if (isRedirect) {
      //   router.push("/" + `${isRedirect}`);
      // } else {
      //   router.push("/home");
      // }
    } else if (!res) {
      // 로그인 실패시 FailMessage 상태 변경
      alert("아이디나 비밀번호를 확인해주세요.");
      setFailMessage(true);
    }
  };

  useEffect(() => {
    if (router.query.redirect && typeof router.query.redirect === "string") {
      setIsRedirect(router.query.redirect);
    }
  }, [router]);

  return (
    <>
      <Head>
        <title>컴퍼니소스 | 로그인</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스(Company Source)에서는 재무제표를 이용한 기업분석을 제공하며 여러 기업들과 결과를 비교해볼 수 있습니다. 컴퍼니소스(Company Source)의 커뮤니티에서 기업에 대한 여러분의 의견을 다른 사람들과 공유해보세요."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/login" />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
        <meta
          name="google-site-verification"
          content="0FzOO996BLTIEWFgwlmmYv-F1WmHiM6SrbwEbK9-p3k"
        />
      </Head>
      <div className="flex flex-col items-center my-[60px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Log In</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[400px] p-56">
          <form onSubmit={loginHandler}>
            <label htmlFor="id" className="font-bold text-16">
              아이디
            </label>
            <br></br>
            <input
              id="id"
              type="text"
              className="border-b-2 focus:outline-none mb-[50px] w-[436px]"
              onChange={idHandler}
              placeholder="ex) company@source.com"
            />
            <br></br>
            <div className="relative">
              <label htmlFor="id" className="font-bold text-16">
                비밀번호
              </label>
              <br></br>
              <input
                id="id"
                type="password"
                className="border-b-2 focus:outline-none w-[436px] mb-[70px]"
                onChange={passwordHandler}
              />
              {failMessage && (
                <div className="absolute text-red-500 text-16 top-60">
                  CompanySource 계정 혹은 비밀번호가 일치하지 않습니다. 입력한
                  내용을 다시 확인해 주세요.
                </div>
              )}
            </div>
            <button className="bg-brand w-[100%] h-50 font-bold text-white rounded-5">
              로그인
            </button>
          </form>
          <div className="flex mt-[20px] text-13 justify-between">
            <Link href="/signup">
              <div>회원가입</div>
            </Link>
            {/* <div className="flex">
              <div className="mr-[10px]">계정 찾기</div> |{" "}
              <div className="ml-[10px]">비밀번호 찾기</div>
            </div> */}
          </div>
        </div>
      </div>
    </>
  );
}
