import {
  Container,
  Nav,
  Navbar as NavbarBootstrap,
  NavDropdown,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

export const Navbar = () => {
  const navigate = useNavigate();
  const { logout } = useAuth();
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
            <Nav.Link href="/ranking">Ranking</Nav.Link>
            <Nav.Link onClick={() => navigate('/mural')}>Mural</Nav.Link>
            <Nav.Link onClick={() => navigate('/boletim')}>Boletim</Nav.Link>

            <NavDropdown title="Opções" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Perfil</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Configurações
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item onClick={() => logout()}>
                Logout
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </NavbarBootstrap.Collapse>
      </Container>
    </NavbarBootstrap>
  );
};
