import { useState } from "react";
import FormInput from "../inputs/FormInput";
import style from "./Form.module.css";
const LoginForm = () => {
  const [showPassword, setShowPassword] = useState(false);

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
  };
  return (
    <section className={style.container}>
      <form className={style.form}>
        <FormInput type="text" placeholder="Usuario" />
        <FormInput
          type="password"
          placeholder="Contraseña"
          showPassword={showPassword}
          onTogglePassword={togglePassword}
        />
      </form>
    </section>
  );
};

export default LoginForm;
