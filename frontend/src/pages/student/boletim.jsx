import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';
import * as Fetch from '../../functions';

export const StudentBoletim = () => {
  const [loading, setLoading] = useState(true);
  const [boletim, setBoletim] = useState(null);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Student.getBoletim(auth.token);
      setLoading(false);
      setBoletim(response);
    };

    getData();
  }, []);


  return (
    <section>
      <h1 className="fs-2 pb-4">Estudante {auth.username} Boletim</h1>

      {boletim && <Table data={boletim} columns={["Disciplina", "AB1", "AB2", "AB3", "AB4", "Total"]}>
        <tr>
          <td>Inglês</td>
          <td>{boletim.english.score1}</td>
          <td>{boletim.english.score2}</td>
          <td>{boletim.english.score3}</td>
          <td>{boletim.english.score4}</td>
          <td>{boletim.english.scores}</td>
        </tr>
        <tr>
          <td>Geografia</td>
          <td>{boletim.geography.score1}</td>
          <td>{boletim.geography.score2}</td>
          <td>{boletim.geography.score3}</td>
          <td>{boletim.geography.score4}</td>
          <td>{boletim.geography.scores}</td>
        </tr>
        <tr>
          <td>História</td>
          <td>{boletim.history.score1}</td>
          <td>{boletim.history.score2}</td>
          <td>{boletim.history.score3}</td>
          <td>{boletim.history.score4}</td>
          <td>{boletim.history.scores}</td>
        </tr>
        <tr>
          <td>Matemática</td>
          <td>{boletim.mathematics.score1}</td>
          <td>{boletim.mathematics.score2}</td>
          <td>{boletim.mathematics.score3}</td>
          <td>{boletim.mathematics.score4}</td>
          <td>{boletim.mathematics.scores}</td>
        </tr>
        <tr>
          <td>Português</td>
          <td>{boletim.portuguese.score1}</td>
          <td>{boletim.portuguese.score2}</td>
          <td>{boletim.portuguese.score3}</td>
          <td>{boletim.portuguese.score4}</td>
          <td>{boletim.portuguese.scores}</td>
        </tr>
      </Table>}
    </section>
  ) 
};
