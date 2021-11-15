import { Provider as SessionProvider } from "next-auth/client";

import "../styles/globals.css";
import type { AppProps } from "next/app";
import React from "react";

function MyApp({ Component, pageProps: { session, ...pageProps } }: AppProps) {
  return (
    <SessionProvider session={session}>
      <Component {...pageProps} />
    </SessionProvider>
  );
}

export default MyApp;
