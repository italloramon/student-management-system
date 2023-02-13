import { Fragment, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const PrivateRoute = ({ children, token }) => {
  const navigate = useNavigate();
  console.log(token);
  useEffect(() => {
    if (token == null) {
      navigate("/");
    }
  }, [token]);

  return <Fragment>{children}</Fragment>;
};

export default PrivateRoute;
