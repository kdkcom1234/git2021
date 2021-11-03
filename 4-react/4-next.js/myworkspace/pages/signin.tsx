import Link from "next/link";
import React from "react";
import { Button, FormControl, InputGroup } from "react-bootstrap";

const SignIn = () => {
  return (
    <div
      className="d-flex flex-column justify-content-center align-items-center"
      style={{ height: "100vh" }}
    >
      <div>
        <InputGroup className="mb-3 d-flex flex-column">
          <FormControl placeholder="userid" className="w-100" />
          <FormControl
            placeholder="password"
            className="w-100"
            type="password"
          />
          <Button variant="primary" className="mt-1">
            Sign In
          </Button>
          <Link href="/signup">
            <a className="text-secondary text-end text-decoration-underline">
              sign up
            </a>
          </Link>
        </InputGroup>
      </div>
    </div>
  );
};

export default SignIn;
