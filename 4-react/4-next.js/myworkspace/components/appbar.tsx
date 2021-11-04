import Link from "next/link";

import { Navbar, Container, Nav } from "react-bootstrap";
import Profile from "./profile";

const AppBar = () => {
  return (
    <Navbar bg="primary" expand="lg">
      <Container className="w-100">
        <Navbar.Brand className="ms-3">
          <Link href="/">
            <a className="text-light">MYWORKSPACE</a>
          </Link>
        </Navbar.Brand>
        <div className="d-flex justify-content-end me-3">
          <Nav className="me-auto">
            <Nav.Item className="me-3">
              {/* next/link 안에 a태그를 주어서 스타일 먹인다 */}
              {/* 변환된 태그 <a class="text-light" href="/">HOME</> */}
              <Link href="/">
                <a className="text-light">HOME</a>
              </Link>
            </Nav.Item>
            <Nav.Item className="me-3">
              <a className="text-light me-3" href="/about">
                ABOUT(Reload, SSR)
              </a>
              <Link href="/about">
                <a className="text-light">ABOUT(Lazy Load, CSR)</a>
              </Link>
            </Nav.Item>
            <Nav.Item className="me-3">
              <Link href="/todo">
                <a className="text-light">TODO</a>
              </Link>
            </Nav.Item>
            <Nav.Item className="me-5">
              <Link href="/photos">
                <a className="text-light">MY PHOTO</a>
              </Link>
            </Nav.Item>
            <Profile />
          </Nav>
        </div>
      </Container>
    </Navbar>
  );
};

export default AppBar;
