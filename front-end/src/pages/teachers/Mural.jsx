import api from "../../services/api";
import { FloatingLabel, Form, Button } from "react-bootstrap";
import { Title } from "../../styles";
import { useForm } from "react-hook-form";
import { muralSchema } from "../../utils/schemas";
import { yupResolver } from "@hookform/resolvers/yup";
import { useAuth } from "../../hooks/useAuth";
import { useEffect, useState } from "react";
import { Textarea } from "@chakra-ui/react";

const MuralTeacher = () => {
  const { roles } = useAuth();

  const {
    register,
    handleSubmit,
    watch,
    reset,

    formState: { errors },
  } = useForm({ resolver: yupResolver(muralSchema) });

  console.log(roles);

  const onSubmit = async (data) => {
    console.log(data);
    const params = {
      text: data.mensagem,
    };

    const response = await api.post("/teachers/sendNotice", params);
    console.log(response);

    reset();
  };
  return roles !== "student" ? (
    <form
      style={{
        margin: "1em",
        display: "flex",
        flexDirection: "column",
        gap: "1em",
      }}
      onSubmit={handleSubmit(onSubmit)}
    >
      <Title>Mural de avisos</Title>
      <FloatingLabel controlId="floatingTextarea2" label="Aviso:">
        <Form.Control
          as="textarea"
          placeholder="Leave a comment here"
          style={{ height: "120px" }}
          {...register("mensagem")}
        />
      </FloatingLabel>
      <span className="text-danger mt-10px">{errors?.mensagem?.message}</span>
      <Button type="submit">Enviar</Button>
    </form>
  ) : (
    <MuralStudent />
  );
};

export default MuralTeacher;
