import { useState, useReducer } from "react";
import AuthContext from "./authContext";

const AuthContextProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [roles, setRoles] = useState("teacher");
  const isUserLogged = !!token;

  const loginHandler = (token) => {
    setToken(token);
    localStorage.setItem("token", token);
  };

  const logoutHandler = () => {
    console.log("foi");
    setToken(null);
    localStorage.removeItem("token");
  };

  const contextValue = {
    token,
    roles,
    isUserLogged,
    login: loginHandler,
    logout: logoutHandler,
  };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

export default AuthContextProvider;
