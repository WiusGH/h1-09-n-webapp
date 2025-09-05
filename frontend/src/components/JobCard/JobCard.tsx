// src/components/JobCard/JobCard.tsx
import React from "react";
import styles from "./JobCard.module.css";

interface JobCardProps {
  title: string;
  company: string;
  description: string;
  
}

const JobCard: React.FC<JobCardProps> = ({ title, company, description}) => {
  return (
    <div className={styles.card}>
      <div className={styles.header}>
        <h3 className={styles.title}>{title}</h3>
        <span className={styles.company}>{company}</span>
        <p className={styles.description}>{description}</p>
      </div>
      
       <div className={styles.actions}>
        <button className={styles.applyBtn}>Aplicar</button>
        
      </div>
     
    </div>
  );
};

export default JobCard;




