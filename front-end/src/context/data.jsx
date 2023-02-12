import { createContext, useState, useContext, useEffect } from "react";

export const DataContext = createContext({});

import { useFetch } from '../hooks';
import { GET_ALL_STUDENTS, METHOD_GET } from '../utils';
import api from '../services/api';

export const DataProvider = ({ children }) => {
  const [data, setData] = useState(null);
  
  const getAll = async() => {
    const result = await api.get('/students/');
    setData(result.data);
  }

  return (
    <DataContext.Provider
      value={{
        getAll,
        data
      }}
    >
      {children}
    </DataContext.Provider>
  );
};

export const useData = () => useContext(DataContext);
