import { Navbar, Modal } from "../components";
import { Title, Container } from "../styles";
import { Button, Form as FormBootstrap, Table } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../context";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import api from "../services/api";

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
  email: yup.string().email("E-mail inválido").required("Campo obrigatório."),
  emailResponsable: yup
    .string()
    .email("E-mail inválido")
    .required("Campo obrigatório."),
  cpfResponsable: yup
    .string()
    .max(11, "CPF inválido.")
    .min(11, "CPF inválido.")
    .required("Campo obrigatório."),
  responsable: yup
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

  const onSubmit = async (data) => {
    const params = {
      email: data.email,
      cpf: data.cpf,
      name: data.name,
      nameResponsable: data.responsable,
      cpfResponsable: data.cpfResponsable,
      emailResponsable: data.emailResponsable,
    };

    const response = await api.post("/students/", params);
  };

  return (
    <FormBootstrap onSubmit={handleSubmit(onSubmit)}>
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
        <FormBootstrap.Label>E-mail do aluno:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o E-mail do aluno"
          {...register("email")}
        />
        <span className="text-danger mt-10px">{errors?.email?.message}</span>
      </FormBootstrap.Group>

      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Nome do responsável:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o nome do responsável do aluno"
          {...register("responsable")}
        />
        <span className="text-danger mt-10px">
          {errors?.responsable?.message}
        </span>
      </FormBootstrap.Group>

      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>CPF do responsável:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o E-mail do aluno"
          {...register("cpfResponsable")}
        />
        <span className="text-danger mt-10px">
          {errors?.cpfResponsable?.message}
        </span>
      </FormBootstrap.Group>

      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>E-mail do responsável:</FormBootstrap.Label>
        <FormBootstrap.Control
          type="text"
          placeholder="Digite o E-mail do aluno"
          {...register("emailResponsable")}
        />
        <span className="text-danger mt-10px">
          {errors?.emailResponsable?.message}
        </span>
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
    console.log(data);
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
                  </tr>
                );
              })}
          </tbody>
        </Table>
      </Container>
    </>
  );
};
