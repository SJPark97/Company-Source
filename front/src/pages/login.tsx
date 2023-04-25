import Link from "next/link";

export default function Login() {
  return (
    <>
      <div className="flex flex-col items-center my-[40px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Log In</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[500px] p-56">
          <form>
            <label htmlFor="id" className="text-16 font-bold">
              아이디
            </label>
            <br></br>
            <input
              id="id"
              type="text"
              className="border-b-2 focus:outline-none mb-[50px] w-[436px]"
            />
            <br></br>
            <label htmlFor="id" className="text-16 font-bold">
              비밀번호
            </label>
            <br></br>
            <input
              id="id"
              type="text"
              className="border-b-2 focus:outline-none w-[436px] mb-[100px]"
            />
            <button className="bg-brand w-[100%] h-50 font-bold text-white rounded-5">
              로그인
            </button>
          </form>
          <div className="flex mt-[20px] text-13 justify-between">
            <Link href="/signup">
              <div>회원가입</div>
            </Link>
            <div className="flex">
              <div className="mr-[10px]">계정 찾기</div> |{" "}
              <div className="ml-[10px]">비밀번호 찾기</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
