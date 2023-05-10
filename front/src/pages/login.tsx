import { useState } from "react";
import Link from "next/link";
import { parseCookies, setCookie, destroyCookie } from "nookies";
import { loginAxios } from "@/utils/user/api";
import { useRouter } from "next/router";

export default function Login() {
  const router = useRouter();
  const [id, setId] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [failMessage, setFailMessage] = useState<boolean>(false);

  const idHandler = (e) => {
    setId(e.target.value);
  };

  const passwordHandler = (e) => {
    setPassword(e.target.value);
  };

  const loginHandler = async (e) => {
    e.preventDefault();
    const res = await loginAxios(id, password);

    // 로그인 성공해서 'data'키가 있으면 쿠키 저장
    if (res.token) {
      const cookies = parseCookies();
      setCookie(null, "accessToken", res.token, {
        maxAge: 60 * 60 * 24,
        secure: true,
        path: "/",
      });
      router.push("/home");
      // 로그인 실패시 FailMessage 상태 변경
    } else if (!res.data) {
      setFailMessage(true);
    }
  };

  return (
    <>
      <div className="flex flex-col items-center my-[60px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Log In</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[400px] p-56">
          <form onSubmit={loginHandler}>
            <label htmlFor="id" className="text-16 font-bold">
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
              <label htmlFor="id" className="text-16 font-bold">
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
                <div className="absolute text-16 text-red-500 top-60">
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
