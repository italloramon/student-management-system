import React from "react";

const AuthContext = React.createContext({
  token: "" | null,
  type: "",
  isUserLogged: false | true,
  login: (token) => {},
  logout: () => {},
});

export default AuthContext;
