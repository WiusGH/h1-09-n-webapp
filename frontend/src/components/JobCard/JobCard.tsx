// src/components/JobCard/JobCard.tsx
import React from "react";
import styles from "./JobCard.module.css";
import GenericButton from "../buttons/GenericButton";
import type { JobPostData } from "../../types/Types";
import { isLoggedIn, isProfileComplete } from "../../utils/userStorage";
import { useNavigate } from "react-router-dom";

interface JobCardProps {
  job: JobPostData;
  applied: boolean;
  onClick?: () => void;
  onApply: (e: React.MouseEvent) => void;
}

/**
 * Componente para mostrar una oferta laboral.
 * @param job - La oferta laboral a mostrar.
 * @param applied - Si la oferta laboral ya ha sido aplicada.
 * @param onClick - (Opcional) Función a llamar cuando se hace click en la tarjeta.
 * @param onApply - Función a llamar cuando se hace click en el botón de aplicar.
 */
const JobCard: React.FC<JobCardProps> = ({
  job,
  applied,
  onClick,
  onApply,
}) => {
  const navigate = useNavigate();
  const userLoggedIn = isLoggedIn();
  const completeUserProfile = isProfileComplete();

  const handleApply = (e: React.MouseEvent) => {
    if (userLoggedIn && completeUserProfile) {
      onApply(e);
    } else {
      if (!completeUserProfile) {
        navigate("/completar-perfil");
      } else {
        navigate("/login");
      }
    }
  };
  return (
    <div className={styles.card} onClick={onClick}>
      <div className={styles.header}>
        <h3 className={styles.title}>{job.title}</h3>
        <span className={styles.company}>{job.companyName}</span>
        <p className={styles.description}>{job.description}</p>
        <div className={styles.actions}>
          <GenericButton
            text={
              userLoggedIn
                ? completeUserProfile
                  ? applied
                    ? "Aplicado"
                    : "Aplicar"
                  : "Completar perfil para aplicar"
                : "Inicia sesión para aplicar"
            }
            onClick={handleApply}
          />
        </div>
      </div>
    </div>
  );
};

export default JobCard;
