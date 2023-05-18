import { useState, useEffect } from "react";
import axios from "axios";
import { SERVER_URL } from "@/utils/url";
import {
  idCheckAxios,
  nickNameCheckAxios,
  signUpAxios,
} from "@/utils/user/api";
import { useRouter } from "next/router";
import Head from "next/head";

export default function SignUp() {
  const router = useRouter();

  const [id, setId] = useState<string>("");
  const [idIsValid, setIdIsValid] = useState<boolean>(false);
  const [idIsDuplicate, setIdIsDuplicate] = useState<boolean>(true);
  const [firstPassword, setFirstPassword] = useState<string>("");
  const [secondPassword, setSecondPassword] = useState<string>("");
  const [passwordIsValid, setPasswordIsValid] = useState<boolean>(false);
  const [nickName, setNickName] = useState<string>("");
  const [nickNameIsValid, setNickNameIsValid] = useState<boolean>(false);
  const [nickNameIsDuplicate, setNickNameIsDuplicate] = useState<boolean>(true);
  const [sex, setSex] = useState<string>("");
  const [birthDate, setBirthDate] = useState<any>("");

  // 생년월일 선택시 Maximum 을 오늘로 지정하기 위해 계산
  const today = new Date();
  const maxDate = today.toISOString().substring(0, 10);

  const idHandler = (e: any) => {
    setId(e.target.value);
    setIdIsDuplicate(true);
  };

  const firstPasswordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFirstPassword(e.target.value);
  };

  const secondPasswordHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSecondPassword(e.target.value);
  };

  const nickNameHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNickName(e.target.value);
    setNickNameIsDuplicate(true);
  };

  // Id 중복체크 함수
  const idCheckHandler = async () => {
    // 아이디가 유효하면 back에 Id 중복체크 요청
    if (idIsValid) {
      const res = await idCheckAxios(id);
      if (res.code === "C005") {
        alert("중복된 ID 입니다.");
        setIdIsDuplicate(true);
      } else if (!res.code) {
        setIdIsDuplicate(false);
      }
    } else {
      alert("유효하지 않은 ID 형식입니다.");
    }
  };

  // 닉네임 중복체크 함수
  const nickNameCheckHandler = async () => {
    if (nickNameIsValid) {
      const res = await nickNameCheckAxios(nickName);
      if (res.code === "C005") {
        alert("중복된 닉네임 입니다.");
        setNickNameIsDuplicate(true);
      } else if (!res.code) {
        setNickNameIsDuplicate(false);
      }
    } else {
      alert("유효하지 않은 닉네임 형식 입니다.");
    }
  };

  const sexHandler = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setSex(e.target.value);
  };

  const birthDateHandler = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setBirthDate(e.target.value);
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
      setNickNameIsValid(true);
    } else {
      setNickNameIsValid(false);
    }
  }, [nickName]);

  // 회원가입 전 유효성 검사목록 확인
  const signUpHandler = async (e: any) => {
    e.preventDefault();
    // id 유효성 통과 못하면 경고
    if (!idIsValid) {
      alert("id 경고");
      return;
    }

    if (idIsDuplicate) {
      alert("ID중복 체크를 해주세요.");
      return;
    }

    // password 유효성 통과 못하면 경고
    if (!passwordIsValid) {
      alert("password 경고");
      return;
    }

    if (!nickNameIsValid) {
      alert("닉네임 유효성 경고");
      return;
    }

    if (nickNameIsDuplicate) {
      alert("닉네임 중복체크 해주세요");
      return;
    }

    if (!sex) {
      alert("성별 선택해주세요");
      return;
    }

    if (!birthDate) {
      alert("생년월일을 선택해주세요");
      return;
    }

    const res = await signUpAxios(birthDate, id, nickName, firstPassword, sex);
    if (res && res.status === 200) {
      router.push("/");
    }
  };

  return (
    <>
      <Head>
        <title>컴퍼니소스 | 회원가입</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스(Company Source)에서는 재무제표를 이용한 기업분석을 제공하며 여러 기업들과 결과를 비교해볼 수 있습니다. 컴퍼니소스(Company Source)의 커뮤니티에서 기업에 대한 여러분의 의견을 다른 사람들과 공유해보세요."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/signup" />
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
      <div className="flex flex-col items-center my-[10px]">
        <p className="mb-10 font-bold text-40 text-brand">Sign Up</p>
        <div className="flex flex-col border-gray-300 border-1 w-[550px] h-[820px] p-56">
          <form onSubmit={signUpHandler}>
            {/* 아이디 입력 */}
            <div className="mb-[50px] relative">
              <div className="flex items-center">
                <label htmlFor="id" className="font-bold text-16">
                  아이디
                </label>
                <button
                  className="px-10 py-5 text-white border-1 rounded-10 ml-30 mr-30 bg-brand"
                  type="button"
                  onClick={idCheckHandler}
                >
                  중복체크
                </button>
                {!idIsDuplicate && id.length !== 0 ? (
                  <div className="text-10 text-brand">
                    사용할 수 있는 ID 입니다.
                  </div>
                ) : id !== null && id.length === 0 ? null : (
                  <div className="text-red-500 text-10">
                    중복체크를 해주세요.
                  </div>
                )}
              </div>
              <input
                id="id"
                type="text"
                maxLength={30}
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={idHandler}
                placeholder="예시 : companysource@companysource.com"
              />
              {!idIsValid && id.length !== 0 ? (
                <div className="absolute text-red-600 text-13">
                  아이디는 이메일 형식으로 입력해주세요.
                </div>
              ) : !id ? null : (
                <div className="absolute font-bold text-blue-500 text-13">
                  올바른 아이디 형식입니다.
                </div>
              )}
            </div>

            {/* 비밀번호 입력 */}
            <div className="mb-[50px]">
              <label htmlFor="password" className="font-bold text-16">
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
              <label htmlFor="password2" className="font-bold text-16">
                비밀번호확인
              </label>
              <input
                id="password2"
                type="password"
                className="border-b-2 focus:outline-none w-[436px]"
                onChange={secondPasswordHandler}
              />
              {!passwordIsValid && firstPassword.length !== 0 ? (
                <div className="absolute text-red-600">
                  <div className="text-13">
                    비밀번호가 일치하지 않거나 양식에 맞지 않습니다.
                  </div>
                  <div className="text-13">
                    8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합해주세요.
                  </div>
                </div>
              ) : firstPassword.length === 0 ? null : (
                <div className="absolute font-bold text-blue-500 text-13">
                  올바른 비밀번호 형식입니다.
                </div>
              )}
            </div>

            {/* 닉네임 입력 */}
            <div className="mb-[50px] relative">
              <div className="flex items-center">
                <label htmlFor="nickname" className="font-bold text-16">
                  닉네임
                </label>

                <button
                  className="px-10 py-5 text-white border-1 rounded-10 ml-30 mr-30 bg-brand"
                  type="button"
                  onClick={nickNameCheckHandler}
                >
                  중복체크
                </button>
                {!nickNameIsDuplicate && nickName.length !== 0 ? (
                  <div className="text-10 text-brand">
                    사용할 수 있는 닉네임 입니다.
                  </div>
                ) : nickName !== null && nickName.length === 0 ? null : (
                  <div className="text-red-500 text-10">
                    중복체크를 해주세요.
                  </div>
                )}
              </div>
              <input
                id="nickname"
                type="text"
                className="border-b-2 focus:outline-none w-[436px]"
                maxLength={10}
                onChange={nickNameHandler}
              />
              {!nickNameIsValid && nickName.length !== 0 ? (
                <div className="absolute text-red-600 text-13">
                  닉네임 특수문자나 공백이 없는 2~10자로 해주세요.
                </div>
              ) : null}
            </div>

            {/* 성별 선택 */}
            <fieldset className="mb-50">
              <legend className="mb-10 font-bold">성별</legend>
              <label htmlFor="male" className="cursor-pointer mr-30">
                <input
                  id="male"
                  type="radio"
                  name="sex"
                  className="peer peer-male"
                  value="남성남성"
                  onChange={sexHandler}
                  checked={sex === "남성남성"}
                />
                <span className="peer-checked:text-sky-500">남성</span>
              </label>

              <label htmlFor="female" className="cursor-pointer">
                <input
                  id="female"
                  type="radio"
                  name="sex"
                  className="peer peer-female"
                  value="여성여성"
                  onChange={sexHandler}
                  checked={sex === "여성여성"}
                />
                <span className="peer-checked:text-sky-500">여성</span>
              </label>
            </fieldset>

            {/* 생년월일 선택 */}
            <div className="mb-50">
              <label htmlFor="birthDate" className="font-bold text-16">
                생년월일
              </label>
              <br></br>
              <input
                id="birthDate"
                type="date"
                max={maxDate}
                className="p-10 mt-10 border-2 rounded-10"
                onChange={birthDateHandler}
              />
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
