import { createContext, useState, useContext, useEffect } from "react";
export const DataContext = createContext({});
import api from "../services/api";

export const DataProvider = ({ children }) => {
  const [data, setData] = useState(null);

  const getAll = async () => {
    const result = await api.get("/students/");
    setData(result.data);
  };

  const addStudent = async (data) => {
    const params = {
      email: data.email,
      cpf: data.cpf,
      name: data.name,
      nameResponsable: data.responsable,
      cpfResponsable: data.cpfResponsable,
      emailResponsable: data.emailResponsable,
    };
    const response = await api.post("/students/", params);
    console.log(response);
  };

  const removeStudent = async (id) => {
    const params = {
      id: id,
    };
    console.log(params);
    const response = await api.delete(`/students/${id}`);
    console.log(response);
  };

  const updateStudent = async (data) => {
    const params = {
      email: data.email,
      cpf: data.cpf,
      name: data.name,
    };
    console.log(params);
    const response = await api.put(`/students/${data.id}`, params);
    console.log(response);
  };

  useEffect(() => getAll, [addStudent, removeStudent]);

  return (
    <DataContext.Provider
      value={{
        getAll,
        data,
        addStudent,
        removeStudent,
        updateStudent,
      }}
    >
      {children}
    </DataContext.Provider>
  );
};

export const useData = () => useContext(DataContext);
