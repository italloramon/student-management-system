import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';
import * as Fetch from '../../functions';

export const TeacherPayroll = () => {
  const [loading, setLoading] = useState(true);
  const [salary, setSalary] = useState(0);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Teacher.getSalary(auth.token);
      setLoading(false);
      setSalary(response);
    };

    getData();
  }, []);

  if (loading) return <h1>Está carregando...</h1>

  return (
    <section>
      <h1 className="fs-2 pb-3">Home - Professor {auth.username} Pagamento (Salário)</h1>
      <h2 className="fs-4">Salário: {salary}</h2>
    </section>
  ) 
};
