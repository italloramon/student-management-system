import { Button, Form as FormBootstrap } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../context";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

const studentSchema = yup.object({
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

const Form = ({ handleClose }) => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({ resolver: yupResolver(studentSchema) });
  const { addStudent } = useData();

  const onSubmit = async (data) => {
    addStudent(data);
    handleClose();
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
          placeholder="Digite o nome do responsável"
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
          placeholder="Digite o CPF do responsável"
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
          placeholder="Digite o E-mail do responsável"
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

export default Form;
