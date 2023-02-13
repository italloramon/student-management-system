import { Fragment, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const PublicRoute = ({ children, token }) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (token !== null) {
      navigate("/students");
    }
  }, []);

  return <Fragment>{children}</Fragment>;
};

export default PublicRoute;
