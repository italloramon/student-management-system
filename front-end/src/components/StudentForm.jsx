import { Button, Form as FormBootstrap } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useData } from "../hooks/useData";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { studentSchema, updateStudentSchema } from "../utils/schemas";

const Form = ({ type, handleClose, id }) => {
  const { addStudent, updateStudent } = useData();

  let configs = {
    // caso seja create student
    func: addStudent,
    button_label: "Enviar",
    schema: studentSchema,
  };

  if (type == "update") {
    configs = {
      func: updateStudent,
      button_label: "Update",
      schema: updateStudentSchema,
    };
  }

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({ resolver: yupResolver(configs.schema) });

  const onSubmit = async (data) => {
    if (type == "update") {
      data["id"] = id;
    }
    configs.func(data);
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

      {type == "create" && (
        <div>
          {" "}
          <FormBootstrap.Group
            className="mb-3"
            controlId="FormBootstrapBasicEmail"
          >
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
          <FormBootstrap.Group
            className="mb-3"
            controlId="FormBootstrapBasicEmail"
          >
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
          <FormBootstrap.Group
            className="mb-3"
            controlId="FormBootstrapBasicEmail"
          >
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
        </div>
      )}

      <Button variant="primary" type="submit">
        {configs.button_label}
      </Button>
    </FormBootstrap>
  );
};

export default Form;
