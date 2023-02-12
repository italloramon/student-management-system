import { Navbar, Modal } from '../components';
import { Title, Container } from '../styles';
import { Button, Form as FormBootstrap } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useData } from '../context';

const Form = () => {
  const { register, handleSubmit } = useForm();

  const onSubmit = data => console.log(data);


  return (
    <FormBootstrap onSubmit={handleSubmit(onSubmit)}>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Tipo</FormBootstrap.Label>
        <FormBootstrap.Select 
          aria-label="Default select example" 
          {...register('type')}
        >
          <option value="student">Estudante</option>
          <option value="teacher">Professor</option>
          <option value="parents">Responsável</option>
        </FormBootstrap.Select>
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Nome</FormBootstrap.Label>
        <FormBootstrap.Control 
          type="text" 
          placeholder="Digite seu nome" 
          {...register('name')} 
        />
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>CPF</FormBootstrap.Label>
        <FormBootstrap.Control 
          type="text" 
          placeholder="Digite seu CPF" 
          {...register('cpf')} 
        />
      </FormBootstrap.Group>
      <FormBootstrap.Group className="mb-3" controlId="FormBootstrapBasicEmail">
        <FormBootstrap.Label>Data de nascimento</FormBootstrap.Label>
        <FormBootstrap.Control 
          type="date" 
          {...register('date')} 
        />
      </FormBootstrap.Group>
      <Button variant="primary" type="submit">
        Enviar
      </Button>
    </FormBootstrap>
  )
}

export const Home = () => {

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);;

  const { getAll } = useData();

  useEffect(() => {
    const result = getAll();
    console.log(result);
  }, []);
  
  return (
    <>
      <Modal show={show} handleClose={handleClose} title="Adicionar aluno">
        <Form />
      </Modal>
      <Navbar />
      <Container>
        <Title>Home</Title>
        <Button variant="primary" onClick={handleShow}>Adicionar</Button>
      </Container>
    </>
  );
};
