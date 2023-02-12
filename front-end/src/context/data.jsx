import { createContext, useState, useContext, useEffect } from "react";

export const DataContext = createContext({});

import { useFetch } from '../hooks';
import { GET_ALL_STUDENTS } from '../utils';

export const DataProvider = ({ children }) => {
  const [data, setData] = useState(null);

  const response = useFetch(GET_ALL_STUDENTS, 'get');
  
  const getAll = () => {
    console.log(response);
  }

  return (
    <DataContext.Provider
      value={{
        getAll
      }}
    >
      {children}
    </DataContext.Provider>
  );
};

export const useData = () => useContext(DataContext);
