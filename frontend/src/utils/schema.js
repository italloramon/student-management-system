import * as yup from 'yup';

const nameRegex = /^[A-Z]+(([a-zA-Z ])?[a-zA-Z]*)*$/g

export const schemaAccount = yup.object().shape({
  username: yup.string().required("username é necessário"),
  password: yup.string().required("senha é necessária"),
});

export const schemaContact = yup.object().shape({
  name: yup.string().required("nome é necessário").matches(nameRegex, "A primeira letra deve ser maiúscula"),
  email: yup.string().email("email inválido").required("email é necessário"),
});

export const schemaNotice = yup.object().shape({
  message: yup.string().required("Messagem é necessário")
});

export const schemaScore = yup.object().shape({
  score: yup
    .number()
    .typeError("A pontuação é obrigatória")
    .required("A pontuação é obrigatória")
    .max(10, "Pontuação máxima é 10")
    .min(0, "Pontuação mínima é 0")
})