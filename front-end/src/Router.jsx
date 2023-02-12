import { Home, PageError } from "./pages";
import { Routes, Route } from "react-router-dom";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="*" element={<PageError />} />
    </Routes>
  );
};

export default Router;
