import { useState } from "react";
import style from "./Form.module.css";
import FormInput from "../inputs/FormInput";
import GenericButton from "../buttons/GenericButton";
import { createJobPost } from "../../api/recruiter-apis/createJobPost";
import SkillsInput from "../inputs/SkillsInput";
import TextAreaInput from "../inputs/TextAreaInput";

/**
 * Formulario para crear una nueva oferta laboral
 * @returns {JSX.Element} Formulario para crear una nueva oferta laboral
 */
const NewJobForm = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [maxCandidates, setMaxCandidates] = useState(0);
  const [skills, setSkills] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await createJobPost({
        title,
        description,
        maxCandidates,
      });
      console.log(response);
    } catch {
      console.error("Error creating job post");
    } finally {
      setLoading(false);
    }
  }

  return (
    <section
      className={`${style.container} ${loading ? style.loading : ""}`}
      style={{ width: "100%" }}
    >
      {loading && (
        <div className={style.spinnerOverlay}>
          <div className={style.spinner}></div>
        </div>
      )}
      <h3>Crear nueva oferta laboral</h3>
      <form className={style.form} onSubmit={handleSubmit}>
        <FormInput
          type="text"
          placeholder="Título"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          label="Título"
        />

        <FormInput
          type="number"
          placeholder="Número de candidatos máximo"
          value={maxCandidates.toString()}
          onChange={(e) => setMaxCandidates(Number(e.target.value))}
          label="Máximo de candidatos"
        />

        <SkillsInput selectedSkills={skills} onChange={setSkills} />

        <TextAreaInput
          label="Job Description"
          placeholder="Describe la oferta"
          value={description}
          onChange={setDescription}
        />

        <GenericButton text="Crear oferta" submit />
      </form>
    </section>
  );
};

export default NewJobForm;
