import Link from "next/link";

import { Navbar, Container, Nav } from "react-bootstrap";

const AppBar = () => {
  return (
    <Navbar bg="primary" expand="lg">
      <Container className="w-100">
        <Navbar.Brand className="ms-3">
          <Link href="/">
            <span className="text-light">MYWORKSPACE</span>
          </Link>
        </Navbar.Brand>
        <div className="d-flex justify-content-end me-3">
          <Nav className="me-auto">
            <Nav.Link>
              <Link href="/">
                <span className="text-light">HOME</span>
              </Link>
            </Nav.Link>
            <Nav.Link>
              <Link href="/about">
                <span className="text-light">ABOUT</span>
              </Link>
            </Nav.Link>
            <Nav.Link>
              <Link href="/todo">
                <span className="text-light">TODO</span>
              </Link>
            </Nav.Link>
            <Nav.Link>
              <Link href="/photos">
                <span className="text-light">PHOTO</span>
              </Link>
            </Nav.Link>
          </Nav>
        </div>
      </Container>
    </Navbar>
  );
};

export default AppBar;
