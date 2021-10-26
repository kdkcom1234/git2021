import Head from "next/head";

import React from "react";
import styles from "./layout.module.css";
import Navbar from "./navbar";

interface LayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: LayoutProps) {
  return (
    <>
      <Head>
        <title>Layouts Example</title>
      </Head>
      <header>
        <Navbar />
      </header>
      <main className={styles.main}>{children}</main>
    </>
  );
}
