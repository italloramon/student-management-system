import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';
import * as Fetch from '../../functions';

export const ResponsablePayment = () => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await Fetch.Responsable.getPayment(auth.token);

      
      
      setData(response);
      setLoading(false);
    };

    getData();
  }, []);

  if (loading) return <h1>Está carregando...</h1>
  if (!data) return <h1>Não existe</h1>

  return (
    <section>
      <h1 className="fs-2">Responsável - Pagamento</h1>
      <Table data={data} columns={["Nome", "Valor"]}>
        {data && data.map((item, index) => {
          return <tr key={index}>
            <td>{item.name}</td>
            <td>R$ {item.value}</td>
          </tr>
        })}
      </Table>
      <h1 className="fs-5">Total: R$ {data.reduce((sum, item) => sum+=item.value, 0)}</h1>
    </section>
  ) 
};
