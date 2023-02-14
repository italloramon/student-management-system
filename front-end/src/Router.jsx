import { Routes, Route } from "react-router-dom";
import { AdminPage } from "./pages/admin/AdminPage";
import SelectedStudent from "./pages/admin/SelectedStudent";
import LoginPage from "./pages/LoginPage";
import PageError from "./pages/404";
import PrivateRoute from "./components/Routes/PrivateRoutes";
import PublicRoute from "./components/Routes/PublicRoutes";
import { useAuth } from "./hooks/useAuth";
import Ranking from "./pages/Ranking";
import MuralTeacher from "./pages/teachers/Mural";
import MuralStudent from "./pages/student/MuralStudent";
import Boletim from "./pages/Boletim";
import Homepage from "./pages/Homepage";

const Router = () => {
  const { token, roles } = useAuth();

  return (
    <Routes>
      <Route path="*" element={<PageError />} />
      <Route
        path="/login"
        element={
          <PublicRoute token={token}>
            <LoginPage />
          </PublicRoute>
        }
      />
      <Route
        path="/"
        element={
          <PublicRoute token={token}>
            <Homepage />
          </PublicRoute>
        }
      />
      <Route path="/404" element={<PageError />} />
      <Route
        path="/students"
        element={
          <PrivateRoute canAcess="ADMIN">
            <AdminPage></AdminPage>
          </PrivateRoute>
        }
      ></Route>
      <Route
        path="/students/:id"
        element={
          <PrivateRoute canAcess="ADMIN">
            <SelectedStudent></SelectedStudent>
          </PrivateRoute>
        }
      ></Route>
      <Route
        path="/ranking"
        element={
          <PrivateRoute>
            <Ranking />
          </PrivateRoute>
        }
      />
      <Route
        path="/mural"
        element={
          roles == "TEACHER" ? (
            <PrivateRoute>
              <MuralTeacher />
            </PrivateRoute>
          ) : (
            <PrivateRoute>
              <MuralStudent></MuralStudent>
            </PrivateRoute>
          )
        }
      />
      <Route
        path="/boletim"
        element={
          <PrivateRoute>
            <Boletim />
          </PrivateRoute>
        }
      />
    </Routes>
  );
};

export default Router;
