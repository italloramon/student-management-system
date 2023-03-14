import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';

import * as Fetch from '../../functions';

export const StudentNotices = () => {
  const [loading, setLoading] = useState(true);
  const [notices, setNotices] = useState(null);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Student.getNotices();
      console.log(response);
      setLoading(false);
      setNotices(response);
    };

    getData();
  }, []);

  if (loading) return <h1>Está carregando...</h1>

  if (!notices) return <h1>Não há notícias</h1>

  return (
    <section>
      <h1 className="fs-2">Notícias - {auth.username}</h1>
      {notices.map(notice => {
        return <section className="py-3">
          <h3 className="fs-4 pb-2">Mensagem {notice.id}</h3>
          <p>{notice.text}</p>
        </section>
      })}
    </section>
  ) 
};
