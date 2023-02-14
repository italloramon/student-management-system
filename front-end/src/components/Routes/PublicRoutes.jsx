import { Fragment, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const PublicRoute = ({ children, token }) => {
  // evitar que o usuario vá para página de login ja estando logado
  const navigate = useNavigate();

  useEffect(() => {
    if (token !== null) {
      navigate("/");
    } else if (token === null) {
      navigate("/login");
    }
  }, [token]);

  return <Fragment>{children}</Fragment>;
};

export default PublicRoute;
