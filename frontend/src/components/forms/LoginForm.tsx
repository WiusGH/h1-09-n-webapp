import { useState } from "react";
import FormInput from "../inputs/FormInput";
import style from "./Form.module.css";
import GenericButton from "../buttons/GenericButton";
import { Link } from "react-router-dom";
const LoginForm = () => {
  const [showPassword, setShowPassword] = useState(false);

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
  };
  return (
    <section className={style.container}>
      <h3>Inicio de sesión</h3>
      <form className={style.form}>
        <FormInput type="email" placeholder="Correo" />
        <FormInput
          type="password"
          placeholder="Contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
        />
        <GenericButton text="Iniciar sesión" submit />
      </form>
      <div className={style.link}>
        <Link to="/registro">O crea una cuenta</Link>
      </div>
    </section>
  );
};

export default LoginForm;
