import Image from 'next/image'
import { Inter } from 'next/font/google'
import background from '/landing-first.png';
import LandingDiscription from '@/components/landing/LandingDescription';

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
  const firstTitle = '다양한 분석 기법을\n 차트로 한눈에 볼 수 있어요.'
  const firstContent = 'Company Source에서 제공하는\n 10가지 분석 기법을 만나보세요.'
  return (
    <div className='flex flex-col'>
      <div className='bg-no-repeat bg-cover bg-top bg-landing-first w-[100vw] h-[100vh]'>
        <div className='text-white font-bold text-[calc(2.5vw+2.2vh)] w-[60vw] ml-[10vw] mt-[15vh]'>
          기업 분석이 어려우신가요? Company Source와 함께 해보세요.
        </div>
      </div>

      <div className='bg-cover bg-gradient-to-b from-[#ffffff] to-[#F9FAFB] w-[100vw] h-[100vh]'>
        <LandingDiscription title={firstTitle} content={firstContent} position='left' />
      </div>

      <div className='bg-white w-[100vw] h-[100vh]'>

      </div>

      <div className='bg-cover bg-gradient-to-b from-[#F9FAFB] to-[#FFFFFF] w-[100vw] h-[100vh]'>

      </div>

    </div>
  )
}
