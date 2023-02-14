import { createContext, useState, useContext, useEffect } from "react";
export const DataContext = createContext({});
import api from "../services/api";

export const DataProvider = ({ children }) => {
  const [data, setData] = useState(null);
  const [id, setId] = useState(20230001);

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

    try {
      const response = await api.post("/students/", params);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const removeStudent = async (id) => {
    const params = {
      id: id,
    };
    try {
      const response = await api.delete(`/students/${id}`);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const updateStudent = async (data) => {
    const params = {
      email: data.email,
      cpf: data.cpf,
      name: data.name,
    };

    try {
      const response = await api.put(`/students/${data.id}`, params);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
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
        id
      }}
    >
      {children}
    </DataContext.Provider>
  );
};
