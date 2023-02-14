import { Table } from "react-bootstrap";
import { useAuth } from "../hooks/useAuth";
import { useEffect, useState } from "react";
import api from "../services/api";

const Boletim = () => {
  const { token } = useAuth();
  const [notas, setNotas] = useState();

  useEffect(() => {
    const getNotas = async () => {
      const notaData = [];
      try {
        const response = await api.get(`/students/${token}`);
        console.log(response.data);
        setNotas(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getNotas();
  }, [token]);

  return (
    <center style={{ padding: "2em" }}>
      <h2>{`Boletim de ${notas?.name}`}</h2>
      <Table striped bordered hover variant="light" style={{ margin: "1em 0" }}>
        <thead>
          <tr>
            <th>Avaliação</th>
            <th>Inglês</th>
            <th>Geografia</th>
            <th>História</th>
            <th>Matemática</th>
            <th>Português</th>
          </tr>
        </thead>
        {notas && (
          <tbody>
            <tr>
              <td>AV1</td>
              <td>{notas.english.score1}</td>
              <td>{notas.geography.score1}</td>
              <td>{notas.history.score1}</td>
              <td>{notas.mathematics.score1}</td>
              <td>{notas.portuguese.score1}</td>
            </tr>
            <tr>
              <td>AV2</td>
              <td>{notas.english.score2}</td>
              <td>{notas.geography.score2}</td>
              <td>{notas.history.score2}</td>
              <td>{notas.mathematics.score2}</td>
              <td>{notas.portuguese.score2}</td>
            </tr>
            <tr>
              <td>AV3</td>
              <td>{notas.english.score3}</td>
              <td>{notas.geography.score3}</td>
              <td>{notas.history.score3}</td>
              <td>{notas.mathematics.score3}</td>
              <td>{notas.portuguese.score3}</td>
            </tr>
            <tr>
              <td>AV4</td>
              <td>{notas.english.score4}</td>
              <td>{notas.geography.score4}</td>
              <td>{notas.history.score4}</td>
              <td>{notas.mathematics.score4}</td>
              <td>{notas.portuguese.score4}</td>
            </tr>
          </tbody>
        )}
      </Table>
    </center>
  );
};

export default Boletim;
