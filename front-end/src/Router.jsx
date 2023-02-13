import { Routes, Route } from "react-router-dom";
import { StudentsPage } from "./pages/Students";
import SelectedStudent from "./pages/SelectedStudent";
import Home from "./pages/Home";
import PageError from "./pages/404";
import PrivateRoute from "./components/Routes/PrivateRoutes";
import PublicRoute from "./components/Routes/PublicRoutes";
import { useAuth } from "./hooks/useAuth";

const Router = () => {
  const { token } = useAuth();
  return (
    <Routes>
      <Route path="*" element={<PageError />} />
      <Route
        path="/"
        element={
          <PublicRoute token={token}>
            <Home />
          </PublicRoute>
        }
      />
      <Route
        path="/students"
        element={
          <PrivateRoute token={token}>
            <StudentsPage></StudentsPage>
          </PrivateRoute>
        }
      ></Route>
      <Route
        path="/students/:id"
        element={<SelectedStudent></SelectedStudent>}
      ></Route>
    </Routes>
  );
};

export default Router;
