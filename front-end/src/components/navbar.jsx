import {
  Container,
  Nav,
  Navbar as NavbarBootstrap,
  NavDropdown,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export const Navbar = () => {
  const navigate = useNavigate();
  return (
    <NavbarBootstrap
      bg="dark"
      variant="dark"
      expand="lg"
      style={{ padding: "1em 2.5em", justifyContent: "space-beetwenn" }}
    >
      <Container>
        <NavbarBootstrap.Brand href="/">HOME</NavbarBootstrap.Brand>
        <NavbarBootstrap.Toggle aria-controls="basic-NavbarBootstrap-nav" />
        <NavbarBootstrap.Collapse id="basic-NavbarBootstrap-nav">
          <Nav className="me-auto">
            <Nav.Link href="/students">Students</Nav.Link>
            <Nav.Link href="#link">Teachers</Nav.Link>
            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </NavbarBootstrap.Collapse>
      </Container>
    </NavbarBootstrap>
  );
};
