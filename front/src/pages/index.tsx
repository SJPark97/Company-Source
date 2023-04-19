import Image from 'next/image'
import { Inter } from 'next/font/google'

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
  return (
    <div className='flex flex-col'>
      <div className='text-white text-[4.2vw] w-[60vw] ml-[10%] mt-[6%]'>
        기업 분석이 어려우신가요?SOURCE와 함께 해보세요.
      </div>
      <Image className='fixed -z-10' src="/landing-first.png" alt="landing-first" width={1920} height={1080} />
    </div>
  )
}
