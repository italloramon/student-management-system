import { Link } from "react-router-dom";

import { Button } from "react-bootstrap";
import { useAuth } from "../context";

import { Navbar } from './navbar';

import * as S from '../styles';

const items = [
  {
    link: "/student",
    name: "Home",
    private: true,
    role: "STUDENT"
  },
  {
    link: "/student/boletim",
    name: "Boletim",
    private: true,
    role: "STUDENT"
  },
  {
    link: "/student/notices",
    name: "Noticias",
    private: true,
    role: "STUDENT"
  },
  {
    link: "/student/ranking",
    name: "Ranking",
    private: true,
    role: "STUDENT"
  },
  {
    link: "/teacher",
    name: "Home",
    private: true,
    role: "TEACHER"
  },
  {
    link: "/teacher/ranking",
    name: "Ranking",
    private: true,
    role: "TEACHER"
  },

  {
    link: "/teacher/students",
    name: "Alunos",
    private: true,
    role: "TEACHER"
  },
  {
    link: "/teacher/payroll",
    name: "Pagamento",
    private: true,
    role: "TEACHER"
  },
  {
    link: "/teacher/notices",
    name: "Notícias",
    private: true,
    role: "TEACHER"
  },
  {
    link: "/responsable",
    name: "Home",
    private: true,
    role: "RESPONSABLE"
  },
  {
    link: "/responsable/students",
    name: "estudantes",
    private: true,
    role: "RESPONSABLE"
  },
  {
    link: "/responsable/boletim",
    name: "Boletim",
    private: true,
    role: "RESPONSABLE"
  },
  {
    link: "/responsable/notices",
    name: "Noticias",
    private: true,
    role: "RESPONSABLE"
  },
  {
    link: "/responsable/payment",
    name: "Pagamentos",
    private: true,
    role: "RESPONSABLE"
  },
  {
    link: "/login",
    name: "Login",
    private: false,
  },
  {
    link: "/register",
    name: "Registro",
    private: false,
  },
  {
    link: "/logout",
    name: "Sair",
    private: true,
  },
];

const withAuth = items.filter((item) => item.private === true);
const withoutAuth = items.filter((item) => item.private === false);

export const Layout = ({ children }) => {
  const auth = useAuth();

  return (
    <>
      <Navbar exist={auth.token} items={withAuth.filter(item => item.role === auth.role || item.link === "/logout")} />
      <S.Container>
        {children}
      </S.Container>
    </>
  );
};
