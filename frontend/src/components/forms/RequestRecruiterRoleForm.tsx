import { useState } from "react";
import style from "./Form.module.css";
import { useNavigate } from "react-router-dom";
import GenericButton from "../buttons/GenericButton";
import FormInput from "../inputs/FormInput";
import { requestRecruiterRole } from "../../api/candidate-apis/requestRecruiterRole";

const RequestRecruiterRoleForm = () => {
  const [companyName, setCompanyName] = useState("");
  const [companyCountry, setCompanyCountry] = useState("");
  const [companyAddress, setCompanyAddress] = useState("");
  const [companyEmail, setCompanyEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  /**
   * Formulario para enviar una solicitud para cambiar el rol de un usuario a RECRUITER
   * @returns {Promise<void>} Solicitud realizada con exito
   * @throws {Error} Si no hay token o la API devuelve un error
   */
  async function handleSubmit() {
    setLoading(true);
    setErrorMessage("");
    try {
      await requestRecruiterRole({
        companyName,
        companyCountry,
        companyAddress,
        companyEmail,
      });
      navigate("/");
    } catch (err) {
      setErrorMessage("Ocurrió un error durante la solicitud.");
      console.error(err);
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
      <h3>solicitud para ser reclutador</h3>
      <form
        className={style.form}
        onSubmit={(e) => {
          e.preventDefault();
          handleSubmit();
        }}
      >
        <FormInput
          type="text"
          label="Nombre de la compañía"
          placeholder="Nombre de la compañía"
          value={companyName}
          onChange={(e) => setCompanyName(e.target.value)}
        />
        <FormInput
          type="text"
          label="País de la compañía"
          placeholder="País de la compañía"
          value={companyCountry}
          onChange={(e) => setCompanyCountry(e.target.value)}
        />
        <FormInput
          type="text"
          label="Dirección de la compañía"
          placeholder="Nombre de la compañía"
          value={companyAddress}
          onChange={(e) => setCompanyAddress(e.target.value)}
        />
        <FormInput
          type="email"
          label="Correo de la compañía"
          placeholder="Correo de la compañía"
          value={companyEmail}
          onChange={(e) => setCompanyEmail(e.target.value)}
        />
        <GenericButton text="Iniciar sesión" submit />
      </form>
      <span className={style.errorContainer}>
        {errorMessage && <p className={style.error}>{errorMessage}</p>}
      </span>
    </section>
  );
};

export default RequestRecruiterRoleForm;
