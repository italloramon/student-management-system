import Form from "../components/StudentForm";
import { Navbar, Modal } from "../components";
import { Title, Container } from "../styles";
import { Button, Table } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../context";
import { useNavigate } from "react-router-dom";

export const StudentsPage = () => {
  const [show, setShow] = useState(false);
  const [isUpdate, setIsUpdate] = useState(false);
  const [selectedId, setSelectedId] = useState(null);
  const handleClose = () => {
    setShow(false);
    setIsUpdate(false);
    setSelectedId(null);
  };
  const handleShow = () => setShow(true);

  const navigate = useNavigate();

  useEffect(() => {
    const result = getAll();
    console.log(data);
  }, []);

  const { getAll, data, removeStudent } = useData();

  return (
    <>
      <Container>
        <Title>Students Page</Title>
        <Button variant="primary" type="submit" onClick={handleShow}>
          Adicionar Estudante
        </Button>
        <Modal
          show={show}
          handleClose={handleClose}
          title={isUpdate ? "Alterar dados do Aluno" : "Adicionar aluno"}
        >
          <Form type={isUpdate ? "update" : "create"} id={selectedId} />
        </Modal>
        <Table
          striped
          bordered
          hover
          variant="light"
          style={{ margin: "1em 0" }}
        >
          <thead>
            <tr>
              <th>Matrícula</th>
              <th>Nome</th>
              <th>CPF</th>
              <th>Email</th>
              <th>Responsável</th>
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
                    <td>{item.id}</td>
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
      </Container>
    </>
  );
};
