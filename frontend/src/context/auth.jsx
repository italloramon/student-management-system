import { createContext, useState, useContext, useEffect } from "react";
import { useInfo } from "./index";

import * as Functions from '../functions';

export const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [username, setUsername] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [role, setRole] = useState("");

  const [loading, setLoading] = useState(false);

  const info = useInfo();

  const login = async (data) => {
    const response = await Functions.Auth.login(data);
    console.log(response);
    setUsername(response.name);
    setToken(response.id);
    setRole(response.role);

    if (response.role === 'RESPONSABLE') setUsername(response.nameResponsable)
  };

  const logout = () => {
    setUsername("");
    setToken("");
  };

  return (
    <AuthContext.Provider
      value={{
        username,
        token,
        login,
        logout,
        role,
        loading,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
