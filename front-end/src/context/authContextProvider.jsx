import { useState, useReducer } from "react";
import AuthContext from "./authContext";

const AuthContextProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [roles, setRoles] = useState(localStorage.getItem("roles")); // roles tipos : admin, teacher, student
  const [notas, setNotas] = useState(null);
  const isUserLogged = !!token;

  const loginHandler = (token, role, notas) => {
    setToken(token);
    setRoles(role);
    setNotas(notas);
    localStorage.setItem("token", token);
    localStorage.setItem("roles", role);
  };

  const logoutHandler = () => {
    console.log("foi");
    setToken(null);
    setRoles(null);
    localStorage.removeItem("roles");
    localStorage.removeItem("token");
  };

  const contextValue = {
    token,
    roles: roles,
    isUserLogged,
    login: loginHandler,
    logout: logoutHandler,
    notas
  };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

export default AuthContextProvider;
