import { Stack, Button, Form, Spinner, Alert } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate, schemaNotice } from "../../utils";
import { Table, Input, TextArea } from '../../components';

import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from 'react-hook-form';

import * as Fetch from '../../functions';

export const TeacherNotices = () => {
  const {
    handleSubmit,
    register,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(schemaNotice),
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [show, setShow] = useState(false);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  const create = async (data) => {
    const response = await Fetch.Teacher.sendNotice(data);
    showMessage("Mensagem adicionada com sucesso [expirando em 4s]")
    reset();
  };

  const showMessage = msg => {
    setMessage(msg);
    setShow(true);

    setTimeout(() => {
      setShow(false);
      setMessage('');
    }, 4000)
  }

  return (
    <section>
      <Alert show={show} variant="success">{message}</Alert>
      <h1 className="fs-2 pb-4">Home - Professor {auth.username} Notícias</h1>
      <Form
        className="d-flex flex-column gap-2"
        onSubmit={handleSubmit(create)}
      >
        <TextArea
          title="Messagem"
          type="text"
          rows={4}
          errors={errors?.message}
          {...register("message")}
        />
        <Button
          variant="primary"
          type="submit"
          disabled={loading ? true : false}
        >
          {!loading && "Submit"}
          {loading && <Spinner animation="border" size="sm" />}
        </Button>
      </Form>
    </section>
  ) 
};
