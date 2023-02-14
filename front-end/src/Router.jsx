import { Routes, Route } from "react-router-dom";
import { AdminPage } from "./pages/admin/AdminPage";
import SelectedStudent from "./pages/admin/SelectedStudent";
import Home from "./pages/Home";
import PageError from "./pages/404";
import PrivateRoute from "./components/Routes/PrivateRoutes";
import PublicRoute from "./components/Routes/PublicRoutes";
import { useAuth } from "./hooks/useAuth";
import Ranking from "./pages/Ranking";
import Mural from "./pages/Mural";
import Boletim from './pages/Boletim';

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
            <AdminPage></AdminPage>
          </PrivateRoute>
        }
      ></Route>
      <Route
        path="/students/:id"
        element={<SelectedStudent></SelectedStudent>}
      ></Route>
      <Route path="/ranking" element={<Ranking />} />
      <Route path="/mural" element={<Mural />} />
      <Route path="/boletim" element={<Boletim />} />
    </Routes>
  );
};

export default Router;
