import { useState } from "react";
import FormInput from "../inputs/FormInput";
import style from "./Form.module.css";
import GenericButton from "../buttons/GenericButton";
import { Link } from "react-router-dom";

const Register = () => {
  const [showPassword, setShowPassword] = useState(false);

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
  };
  return (
    <section className={style.container}>
      <h3>Inicio de sesión</h3>
      <form className={style.form}>
        <FormInput type="text" placeholder="Nombre" />
        <FormInput type="text" placeholder="Apellido" />
        <FormInput type="email" placeholder="Correo" />
        <FormInput
          type="password"
          placeholder="Contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
        />
        <FormInput
          type="password"
          placeholder="Confirmar contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
        />
        <GenericButton text="Iniciar sesión" submit />
      </form>
      <div className={style.link}>
        <Link to="/register">O inicia sesión</Link>
      </div>
    </section>
  );
};

export default Register;
