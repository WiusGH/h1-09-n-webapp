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



// import React from "react";
// import styles from "./JobCard.module.css";
// import GenericButton from "../buttons/GenericButton";
// import type { jobOfferData } from "../../types/Types";
// import { useState } from "react";
// import axiosInstance from "../../api/axiosInstance";



// interface JobCardProps {
//   job: jobOfferData;        // 👈 Debe recibir el objeto job completo
//   applied: boolean;
//   onClick?: () => void;
//   onApply: (e: React.MouseEvent) => void;
// }

// const JobCard: React.FC<JobCardProps> = ({ job, onClick, onApply }) => {
//    const [applied, setApplied] = useState(job.applied);

//    const handleToggleApply = async (e: React.MouseEvent) => {
//     e.stopPropagation(); // evita abrir modal al clickear botón
//     try {
//       if (!applied) {
//         // 📌 Aplicar → POST
//         await axiosInstance.post("/job-apply", { jobId: job.id });
//         setApplied(true);
//       } else {
//         // 📌 Desaplicar → DELETE
//         await axiosInstance.delete(`/job-apply/${job.id}`);
//         setApplied(false);
//       }
//     } catch (error) {
//       console.error("Error al aplicar/desaplicar:", error);
//     }
//   };

//   return (
//     <div className={styles.card} onClick={onClick}>
//       <div className={styles.header}>
//         <h3 className={styles.title}>{job.title}</h3>
//         <span className={styles.company}>{job.companyName}</span>
//         <p className={styles.description}>{job.description}</p>
       
//           <div className={styles.actions}>
//             <GenericButton
//               text={job.applied ? "Desaplicar" : "Aplicar"}
              
//               onClick={(e) => {
//                 e.stopPropagation(); // evita abrir el modal
//                 onApply(e);
//                 handleToggleApply
//               }}
//             >
               
//             </GenericButton>
//            </div>
//       </div>
      
      
     
//     </div>
//   );
// };

// export default JobCard;