// src/components/JobCard/JobCard.tsx
import React from "react";
import styles from "./JobCard.module.css";
import GenericButton from "../buttons/GenericButton";
import type { jobOfferData } from "../../types/Types";

interface JobCardProps {
  job: jobOfferData;
  applied: boolean;
  onClick?: () => void;
  onApply: (e: React.MouseEvent) => void;
}

const JobCard: React.FC<JobCardProps> = ({ job, applied, onClick, onApply }) => {
  return (
    <div className={styles.card} onClick={onClick}>
      <div className={styles.header}>
        <h3 className={styles.title}>{job.title}</h3>
        <span className={styles.company}>{job.companyName}</span>
        <p className={styles.description}>{job.description}</p>
        <div className={styles.actions}>
          <GenericButton
            text={applied ? "Desaplicar" : "Aplicar"}
            onClick={onApply}
          />
        </div>
      </div>
    </div>
  );
};

export default JobCard;


