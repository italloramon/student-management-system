import { Fragment, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

const PrivateRoute = ({ children, canAcess }) => {
  // evitar que o usuario entre é uma rota sem role ou entre em uma rota sem esta logado
  const navigate = useNavigate();
  const { roles, token } = useAuth();

  useEffect(() => {
    if (token == null) {
      navigate("/login");
    } else if (canAcess !== roles && canAcess !== null) {
      navigate("/home");
    }
  }, [token]);

  if (token === null) {
    return <h2>Loading</h2>;
  } else {
    return <Fragment>{children}</Fragment>;
  }
};

export default PrivateRoute;
