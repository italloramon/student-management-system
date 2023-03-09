import { createContext, useState, useContext, useEffect } from "react";
import * as Functions from "../functions";

import { useAuth, useInfo } from "./index";

export const StudentContext = createContext({});

export const StudentProvider = ({ children }) => {
  const [data, setData] = useState(null);
  const [bodyEdit, setBodyEdit] = useState(null);
  const [load, setLoad] = useState(false);

  const auth = useAuth();

  const info = useInfo();

  useEffect(() => {
    if (!auth.token) setData(null);
  }, [auth.token]);

  const create = async (data) => {
    // função de criar
  };

  const edit = async (data, id) => {
    // função de editar
  };

  const getAll = async () => {
    // função de pegar todos
  };

  const remove = async (id) => {
    // função de remover
  };

  const handleBodyEdit = (data) => setBodyEdit(data);

  return (
    <StudentContext.Provider
      value={{
        data,
        create,
        remove,
        getAll,
        handleBodyEdit,
        bodyEdit,
        edit,
        load,
      }}
    >
      {children}
    </StudentContext.Provider>
  );
};

export const useStudent = () => useContext(StudentContext);
