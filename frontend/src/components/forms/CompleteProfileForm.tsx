import { useState } from "react";
import style from "./Form.module.css";
import { useNavigate } from "react-router-dom";
import FormInput from "../inputs/FormInput";
import GenericButton from "../buttons/GenericButton";
import SkillsInput from "../inputs/SkillsInput";
import { completeProfile } from "../../api/candidate-apis/completeProfile";

/**
 * Formulario para completar el perfil
 * @return {JSX.Element} Formulario para completar el perfil
 */
const CompleteProfileForm = () => {
  const [title, setTitle] = useState("");
  const [country, setCountry] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [skills, setSkills] = useState<string[]>([]);
  const [resumeUrl, setResumeUrl] = useState("");
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  function handleSubmit() {
    setLoading(true);
    setErrorMessage("");
    completeProfile({
      title,
      country,
      address,
      phoneNumber,
      skills,
      resumeUrl,
    })
      .then(() => {
        setLoading(false);
        navigate("/");
      })
      .catch((error) => {
        setErrorMessage("Error al completar el perfil");
        console.error("Error al completar el perfil:", error);
      })
      .finally(() => setLoading(false));
  }

  return (
    <section className={`${style.container} ${loading ? style.loading : ""}`}>
      {loading && (
        <div className={style.spinnerOverlay}>
          <div className={style.spinner}></div>
        </div>
      )}
      <h3>Completa tu perfil</h3>
      <form
        className={style.form}
        onSubmit={(e) => {
          e.preventDefault();
          handleSubmit();
        }}
      >
        <FormInput
          type="text"
          label="Título"
          placeholder="Título"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <FormInput
          type="text"
          label="País"
          placeholder="País"
          value={country}
          onChange={(e) => setCountry(e.target.value)}
        />
        <FormInput
          type="text"
          label="Dirección"
          placeholder="Dirección"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />
        <FormInput
          type="text"
          label="Teléfono"
          placeholder="Teléfono"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        />
        <SkillsInput selectedSkills={skills} onChange={setSkills} />
        <FormInput
          type="text"
          label="Enlace CV"
          placeholder="Enlace CV"
          value={resumeUrl}
          onChange={(e) => setResumeUrl(e.target.value)}
        />

        <GenericButton text="Guardar" submit />
      </form>
      <span className={style.errorContainer}>
        {errorMessage && <p className={style.error}>{errorMessage}</p>}
      </span>
    </section>
  );
};

export default CompleteProfileForm;
