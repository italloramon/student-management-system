import api from "../../services/api";
import { useEffect, useState } from "react";
import { Title } from "../../styles";

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
      <Title style={{ padding: "1em" }}>Mural de Estudantes</Title>
      {data && data.map((item, index) => <p key={index}>{item.text}</p>)}
    </section>
  );
};

export default MuralStudent;
