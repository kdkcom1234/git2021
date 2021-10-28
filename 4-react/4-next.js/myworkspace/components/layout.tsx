import Head from "next/head";

import React from "react";
import styles from "./layout.module.css";
import AppBar from "./appbar";

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
      <main className={styles.main}>{children}</main>
    </>
  );
}
