import { createContext, useState, useContext, useEffect } from "react";

export const DataContext = createContext({});

import { useFetch } from '../hooks';
import { GET_ALL_STUDENTS, METHOD_GET } from '../utils';

export const DataProvider = ({ children }) => {
  const [data, setData] = useState(null);
  
  const [config, setConfig] = useState({ 
    url: '', 
    method: ''
  })

  const response = useFetch(config.url, config.method);
  
  const getAll = () => {
    setConfig({ 
      url: GET_ALL_STUDENTS, 
      method: METHOD_GET 
    });

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
