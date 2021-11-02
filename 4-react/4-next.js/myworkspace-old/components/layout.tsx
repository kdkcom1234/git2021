import Head from "next/head";

import React from "react";
import styles from "./layout.module.css";
import AppBar from "./appbar";

import Progress from "./progress";
import AlertStack from "./alert/alertStack";

interface LayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: LayoutProps) {
  return (
    <>
      <Head>
        <title>Myworkspace</title>
      </Head>
      <header>
        <AppBar />
      </header>
      <main className={styles.main}>
        {children}
        <Progress />
        <AlertStack />
      </main>
    </>
  );
}
