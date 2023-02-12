import Form from "../components/StudentForm";
import { Navbar, Modal } from "../components";
import { Title, Container } from "../styles";
import { Button, Table } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../context";

export const Home = () => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    const result = getAll();
    console.log(data);
  }, []);

  const { getAll, data, removeStudent } = useData();

  return (
    <>
      <Navbar />
      <Modal show={show} handleClose={handleClose} title="Adicionar aluno">
        <Form />
      </Modal>
      <Container>
        <Title>Home</Title>
        <Button variant="primary" type="submit" onClick={handleShow}>
          Adicionar
        </Button>
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
                  <tr key={item.id}>
                    <th>{item.id}</th>
                    <th>{item.name}</th>
                    <th>{item.cpf}</th>
                    <th>{item.email}</th>
                    <th>{item.responsable.nameResponsable}</th>
                    <th>
                      <Button onClick={() => removeStudent(item.id)}>
                        Delete
                      </Button>
                    </th>
                  </tr>
                );
              })}
          </tbody>
        </Table>
      </Container>
    </>
  );
};
