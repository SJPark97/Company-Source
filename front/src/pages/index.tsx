import LandingDiscription from "@/components/landing/LandingDescription";
import LandingImageCard from "@/components/landing/LandingImageCard";
import NavBar from "@/components/NavBar";
import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import { motion } from "framer-motion";

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
    <>
      <Head>
        <title>컴퍼니소스</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta
          name="description"
          content="컴퍼니소스에서는 재무제표를 이용한 기업분석을 제공하며 여러 기업들과 결과를 비교해볼 수 있습니다. 컴퍼니소스의 커뮤니티에서 기업에 대한 여러분의 의견을 다른 사람들과 공유해보세요."
        />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="https://company-source.com/" />
        <meta property="og:title" content="Company Source" />
        <meta property="og:image" content="/company_default.jpg" />
        <meta
          property="og:description"
          content="기업분석이 어려우신가요? Company Source와 함께 해보세요."
        />
      </Head>
      <div className="flex flex-col justify-center w-full">
        <div className="z-50">
          <NavBar />
        </div>

        <div className="relative">
          <video
            className="w-full h-auto filter brightness-[45%]"
            autoPlay
            muted
            loop
          >
            <source src="/randing_video.mp4" className="w-full" />
          </video>
          <div
            className="text-white drop-shadow-lg font-bold text-40 w-[65vw] text-center animate-fadeIn absolute top-[40%] left-1/2 -translate-x-1/2 -translate-y-1/2"
            style={{ textShadow: "2px 2px 2px rgba(0, 0, 0, 1)" }}
          >
            기업 분석이 어려우신가요? <br></br> Company Source와 함께 해보세요.
          </div>
          <div className="absolute top-[75%] left-1/2 animate-bounce">
            <Image
              src="./arrow-down.svg"
              alt="arrow-down"
              width={60}
              height={30}
              className="drop-shadow-lg"
            />
          </div>
        </div>

        <div className="flex justify-around bg-cover bg-gradient-to-b h-screen from-[#ffffff] to-[#F9FAFB] items-center px-100">
          <div>
            <LandingDiscription title={firstTitle} content={firstContent} />
          </div>
          <div>
            <LandingImageCard
              src="/chart3.jpg"
              className="relative left-0 top-[200px]"
              width={460}
              height={306}
              direction="toLeft"
            />
            <LandingImageCard
              src="/chart2.jpg"
              className="relative left-60"
              width={460}
              height={306}
              direction="toLeft"
            />
            <LandingImageCard
              src="/chart1.jpg"
              className="relative left-[120px] top-[-200px]"
              width={460}
              height={306}
              direction="toLeft"
            />
          </div>
        </div>

        <div className="flex items-center justify-around h-screen bg-white bg-cover px-100">
          <div className="flex flex-col justify-center">
            <LandingImageCard
              src="/chart-comparison.jpg"
              className=""
              width={460}
              height={393}
              direction="toRight"
            />
          </div>
          <div>
            <LandingDiscription title={secondTitle} content={secondContent} />
          </div>
        </div>

        <div className="flex justify-around bg-cover bg-gradient-to-b from-[#F9FAFB] to-[#FFFFFF] h-screen items-center px-100">
          <div className="flex flex-col">
            <LandingDiscription title={thirdTitle} content={thirdContent} />

            <Link href="/home" className="self-center mt-30">
              <div className="bg-brand drop-shadow-lg w-[250px] text-center text-white text-20 p-16 rounded-10 hover:bg-blue-800">
                기업 분석 하러가기
              </div>
            </Link>
          </div>
          <LandingImageCard
            src="/community.jpg"
            className=""
            width={600}
            height={400}
            direction="toLeft"
          />
        </div>
      </div>
    </>
  );
}
