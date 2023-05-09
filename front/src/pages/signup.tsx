import { useState, useEffect } from "react";

export default function SignUp() {
  const [id, setId] = useState<string>("");
  const [idIsValid, setIdIsValid] = useState<boolean>(true);
  const [firstPassword, setFirstPassword] = useState<string>("");
  const [secondPassword, setSecondPassword] = useState<string>("");
  const [passwordIsValid, setPasswordIsValid] = useState<boolean>(true);

  const idHandler = (e: any) => {
    setId(e.target.value);
  };

  const firstPasswordHandler = (e: any) => {
    setFirstPassword(e.target.value);
  };

  const secondPasswordHandler = (e: any) => {
    setSecondPassword(e.target.value);
  };

  const signUpHandler = (e: any) => {
    e.preventDefault();

    // id 유효성 통과 못하면 경고
    if (!idIsValid) {
      alert("id 경고");
      return;
    }

    // password 유효성 통과 못하면 경고
    if (!passwordIsValid) {
      alert("password 경고");
      return;
    }
  };

  useEffect(() => {
    var regExpPassword =
      /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
    if (firstPassword !== secondPassword) {
      setPasswordIsValid(false);
    } else if (firstPassword === secondPassword) {
      setPasswordIsValid(true);
    }
  }, [firstPassword, secondPassword]);

  useEffect(() => {
    var regExp = /^[a-z]+[a-z0-9]{7,15}$/g;
    console.log(regExp.test(id));

    if (id.length === 0 || regExp.test(id)) {
      setIdIsValid(true);
    } else if (regExp.test(id)) {
      setIdIsValid(true);
    } else {
      setIdIsValid(false);
    }
  }, [id]);

  return (
    <>
      <div className="flex flex-col items-center my-[40px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Sign Up</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[550px] p-56">
          <form onSubmit={signUpHandler}>
            <div className="mb-[50px] relative">
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
                <div className="text-red-600 absolute text-13">
                  아이디는 영문+숫자 8~16글자로 입력해주세요.
                </div>
              ) : // <div className="text-white">blank area</div>
              null}
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

            <div className="mb-[100px] relative">
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
                <div className="text-red-600 absolute">
                  <div className="text-13">
                    비밀번호가 일치하지 않거나 양식에 맞지 않습니다.
                  </div>
                  <div className="text-13">
                    8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합해주세요.
                  </div>
                </div>
              ) : // <div className="text-white">blank area</div>
              null}
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
