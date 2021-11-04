import { useRouter } from "next/router";
import React, { MutableRefObject, useRef } from "react";
import Link from "next/link";
import { Button, FormControl, InputGroup } from "react-bootstrap";
import authApi from "../api/auth";

import { setSessionId } from "../lib/cookie";
import Progress from "../components/progress";
import AlertStack from "../components/alert/alertStack";

const SignIn = () => {
  const router = useRouter();

  // current 객체 null체크 해야함
  // const userIdRef = useRef<HTMLInputElement>(null);

  // current 객체 null체크 안 해됨
  const userIdRef = useRef() as MutableRefObject<HTMLInputElement>;
  const passwordRef = useRef() as MutableRefObject<HTMLInputElement>;

  const handleSignIn = async () => {
    const result = await authApi.signin({
      userId: userIdRef.current.value,
      password: passwordRef.current.value,
    });

    console.log(result.data);
    const sessionId = result.data;

    // 세션쿠키를 생성함
    setSessionId(sessionId);
    router.replace("/");
  };

  return (
    <div
      className="d-flex flex-column justify-content-center align-items-center"
      style={{ height: "100vh" }}
    >
      <div>
        <InputGroup className="mb-3 d-flex flex-column">
          <FormControl
            placeholder="userid"
            className="w-100"
            ref={userIdRef}
            defaultValue="kdkcom"
          />
          <FormControl
            placeholder="password"
            className="w-100"
            type="password"
            ref={passwordRef}
            defaultValue="password123!"
          />
          <Button
            variant="primary"
            className="mt-1"
            onClick={() => {
              handleSignIn();
            }}
          >
            Sign In
          </Button>
          <Link href="/signup">
            <a className="text-secondary text-end text-decoration-underline">
              sign up
            </a>
          </Link>
        </InputGroup>

        <Progress />
        <AlertStack />
      </div>
    </div>
  );
};

export default SignIn;
