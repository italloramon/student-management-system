import React from "react";
import ReactDOM from "react-dom/client";
import Router from "./Router";

import { BrowserRouter } from "react-router-dom";
import { ContextProvider } from "./context";

import 'bootstrap/dist/css/bootstrap.min.css';  

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
      <BrowserRouter>
        <ContextProvider>
          <Router />
        </ContextProvider>
      </BrowserRouter>
  </React.StrictMode>
);
