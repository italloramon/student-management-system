import Form from "../../components/StudentForm";
import FormTeacher from "../../components/StudentTeacher";
import { Modal } from "../../components";
import { Title, Container } from "../../styles";
import { Button, Table } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../../hooks/useAdmin";
import { useNavigate } from "react-router-dom";
import api from "../../services/api";

export const AdminPage = () => {
  const [show, setShow] = useState(false);
  const [isUpdate, setIsUpdate] = useState(false);
  const [selectedId, setSelectedId] = useState(null);

  const handleClose = () => {
    setShow(false);
    setIsUpdate(false);
    setSelectedId(null);
  };
  const handleShow = () => setShow(true);

  const [showTeacher, setShowTeacher] = useState(false);
  const [isUpdateTeacher, setIsUpdateTeacher] = useState(false);
  const [selectedIdTeacher, setSelectedIdTeacher] = useState(null);

  const [teachers, setTeachers] = useState(null);

  const handleCloseTeacher = () => {
    setShowTeacher(false);
    setIsUpdateTeacher(false);
    setSelectedIdTeacher(null);
  };
  const handleShowTeacher = () => setShowTeacher(true);

  const navigate = useNavigate();

  useEffect(() => {
    const getTeachers = async () => {
      const response = await api.get("/teachers/");
      setTeachers(response.data);
    };
    getTeachers();
  }, []);

  const { getAll, data, removeStudent } = useData();

  return (
    <>
      <Container>
        <Title>Admin Dashboard</Title>
        <section style={{ display: "flex", gap: "1em" }}>
          <Button variant="primary" type="submit" onClick={handleShow}>
            Adicionar Estudante
          </Button>
          <Button variant="primary" type="submit" onClick={handleShowTeacher}>
            Adicionar professor
          </Button>
        </section>
        <Modal
          show={show}
          handleClose={handleClose}
          title={isUpdate ? "Alterar dados do Aluno" : "Adicionar aluno"}
        >
          <Form
            type={isUpdate ? "update" : "create"}
            id={selectedId}
            handleClose={handleClose}
          />
        </Modal>
        <Modal
          show={showTeacher}
          handleClose={handleCloseTeacher}
          title={isUpdateTeacher ? "Alterar dados do Aluno" : "Adicionar aluno"}
        >
          <FormTeacher
            type={"create"}
            id={selectedIdTeacher}
            handleClose={handleCloseTeacher}
          />
        </Modal>
        <center style={{ margin: "3em" }}>
          <h2>Alunos</h2>
        </center>
        <Table
          striped
          bordered
          hover
          variant="light"
          style={{ margin: "1em 0" }}
        >
          <thead>
            <tr>
              <th>Matr??cula</th>
              <th>Nome</th>
              <th>CPF</th>
              <th>Email</th>
              <th>Respons??vel</th>
            </tr>
          </thead>
          <tbody>
            {data &&
              data.map((item) => {
                return (
                  <tr
                    key={item.id}
                    //onClick={() => navigate(`/students/${item.id}`)}
                  >
                    <td>
                      <a onClick={() => navigate(`/students/${item.id}`)}>
                        {item.id}
                      </a>
                    </td>
                    <td>{item.name}</td>
                    <td>{item.cpf}</td>
                    <td>{item.email}</td>
                    <td>{item.responsable.nameResponsable}</td>
                    <td>
                      <Button onClick={() => removeStudent(item.id)}>
                        Delete
                      </Button>
                    </td>
                    <td>
                      <Button
                        onClick={() => {
                          setSelectedId(item.id);
                          setIsUpdate(true);
                          handleShow();
                        }}
                        variant="primary"
                      >
                        Update
                      </Button>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </Table>
        <center style={{ margin: "3em" }}>
          <h2>Professores</h2>
        </center>
        <Table
          striped
          bordered
          hover
          variant="light"
          style={{ margin: "1em 0" }}
        >
          <thead>
            <tr>
              <th>Matr??cula</th>
              <th>Nome</th>
              <th>CPF</th>
              <th>Email</th>
            </tr>
          </thead>
          <tbody>
            {teachers &&
              teachers.map((item) => {
                return (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.cpf}</td>
                    <td>{item.email}</td>
                    <td>
                      <Button onClick={() => removeStudent(item.id)}>
                        Delete
                      </Button>
                    </td>
                    <td>
                      <Button
                        onClick={() => {
                          setSelectedId(item.id);
                          setIsUpdate(true);
                          handleShow();
                        }}
                        variant="primary"
                      >
                        Update
                      </Button>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </Table>
      </Container>
    </>
  );
};
