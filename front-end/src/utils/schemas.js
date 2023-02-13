import * as yup from "yup";

export const studentSchema = yup.object({
  name: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
  cpf: yup
    .string()
    .max(11, "CPF inválido.")
    .min(11, "CPF inválido.")
    .required("Campo obrigatório."),
  email: yup.string().email("E-mail inválido").required("Campo obrigatório."),
  emailResponsable: yup
    .string()
    .email("E-mail inválido")
    .required("Campo obrigatório."),
  cpfResponsable: yup
    .string()
    .max(11, "CPF inválido.")
    .min(11, "CPF inválido.")
    .required("Campo obrigatório."),
  responsable: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
});

export const updateStudentSchema = yup.object({
  name: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
  cpf: yup
    .string()
    .max(11, "CPF inválido.")
    .min(11, "CPF inválido.")
    .required("Campo obrigatório."),
  email: yup.string().email("E-mail inválido").required("Campo obrigatório."),
});

export const loginSchema = yup.object({
  email: yup.string().email("E-mail inválido").required("Campo obrigatório."),
  password: yup
    .string()
    .max(30, "Limite máximo de 30 caracteres.")
    .required("Campo obrigatório."),
});

export const muralSchema = yup.object({
  mensagem: yup.string().required("Campo obrigatório."),
});
