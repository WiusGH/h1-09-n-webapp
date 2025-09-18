import React, { useState } from "react";
import GenericButton from "../buttons/GenericButton";
import style from "./ProfileEditor.module.css";
import type { UserData } from "../../types/Types";

interface EditCvProps {
  user: UserData;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

/**
 * Componente que renderiza una sección para editar el CV del usuario.
 * @param user - Los datos del usuario que se están editando.
 * @param handleChange - Función que se llama cuando se produce un cambio en el formulario.
 * @returns {JSX.Element} - Elemento JSX que representa la sección de edición del CV.
 */
const EditCv: React.FC<EditCvProps> = ({ user, handleChange }) => {
  const [cvOption, setCvOption] = useState<"upload" | "create">("upload");

  const handleDownloadCV = () => {
    if (user.resumeUrl) {
      window.open(user.resumeUrl, "_blank");
    } else {
      alert("No hay un CV cargado para descargar.");
    }
  };

  return (
    <div className={style.section}>
      <h2>Editar CV</h2>
      <div className={style.cvOptions}>
        <button
          type="button"
          className={`${style.cvOptionButton} ${
            cvOption === "upload" ? style.active : ""
          }`}
          onClick={() => setCvOption("upload")}
        >
          Subir URL
        </button>
        <button
          type="button"
          className={`${style.cvOptionButton} ${
            cvOption === "create" ? style.active : ""
          }`}
          onClick={() => setCvOption("create")}
        >
          Crear CV
        </button>
      </div>

      {cvOption === "upload" && (
        <div className={style.formGroup}>
          <label htmlFor="cvUrl">URL del CV:</label>
          <input
            type="text"
            id="cvUrl"
            name="cvUrl"
            value={user.resumeUrl || ""}
            onChange={handleChange}
            className={style.input}
          />
        </div>
      )}

      {cvOption === "create" && (
        <div className={style.createCvContainer}>
          <p>
            Elige esta opción para generar un CV en PDF con tus datos de perfil.
            (Lógica no implementada).
          </p>
          <GenericButton
            text="Generar CV"
            onClick={() => alert("Función de generación de CV en desarrollo.")}
          />
        </div>
      )}

      <div className={style.downloadCvContainer}>
        {user.resumeUrl && (
          <GenericButton text="Descargar CV" onClick={handleDownloadCV} />
        )}
      </div>
    </div>
  );
};

export default EditCv;
