import { Button, Modal as ModalBootstrap, Form as FormBootstrap } from 'react-bootstrap';

export const Modal = ({ show, handleClose, title, children }) => {
  return (
    <ModalBootstrap show={show} onHide={handleClose}>
        <ModalBootstrap.Header closeButton>
          <ModalBootstrap.Title>{title}</ModalBootstrap.Title>
        </ModalBootstrap.Header>
        <ModalBootstrap.Body>
          {children}
        </ModalBootstrap.Body>
    </ModalBootstrap>
  );
};

