import Question from "@/components/Question";
import "@/styles/globals.css";
import type { AppProps } from "next/app";
import { useRouter } from "next/router";
import { useEffect } from "react";

function pageViewHandler() {
  if (window.gtag) {
    window.gtag('config', process.env.NEXT_PUBLIC_GA_ID as string, {
      page_path: window.location.pathname + window.location.search
    })
  }
}

export default function App({ Component, pageProps }: AppProps) {
  const router = useRouter()
  useEffect(() => {
    router.events.on('routeChangeComplete', pageViewHandler)

    return () => {
      router.events.off('routeChangeComplete', pageViewHandler)
    }
  }, [router.events])
  return (
    <>
      <Component {...pageProps} />
      <Question />
    </>
  );
}
