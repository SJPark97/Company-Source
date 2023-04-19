import Image from "next/image";
import { useRouter } from "next/router";

export default function NavBar() {
  const router = useRouter();
  console.log(router.pathname);
  return (
    <>
      <div className="flex justify-between mx-[10%]">
        <div className="flex items-center">
          <div>
            <Image src="/logo.png" alt="logo.png" width={123} height={58} />
          </div>

          <div
            className={
              "ml-[10%] w-100 " +
              `${router.pathname === "/home" ? "text-black" : "text-gray-200"}`
            }
          >
            기업 분석
          </div>
          <div
            className={
              "ml-[10%] w-100 " +
              `${
                router.pathname === "/analysis" ? "text-black" : "text-gray-300"
              }`
            }
          >
            기업 비교
          </div>
        </div>
        <div>
          <Image src="/user.png" alt="user.png" width={40} height={40} />
        </div>
      </div>
    </>
  );
}
