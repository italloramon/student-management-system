import React from "react";
import ReactDOM from "react-dom/client";
import Router from "./Router";
import { Navbar } from "./components";
import { BrowserRouter } from "react-router-dom";
import { DataProvider } from "./context/dataContext";
import AuthContextProvider from "./context/authContextProvider";
import { ChakraProvider } from "@chakra-ui/react";
import "bootstrap/dist/css/bootstrap.min.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <AuthContextProvider>
      <BrowserRouter>
        <DataProvider>
          <Navbar></Navbar>
          <Router />
        </DataProvider>
      </BrowserRouter>
    </AuthContextProvider>
  </React.StrictMode>
);
