import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import * as Fetch from '../../functions';
import { Alert, Table } from '../../components';

export const StudentRanking = () => {
  const [loading, setLoading] = useState(true);
  const [ranking, setRanking] = useState(null);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Student.getRanking();
      setLoading(false);
      setRanking(response);

      console.log(response);
    };

    getData();
  }, []);

  if (loading) return <h1>Está carregando...</h1>

  return (
    <section>
      <h1 className="fs-2 pb-4">Ranking - Estudante {auth.username} Boletim</h1>
      <Table data={ranking} columns={["posição", "nome", "pontuação"]}>
        {ranking && ranking.map((student, index) => {
          return <tr key={index}>
            <td>{index+1}</td>
            <td>{student.name}</td>
            <td>{student.rankingStudent}</td>
          </tr>
        })}
      </Table>
    </section>
  ) 
};
