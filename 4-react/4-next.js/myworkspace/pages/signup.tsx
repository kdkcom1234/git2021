import { useRouter } from "next/router";
import React, { MutableRefObject, useRef } from "react";
import { Button, FormControl, InputGroup } from "react-bootstrap";
import authApi from "../api/auth";

import Progress from "../components/progress";
import AlertStack from "../components/alert/alertStack";

const SignUp = () => {
  const userIdRef = useRef() as MutableRefObject<HTMLInputElement>;
  const passwordRef = useRef() as MutableRefObject<HTMLInputElement>;
  const usernameRef = useRef() as MutableRefObject<HTMLInputElement>;
  const emailRef = useRef() as MutableRefObject<HTMLInputElement>;

  const router = useRouter();

  const handleSignUp = async () => {
    const result = await authApi.signup({
      userId: userIdRef.current.value,
      password: passwordRef.current.value,
      username: usernameRef.current.value,
      email: emailRef.current.value,
      role: "USER", // USER, ADMIN, PARTNER, GUEST....
    });

    console.log(result.data);
    if (result.data === "success") {
      router.replace("/signin");
    }
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

          <FormControl
            placeholder="username"
            className="w-100"
            ref={usernameRef}
            defaultValue="Daekeun Ko"
          />
          <FormControl
            placeholder="email"
            className="w-100"
            type="email"
            ref={emailRef}
            defaultValue="kdkcom1234@gmail.com"
          />
          <Button
            variant="primary"
            className="mt-1"
            onClick={() => {
              handleSignUp();
            }}
          >
            Sign Up
          </Button>
        </InputGroup>

        <Progress />
        <AlertStack />
      </div>
    </div>
  );
};

export default SignUp;
