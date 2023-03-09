import { Stack, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect, useMemo } from "react";
import { useAuth, useContact, useInfo } from "../../context";
import { api, formatDate } from "../../utils";
import { Alert, Table } from '../../components';

export const StudentNotices = () => {
  const [loading, setLoading] = useState(true);

  const info = useInfo();
  const auth = useAuth();
  const contact = useContact();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      const response = await contact.getAll();
      setLoading(false);
    };

    // getData();
  }, []);

  const create = () => {
    contact.handleBodyEdit(null);
    navigate("/form/contact");
  };

  const remove = async (event, id) => {
    event.target.disabled = true;
    const response = await contact.remove(id);
    event.target.disabled = false;
  };

  const edit = (data) => {
    contact.handleBodyEdit(data);
    navigate("/form/contact");
  };

  return (
    <section>
      <h1 className="fs-2">Home - Estudante {auth.username} Noticias</h1>
    </section>
  ) 
};
