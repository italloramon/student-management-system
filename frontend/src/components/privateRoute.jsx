import { useNavigate, useHref, Outlet } from "react-router-dom";
import { useAuth, useContact } from "../context";
import { useEffect } from "react";
import { Layout } from "./layout";
import { Loading } from "./loading";

const items = [
  {
    role: "STUDENT",
    link: "/student",
  },
  {
    role: "admin",
    link: "/admin",
  },
  {
    role: "TEACHER",
    link: "/teacher"
  },
  {
    role: "RESPONSABLE",
    link: "/responsable"
  }
];

export const PrivateRoute = ({ role, redirectTo }) => {
  const navigate = useNavigate();
  const href = useHref();
  const auth = useAuth();

  console.log(role, auth.role)

  useEffect(() => {
    console.log(auth.token)
    if (!auth.token) navigate(redirectTo);
    else if (!auth.loading) {
      console.log(auth.role, items);
      const item = items.find((item) => item.role === auth.role);

      if (!auth.token || !auth.role) navigate(redirectTo);
      else if (href != item.link) navigate(item.link);
      else if (item.role !== auth.role) navigate("/unauthorized");
    }
  }, [auth.token, auth.loading]);

  return <Layout>{(!auth.loading && auth.token) ? <Outlet /> : <Loading />}</Layout>;
};
