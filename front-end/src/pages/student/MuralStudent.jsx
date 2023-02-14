import api from "../../services/api";
import { FloatingLabel, Form, Button } from "react-bootstrap";
import { Title } from "../../styles";
import { useForm } from "react-hook-form";
import { muralSchema } from "../../utils/schemas";
import { yupResolver } from "@hookform/resolvers/yup";
import { useAuth } from "../../hooks/useAuth";
import { useEffect, useState } from "react";
import { Textarea } from "@chakra-ui/react";

const MuralStudent = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    const getData = async () => {
      const response = await api.get("/students/getNotices");
      console.log(response.data);
      setData(response.data);
    };
    getData();
  }, []);

  return (
    <section>
      {data && data.map((item, index) => <p key={index}>{item.text}</p>)}
    </section>
  );
};

export default MuralStudent;
