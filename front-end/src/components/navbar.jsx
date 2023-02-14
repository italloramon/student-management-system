import {
  Container,
  Nav,
  Navbar as NavbarBootstrap,
  NavDropdown,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";
import { Link } from "react-router-dom";

export const Navbar = () => {
  const navigate = useNavigate();
  const { roles } = useAuth();
  const { logout } = useAuth();
  return (
    <NavbarBootstrap
      bg="dark"
      variant="dark"
      expand="lg"
      style={{ padding: "1em 2.5em", justifyContent: "space-beetwenn" }}
    >
      <Container>
        <NavbarBootstrap.Brand>
          <Link to="/" style={{ textDecoration: "none", color: "white" }}>
            HOME
          </Link>
        </NavbarBootstrap.Brand>
        <NavbarBootstrap.Toggle aria-controls="basic-NavbarBootstrap-nav" />
        <NavbarBootstrap.Collapse id="basic-NavbarBootstrap-nav">
          <Nav
            className="me-auto"
            style={{
              display: "flex",
              flexDirection: "row",
              alignItems: "center",
              justifyContent: "center",
              gap: "15px",
            }}
          >
            {roles == "ADMIN" && (
              <Link
                to="/students"
                style={{
                  textDecoration: "none",
                  color: "darkgray",
                  textAlign: "center",
                }}
              >
                Admin Dashboard
              </Link>
            )}
            {roles == "TEACHER" && roles == "ADMIN" && (
              <Link
                style={{
                  textDecoration: "none",
                  color: "darkgray",
                  textAlign: "center",
                }}
              >
                Teachers
              </Link>
            )}
            <Link
              to="/ranking"
              style={{
                textDecoration: "none",
                color: "darkgray",
                textAlign: "center",
              }}
            >
              Ranking
            </Link>{" "}
            <Link
              to="/mural"
              style={{
                textDecoration: "none",
                color: "darkgray",
                textAlign: "center",
              }}
            >
              Mural
            </Link>
            {roles === "STUDENT" && (
              <Link
                to="/boletim"
                style={{
                  textDecoration: "none",
                  color: "darkgray",
                  textAlign: "center",
                }}
              >
                Boletim
              </Link>
            )}
            <NavDropdown title="Opções" id="basic-nav-dropdown">
              <NavDropdown.Item>Perfil</NavDropdown.Item>
              <NavDropdown.Item>Configurações</NavDropdown.Item>
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
