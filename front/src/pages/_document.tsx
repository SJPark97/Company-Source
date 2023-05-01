import { Html, Head, Main, NextScript } from 'next/document'

export default function Document() {
  return (
    <Html lang="en">
      <Head>
        <title>컴퍼니소스</title>
        <meta name='description' content='재무제표를 이용한 기업분석을 제공하며 여러 기업들과 결과를 비교해볼 수 있습니다. 커뮤니티에서 기업에 대한 여러분의 의견을 다른 사람들과 공유해보세요.' />
        <meta property='og:url' content='https://company-source.com/' />
        <meta property='og:title' content='/company_default.jpg' />
        <meta property='og:description' content='기업분석이 어려우신가요? Company Source와 함께 해보세요.' />
      </Head>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  )
}
