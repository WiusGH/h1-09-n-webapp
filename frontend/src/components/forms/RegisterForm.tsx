import { useEffect, useState } from "react";
import FormInput from "../inputs/FormInput";
import style from "./Form.module.css";
import GenericButton from "../buttons/GenericButton";
import { Link, useNavigate } from "react-router-dom";
import { isLoggedIn } from "../../utils/userStorage";
import axiosInstance from "../../api/axiosInstance";

const RegisterForm = () => {
  const [name, setName] = useState("");
  const [lastName, setLastNAme] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
  };

  async function handleRegister(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axiosInstance.post("/auth/register", {
        name,
        lastName,
        email,
        password,
      });
      console.log(response);
    } catch {
      setErrorMessage("Error al crear un nuevo usuario");
      console.error("Error creating user");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    if (isLoggedIn()) {
      navigate("/");
    }
  }, [navigate]);

  return (
    <section className={style.container}>
      {loading && (
        <div className={style.spinnerOverlay}>
          <div className={style.spinner}></div>
        </div>
      )}
      <h3>Inicio de sesión</h3>
      <form className={style.form} onSubmit={handleRegister}>
        <FormInput
          type="text"
          placeholder="Nombre"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <FormInput
          type="text"
          placeholder="Apellido"
          value={lastName}
          onChange={(e) => setLastNAme(e.target.value)}
        />
        <FormInput
          type="email"
          placeholder="Correo"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <FormInput
          type="password"
          placeholder="Contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <FormInput
          type="password"
          placeholder="Confirmar contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
          value={repeatPassword}
          onChange={(e) => setRepeatPassword(e.target.value)}
        />
        <GenericButton text="Iniciar sesión" submit />
      </form>
      <div className={style.link}>
        <Link to="/login">O inicia sesión</Link>
      </div>
      <span className={style.errorContainer}>
        {errorMessage && <p className={style.error}>{errorMessage}</p>}
      </span>
    </section>
  );
};

export default RegisterForm;
