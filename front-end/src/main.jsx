import React from "react";
import ReactDOM from "react-dom/client";
import Router from "./Router";
import { Navbar } from "./components";
import { BrowserRouter } from "react-router-dom";
import { ContextProvider } from "./context";

import "bootstrap/dist/css/bootstrap.min.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <ContextProvider>
        <Navbar></Navbar>
        <Router />
      </ContextProvider>
    </BrowserRouter>
  </React.StrictMode>
);
