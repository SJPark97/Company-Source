import Question from "@/components/Question";
import "@/styles/globals.css";
import type { AppProps } from "next/app";
import * as gtag from '../lib/gtag';
import Script from "next/script";
import { useRouter } from "next/router";
import { useEffect } from "react";

function pageViewHandler() {
  if (window.gtag) {
    window.gtag('config', process.env.GA_TRACKING_ID as string, {
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
      {/* Global Site Tag (gtag.js) - Google Analytics */}
      <Script
        strategy="afterInteractive"
        src={`https://www.googletagmanager.com/gtag/js?id=${gtag.GA_TRACKING_ID}`}
      />
      <Script
        id="gtag-init"
        strategy="afterInteractive"
        dangerouslySetInnerHTML={{
          __html: `
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', '${gtag.GA_TRACKING_ID}', {
              page_path: window.location.pathname,
            });
          `,
        }}
      />
      <Component {...pageProps} />
      <Question />
    </>
  );
}
