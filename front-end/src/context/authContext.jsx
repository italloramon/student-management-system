import React from "react";

const AuthContext = React.createContext({
  token: "" | null,
  isUserLogged: false | true,
  login: (token) => {},
  logout: () => {},
});

export default AuthContext;
