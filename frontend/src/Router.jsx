import * as P from "./pages";
import { Routes, Route } from "react-router-dom";
import * as C from "./components";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<C.Wait />}>
        <Route
          element={<C.PrivateRoute role="user" redirectTo="/login" />}
        >
          <Route index element={<P.Home />} />

          <Route path="form/">
            <Route path="contact" element={<P.FormContact />} />
            <Route index element={<C.Redirect path="/" />} />
          </Route>

          <Route path="/logout" element={<P.Logout />} />
        </Route>

        <Route
          element={
            <C.PrivateRoute role="admin" redirectTo="/login" />
          }
        >
          <Route path="admin" element={<P.AdminHome />} />
        </Route>

        <Route element={<C.PrivateRoute role="STUDENT" redirectTo="/login" />}>
          <Route path="student" element={<P.StudentHome />} />
          <Route path="student/boletim" element={<P.StudentBoletim />} />
          <Route path="student/notices" element={<P.StudentNotices />} />
        </Route>

        <Route element={<C.PrivateRoute role="TEACHER" redirectTo="/login" />}>
          <Route path="teacher" element={<P.TeacherHome />} />
          <Route path="teacher/students" element={<P.TeacherStudents />} />
          <Route path="teacher/payroll" element={<P.TeacherPayroll />} />
          <Route path="teacher/notices" element={<P.TeacherNotices />} />
        </Route>

        <Route element={<C.PrivateRoute role="RESPONSABLE" redirectTo="/login" />}>
          <Route path="responsable" element={<P.ResponsableHome />} />
          <Route path="responsable/boletim" element={<P.ResponsableBoletim />} />
          <Route path="responsable/notices" element={<P.ResponsableNotices />} />
          <Route path="responsable/payment" element={<P.ResponsablePayment />} />
          <Route path="responsable/students" element={<P.ResponsableStudents />} />
        </Route>

        <Route element={<C.PublicRoute />}>
          <Route path="login" element={<P.Login />} />
          <Route path="register" element={<P.Register />} />
        </Route>
        <Route path="*" element={<P.PageError />} />
        <Route path="/unauthorized" element={<P.Unauthorized />} />
      </Route>
    </Routes>
  );
};

export default Router;
