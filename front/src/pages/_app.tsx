import Question from "@/components/Question";
import "@/styles/globals.css";
import type { AppProps } from "next/app";
import { Hydrate, QueryClient, QueryClientProvider } from 'react-query'


export default function App({ Component, pageProps }: AppProps) {
  const queryClient = new QueryClient();

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <Hydrate state={pageProps.dehydratedProps}>
          <Component {...pageProps} />
          <Question />
        </Hydrate>
      </QueryClientProvider>
    </>
  );
}
