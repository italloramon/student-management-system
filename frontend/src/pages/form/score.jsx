import { Button, Form, Stack, InputGroup, Alert } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useAuth, useContact, useInfo } from "../../context";
import { useState, useMemo } from "react";
import { api, schemaScore } from "../../utils";
import { Input, Select } from '../../components';

import * as Fetch from '../../functions';

export const FormScore = () => {
  const navigate = useNavigate();
  const auth = useAuth();
  const contact = useContact();
  const info = useInfo();

  const [searchParams] = useSearchParams();

  const {
    handleSubmit,
    register,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(schemaScore),
  });

  const submit = async (data) => {
    const idStudent = searchParams.get("idStudent");
    const idTeacher = searchParams.get("idTeacher");

    console.log(idStudent, idTeacher, data.bimester, data.score);

    Fetch.Student.sendScore(idStudent, idTeacher, data.bimester, data.score);

    navigate(-1);

    reset();
}
  return (
    <Stack className="col-md-5 col-10 mx-auto my-5" gap={4}>
      <h1 className="fs-2 text-center">
        Adicionar Nota
      </h1>
      <Form
        className="d-flex flex-column gap-2"
        onSubmit={handleSubmit(submit)}
      >
        <Input
          title="Nota"
          type="number"
          errors={errors?.score}
          {...register("score")}
          step="any"
          max="10"
        />
        <Select {...register("bimester")} title="Bimestre">
          <option value={1}>1</option>
          <option value={2}>2</option>
          <option value={3}>3</option>
          <option value={4}>4</option>
        </Select>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </Stack>
  );
};
