import { useState, useEffect } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";

export default function SignUp() {
  const [id, setId] = useState<string>("");
  const [idIsValid, setIdIsValid] = useState<boolean>(false);
  const [firstPassword, setFirstPassword] = useState<string>("");
  const [secondPassword, setSecondPassword] = useState<string>("");
  const [passwordIsValid, setPasswordIsValid] = useState<boolean>(false);
  const [nickName, setNickName] = useState<string>("");
  const [nickNameIsValid, setNickNameIsValid] = useState<boolean>(false);
  const [isDuplicate, setIsDuplicate] = useState<boolean>(true);

  const idHandler = (e: any) => {
    setId(e.target.value);
  };

  const firstPasswordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFirstPassword(e.target.value);
  };

  const secondPasswordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSecondPassword(e.target.value);
  };

  const nickNameHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNickName(e.target.value);
  };

  const nickNameCheckHandler = async () => {
    if (nickNameIsValid) {
      await axios
        .get(SERVER_URL + `/user/validnickname/${nickName}`)
        .then((res) => console.log(res));
    } else {
      alert("닉네임이 유효하지 않습니다.");
    }
  };

  // ID 유효성 검사
  useEffect(() => {
    var regExpId =
      /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    // if (id.length === 0 || regExpId.test(id)) {
    if (regExpId.test(id)) {
      setIdIsValid(true);
    } else if (regExpId.test(id)) {
      setIdIsValid(true);
    } else {
      setIdIsValid(false);
    }
  }, [id]);

  // 비밀번호 유효성 검사
  useEffect(() => {
    var regExpPassword =
      /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
    if (firstPassword !== secondPassword) {
      setPasswordIsValid(false);
    } else if (
      firstPassword === secondPassword &&
      regExpPassword.test(firstPassword) &&
      regExpPassword.test(secondPassword)
    ) {
      setPasswordIsValid(true);
    }
  }, [firstPassword, secondPassword]);

  // 닉네임 유효성 검사
  useEffect(() => {
    var regExpNickName = /^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]{2,10}$/;
    if (regExpNickName.test(nickName)) {
      console.log("유효함");
      setNickNameIsValid(true);
    } else {
      console.log("유효하지않음");
      setNickNameIsValid(false);
    }
  }, [nickName]);

  // 회원가입 전 유효성 검사목록 확인
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

  return (
    <>
      <div className="flex flex-col items-center my-[40px]">
        <p className="text-40 text-brand font-bold mb-[5vh]">Sign Up</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[650px] p-56">
          <form onSubmit={signUpHandler}>
            {/* 아이디 입력 */}
            <div className="mb-[50px] relative">
              <div className="flex items-center">
                <label htmlFor="id" className="text-16 font-bold">
                  아이디
                </label>
                <div className="text-10 text-gray-400 ml-30">
                  예시 : companysource@companysource.com
                </div>
              </div>
              <input
                id="id"
                type="text"
                maxLength={30}
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={idHandler}
              />
              {!idIsValid && id.length !== 0 ? (
                <div className="text-red-600 absolute text-13">
                  아이디는 이메일 형식으로 입력해주세요.
                </div>
              ) : !id ? null : (
                <div className="text-blue-500 absolute text-13 font-bold">
                  올바른 아이디 형식입니다.
                </div>
              )}
            </div>

            {/* 비밀번호 입력 */}
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
            <div className="mb-[50px] relative">
              <label htmlFor="password2" className="text-16 font-bold">
                비밀번호확인
              </label>
              <input
                id="password2"
                type="password"
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={secondPasswordHandler}
              />
              {!passwordIsValid && firstPassword.length !== 0 ? (
                <div className="text-red-600 absolute">
                  <div className="text-13">
                    비밀번호가 일치하지 않거나 양식에 맞지 않습니다.
                  </div>
                  <div className="text-13">
                    8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합해주세요.
                  </div>
                </div>
              ) : // <div className="text-white">blank area</div>
              firstPassword.length === 0 ? null : (
                <div className="text-blue-500 absolute text-13 font-bold">
                  올바른 비밀번호 형식입니다.
                </div>
              )}
            </div>

            {/* 닉네임 입력 */}
            <div className="mb-[50px] relative">
              <div className="flex items-center">
                <label htmlFor="nickname" className="text-16 font-bold">
                  닉네임
                </label>

                <button
                  className="border-1 rounded-10 ml-30 pl-5 pr-5"
                  type="button"
                  onClick={nickNameCheckHandler}
                >
                  중복체크
                </button>
              </div>
              <input
                id="nickname"
                type="text"
                className="border-b-2 focus:outline-none w-[436px]"
                maxLength={10}
                onChange={nickNameHandler}
              />
              {!nickNameIsValid && nickName.length !== 0 ? (
                <div className="text-13 text-red-600 absolute">
                  닉네임 특수문자나 공백이 없는 2~10자로 해주세요.
                </div>
              ) : null}
            </div>

            {/* 성별 선택 */}
            <fieldset className="mb-50">
              <legend className="font-bold mb-10">성별</legend>
              <label htmlFor="male" className="cursor-pointer mr-30">
                <input
                  id="male"
                  type="radio"
                  name="sex"
                  className="peer peer-male"
                />
                <span className="peer-checked:text-sky-500">남성</span>
              </label>

              <label htmlFor="female" className="cursor-pointer">
                <input
                  id="female"
                  type="radio"
                  name="sex"
                  className="peer peer-female"
                />
                <span className="peer-checked:text-sky-500">여성</span>
              </label>
            </fieldset>

            <button className="bg-brand w-[100%] h-50 font-bold text-white rounded-5">
              회원 가입
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
