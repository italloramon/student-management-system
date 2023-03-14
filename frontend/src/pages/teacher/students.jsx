import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';
import * as Fetch from '../../functions';

export const TeacherStudents = () => {
  const [loading, setLoading] = useState(true);
  const [students, setStudents] = useState(null);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Student.getAll();
      setStudents(response);
      setLoading(false);
    };

    getData();
  }, []);

  if (loading) return <h1>Está carregando...</h1>

  return (
    <section>
      <h1 className="fs-2">Home - Professor {auth.username} Estudantes</h1>
      {students && <Table data={students} columns={["id", "nome", "email", "cpf"]}>
        {students && students.map((student, index) => {
          return <tr key={index}>
            <td>{student.id}</td>
            <td>{student.name}</td>
            <td>{student.email}</td>
            <td>{student.cpf}</td>
            <td>
              <Button as={Link} to={`/teacher/form/score?idStudent=${student.id}&idTeacher=${auth.token}`} variant="primary">Adicionar nota</Button>
            </td>
          </tr>
        })}
      </Table>}
    </section>
  ) 
};
