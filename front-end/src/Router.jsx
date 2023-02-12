import { Routes, Route } from "react-router-dom";
import { StudentsPage } from "./pages/Students";
import SelectedStudent from "./pages/SelectedStudent";
import Home from "./pages/Home";
import PageError from "./pages/404";
const Router = () => {
  return (
    <Routes>
      <Route path="*" element={<PageError />} />
      <Route path="/" element={<Home />} />
      <Route path="/students" element={<StudentsPage></StudentsPage>}></Route>
      <Route
        path="/students/:id"
        element={<SelectedStudent></SelectedStudent>}
      ></Route>
    </Routes>
  );
};

export default Router;
