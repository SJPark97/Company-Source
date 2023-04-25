import LandingDiscription from "@/components/landing/LandingDescription";
import LandingImageCard from "@/components/landing/LandingImageCard";
import NavBar from "@/components/NavBar";

export default function Home() {
  const firstTitle = "다양한 분석 기법을\n 차트로 한눈에 볼 수 있어요.";
  const firstContent =
    "Company Source에서 제공하는\n 10가지 분석 기법을 만나보세요.";
  const secondTitle =
    "당신이 원하는 분석 기법으로\n 다양한 기업들을 비교해보세요.";
  const secondContent = "두 기업의 지표를 동시에 비교할 수 있어요.";
  const thirdTitle = "기업분석.\n 당신의 생각은 어떠신가요?";
  const thirdContent = "커뮤니티에서 여러사람들과\n 의견을 공유해보세요.";
  return (
    <div className="relative flex flex-col w-full">
      <NavBar />
      <div className="h-screen bg-top bg-no-repeat bg-cover bg-landing-first">
        <div className="text-white drop-shadow-lg font-bold text-56 w-[60vw] ml-[10vw] mt-[15vh]">
          기업 분석이 어려우신가요? Company Source와 함께 해보세요.
        </div>
      </div>

      <div className="flex bg-cover bg-gradient-to-b from-[#ffffff] to-[#F9FAFB] h-screen items-center">
        <LandingDiscription title={firstTitle} content={firstContent} />
        <div className="h-full">
          <LandingImageCard
            src="/landing-community.png"
            className="relative left-0 top-[20vh] w-[30vw] h-[40vh]"
          />
          <LandingImageCard
            src="/landing-community.png"
            className="relative left-[5vw] -top-[10vh] w-[30vw] h-[40vh]"
          />
          <LandingImageCard
            src="/landing-community.png"
            className="relative left-[10vw] -top-[40vh] w-[30vw] h-[40vh]"
          />
        </div>
      </div>

      <div className="flex items-center h-screen bg-white bg-cover">
        <div className="flex flex-col justify-center h-full">
          <LandingImageCard src="/landing-community.png" className="w-[30vw] h-[60vh]" />
        </div>
        <LandingDiscription title={secondTitle} content={secondContent} />
      </div>

      <div className="flex bg-cover bg-gradient-to-b from-[#F9FAFB] to-[#FFFFFF] h-screen items-center">
        <LandingDiscription title={thirdTitle} content={thirdContent} />
        <LandingImageCard
          src="/landing-community.png"
          className=" w-[40vw] h-[59vh]"
        />
      </div>
    </div>
  );
}
