import "../styles/globals.css";
import "../styles/bootstrap-custom.scss";
import type { AppProps } from "next/app";

import { Provider } from "react-redux"; // react 앱에 redux store를 제공해줌
import { store } from "../provider"; // redux store

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <Provider store={store}>
      <Component {...pageProps} />
    </Provider>
  );
}
export default MyApp;
