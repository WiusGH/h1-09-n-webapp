
import React from "react";
import styles from "./JobCard.module.css";
import GenericButton from "../buttons/GenericButton";


interface JobCardProps {
  title: string;
  company: string;
  description: string;
  onClick?: () => void;
  applied: boolean;
  onApply: (e: React.MouseEvent) => void;

}

const JobCard: React.FC<JobCardProps> = ({ title, company, description, onClick, applied, onApply }) => {
  return (
    <div className={styles.card} onClick={onClick}>
      <div className={styles.header}>
        <h3 className={styles.title}>{title}</h3>
        <span className={styles.company}>{company}</span>
        <p className={styles.description}>{description}</p>
       
          <div className={styles.actions}>
            <GenericButton
              text={applied ? "Desaplicar" : "Aplicar"}
              
              onClick={(e) => {
                e.stopPropagation(); // evita abrir el modal
                onApply(e);
              }}
            >
               
            </GenericButton>
           </div>
      </div>
      
      
     
    </div>
  );
};

export default JobCard;




