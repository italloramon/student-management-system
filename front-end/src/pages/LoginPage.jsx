import classes from "../styles/home.module.css";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { loginSchema } from "../utils/schemas";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const LoginPage = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({ resolver: yupResolver(loginSchema) });

  const navigate = useNavigate();
  const [createAccount, setCreateAccount] = useState(false);
  const [loginError, setLoginError] = useState(false);
  const { login } = useAuth();

  const onSubmit = async (data) => {
    const params = {
      login: data.email,
      password: data.password,
    };

    try {
      const response = await api.post("/students/login", params);
      console.log(response);
      login(response.data.id);
      navigate("/");
    } catch (error) {
      console.log(error);
      setLoginError(true);
    }
  };
  return (
    <section className="vh-100">
      <form
        className={classes.gradient_custom}
        onSubmit={handleSubmit(onSubmit)}
      >
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-8 col-lg-6 col-xl-5">
              <div className="card bg-dark text-white">
                <div className="card-body p-5 text-center">
                  <div className="mb-md-5 mt-md-4 pb-5">
                    <h2 className="fw-bold mb-5 text-uppercase">
                      {!createAccount ? "Login" : "Criar Conta"}
                    </h2>
                    {loginError && <p>ocorreu algum erro</p>}
                    <div className="form-outline form-white mb-4">
                      <label className="form-label">Email</label>
                      <input
                        type="email"
                        id="typeEmailX"
                        className="form-control form-control-lg"
                        {...register("email")}
                      />
                      <span className="text-danger mt-10px">
                        {errors?.email?.message}
                      </span>
                    </div>

                    <div className="form-outline form-white mb-4">
                      <label className="form-label">Senha</label>
                      <input
                        type="password"
                        id="typePasswordX"
                        className="form-control form-control-lg"
                        {...register("password")}
                      />
                      <span className="text-danger m-10">
                        {errors?.password?.message}
                      </span>
                    </div>

                    {!createAccount && (
                      <p className="small mb-5 pb-lg-2">
                        <Link className="text-white-50" href="#!">
                          Esqueceu a senha?
                        </Link>
                      </p>
                    )}

                    <button
                      className="btn btn-outline-light btn-lg px-5"
                      type="submit"
                      onClick={onSubmit}
                    >
                      {!createAccount ? "Login" : "Criar"}
                    </button>

                    <div className="d-flex justify-content-center text-center pt-1">
                      <Link className="text-white">
                        <i className="fab fa-facebook-f fa-lg"></i>
                      </Link>
                      <Link className="text-white">
                        <i className="fab fa-twitter fa-lg mx-4 px-2"></i>
                      </Link>
                      <Link className="text-white">
                        <i className="fab fa-google fa-lg"></i>
                      </Link>
                    </div>
                  </div>

                  <div>
                    <p className="mb-0">
                      {!createAccount
                        ? "Não possui uma conta?"
                        : "Possui uma conta?"}
                    </p>
                    <Link
                      className="text-white-50 fw-bold"
                      onClick={() => setCreateAccount(!createAccount)}
                    >
                      {!createAccount ? "Criar conta" : "Logar "}
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </section>
  );
};

export default LoginPage;
