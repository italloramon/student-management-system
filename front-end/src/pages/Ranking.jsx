import { useState, useEffect, Fragment } from "react";
import api from "../services/api";
import { Table, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { Title } from "../styles";

const Ranking = () => {
  const [data, setData] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const getRanking = async () => {
      const result = await api.get("/students/getRanking");
      setData(result.data);
      console.log(data);
    };
    getRanking();
  }, []);

  return (
    <Fragment>
      <Title style={{ textAlign: "center" }}>Ranking de Alunos</Title>
      <Table striped bordered hover variant="light" style={{ margin: "1em 0" }}>
        <thead>
          <tr>
            <th>Posição</th>
            <th>Matrícula</th>
            <th>Nome</th>
            <th>Pontuação</th>
          </tr>
        </thead>
        <tbody>
          {data &&
            data.map((item, index) => {
              return (
                <tr
                  key={item.id}
                  //onClick={() => navigate(`/students/${item.id}`)}
                >
                  <td>{index + 1}</td>
                  <td onClick={() => navigate(`/students/${item.id}`)}>
                    {item.id}
                  </td>
                  <td>{item.name}</td>

                  <td>{item.rankingStudent}</td>
                </tr>
              );
            })}
        </tbody>
      </Table>
    </Fragment>
  );
};

export default Ranking;
