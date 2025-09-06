import { useState, useEffect } from "react";
import FormInput from "../inputs/FormInput";
import style from "./Form.module.css";
import GenericButton from "../buttons/GenericButton";
import { Link, useNavigate } from "react-router-dom";
import { isLoggedIn } from "../../utils/userStorage";

import { saveUserData } from "../../utils/userStorage";
import axiosInstance from "../../api/axiosInstance";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    if (isLoggedIn()) {
      navigate("/");
    }
  }, [navigate]);

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
  };

  async function handleLogin(email: string, password: string) {
    setLoading(true);
    try {
      const { data } = await axiosInstance.post("/auth/login", {
        email,
        password,
      });

      const { user, token } = data.data;
      saveUserData({ ...user, token });
      navigate("/");
    } catch (error) {
      setErrorMessage("Correo o contraseña inválidos");
      console.error("Ingreso fallido", error);
      setTimeout(() => setErrorMessage(""), 5000);
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className={`${style.container} ${loading ? style.loading : ""}`}>
      {loading && (
        <div className={style.spinnerOverlay}>
          <div className={style.spinner}></div>
        </div>
      )}
      <h3>Inicio de sesión</h3>
      <form
        className={style.form}
        onSubmit={(e) => {
          e.preventDefault();
          handleLogin(email, password);
        }}
      >
        <FormInput
          type="email"
          placeholder="Correo"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <FormInput
          type="password"
          placeholder="Contraseña"
          value={password}
          showPassword={showPassword}
          onTogglePassword={togglePassword}
          onChange={(e) => setPassword(e.target.value)}
        />
        <GenericButton text="Iniciar sesión" submit />
      </form>
      <div className={style.link}>
        <Link to="/registro">O crea una cuenta</Link>
      </div>
      <span className={style.errorContainer}>
        {errorMessage && <p className={style.error}>{errorMessage}</p>}
      </span>
    </section>
  );
};

export default LoginForm;
