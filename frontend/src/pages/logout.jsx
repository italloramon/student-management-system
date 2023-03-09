import { useAuth } from "../context";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const Logout = () => {
  const auth = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    auth.logout();
  }, []);

  return <h1>Deslogando</h1>
};
