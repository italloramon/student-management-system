import { Navbar, Modal } from "../components";
import { Title, Container } from "../styles";
import { Button, Form as FormBootstrap, Table } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../context";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

const userSchema = yup.object({
  name: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
  cpf: yup
    .string()
    .max(11, "CPF inválido.")
    .min(11, "CPF inválido.")
    .required("Campo obrigatório."),
  responsavel: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
});

const Form = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({ resolver: yupResolver(userSchema) });

  const onSubmit = (data) => console.log(data);

  return (
    <FormBootstrap onSubmit={handleSubmit(onSubmit)}>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Tipo</FormBootstrap.Label>
        <FormBootstrap.Select
          aria-label="Default select example"
          {...register("type")}
        >
          <option value="student">Estudante</option>
          <option value="teacher">Professor</option>
          <option value="parents">Responsável</option>
        </FormBootstrap.Select>
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Nome do aluno:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o nome do aluno"
          {...register("name")}
        />
        <span className="text-danger mt-10px">{errors?.name?.message}</span>
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>CPF do aluno:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o CPF do aluno"
          {...register("cpf")}
        />
        <span className="text-danger mt-10px">{errors?.cpf?.message}</span>
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Nome do responsável:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o nome do responsável do aluno"
          {...register("responsavel")}
        />
        <span className="text-danger mt-10px">
          {errors?.responsavel?.message}
        </span>
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Data de nascimento</FormBootstrap.Label>
        <FormBootstrap.Control type="date" {...register("date")} />
      </FormBootstrap.Group>
      <Button variant="primary" type="submit">
        Enviar
      </Button>
    </FormBootstrap>
  );
};

export const Home = () => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    const result = getAll();
  }, []);

  const { getAll, data } = useData();

  return (
    <>
      <Modal show={show} handleClose={handleClose} title="Adicionar aluno">
        <Form />
      </Modal>
      <Navbar />
      <Container>
        <Title>Home</Title>
        <Button variant="primary" type="submit" onClick={handleShow}>
          Adicionar
        </Button>
        <Table striped bordered hover variant="dark" style={{ margin: '1em 0' }}>
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
            {data && data.map(item => {
              return (
                <tr key={item.id}>
                  <th>{item.id}</th>
                  <th>{item.name}</th>
                  <th>{item.cpf}</th>
                  <th>{item.email}</th>
                  <th>{item.responsable.name}</th>
                </tr>
              )
            })}
          </tbody>
        </Table>
      </Container>
    </>
  );
};
