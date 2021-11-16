import "../styles/globals.css";
import type { AppProps } from "next/app";

import { Provider as SessionProvider } from "next-auth/client";

function MyApp({ Component, pageProps: { session, ...pageProps } }: AppProps) {
  return (
    <SessionProvider session={session}>
      <Component {...pageProps} />
    </SessionProvider>
  );
}

export default MyApp;
