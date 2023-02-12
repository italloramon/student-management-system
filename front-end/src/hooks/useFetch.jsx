import { useState, useEffect } from "react";
import axios from "axios";

export const useFetch = (url, method, body = null, headers = null) => {
  const [response, setResponse] = useState(null);
  const [error, setError] = useState("");
  const [loading, setloading] = useState(true);

  useEffect(() => {
    const fetchData = () => {
      axios({
        url,
        method,
        headers,
        body,
      })
        .then((res) => {
          setResponse(res.data);
        })
        .catch((err) => {
          setError(err);
        })
        .finally(() => {
          setloading(false);
        });
    };
    fetchData();
  }, [method, url, body, headers]);

  return { response, error, loading };
};
