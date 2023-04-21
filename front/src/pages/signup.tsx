import { useState } from "react";

export default function SignUp() {
  const [id, setId] = useState<string>("");
  const [idIsValid, setIdIsValid] = useState<boolean>(true);
  const [firstPassword, setFirstPassword] = useState<string>("");
  const [secondPassword, setSecondPassword] = useState<string>("");
  const [passwordIsValid, setPasswordIsValid] = useState<string>(true);

  const idHandler = (e: any) => {
    setId(e.target.value);
    if (id.length >= 8 && id.length <= 16) {
      setIdIsValid(true);
    }
  };

  const firstPasswordHandler = (e: any) => {
    setFirstPassword(e.target.value);
  };

  const secondPasswordHandler = (e: any) => {
    setSecondPassword(e.target.value);
  };

  const signUpHandler = (e: any) => {
    e.preventDefault();

    // id 8글자 미만이면 경고
    if (id.length < 8 || id.length > 16) {
      setIdIsValid(false);
      alert("ID 경고");
      return;
    }

    // 패스워드 2개가 같지 않다면 회원가입 실패
    if (firstPassword !== secondPassword) {
      setPasswordIsValid(false);
      alert("비번 경고");
    }
  };

  return (
    <>
      <div className="flex flex-col items-center my-[40px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Sign Up</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[550px] p-56">
          <form onSubmit={signUpHandler}>
            <div className="mb-[50px]">
              <label htmlFor="id" className="text-16 font-bold">
                아이디
              </label>
              <input
                id="id"
                type="text"
                maxLength={18}
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={idHandler}
              />
              {!idIsValid ? (
                <div className="text-red-600">
                  아이디는 8~16글자로 입력해주세요.
                </div>
              ) : (
                <div className="text-white">blank area</div>
              )}
            </div>
            <div className="mb-[50px]">
              <label htmlFor="password" className="text-16 font-bold">
                비밀번호
              </label>
              <input
                id="password"
                type="password"
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={firstPasswordHandler}
              />
            </div>

            <div className="mb-[100px]">
              <label htmlFor="password2" className="text-16 font-bold">
                비밀번호확인
              </label>
              <input
                id="password2"
                type="password"
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={secondPasswordHandler}
              />
              {!passwordIsValid ? (
                <div className="text-red-600">
                  비밀번호가 일치하지 않습니다.
                </div>
              ) : (
                <div className="text-white">blank area</div>
              )}
            </div>
            <button className="bg-brand w-[100%] h-50 font-bold text-white rounded-5">
              회원 가입
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
