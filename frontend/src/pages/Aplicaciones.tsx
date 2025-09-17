// src/pages/Aplicaciones.tsx
import { useState } from "react";
import JobCard from "../components/JobCard/JobCard";
import ModalCard from "../components/JobCard/ModalCard";
import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import styles from "../components/layout/Layout.module.css";
import type { jobOfferData } from "../types/Types";
import { useAppliedJobs } from "../hooks/useAppliedJobs"; // 👈 importamos el hook

const Aplicaciones = () => {
  const { jobs, loading, error } = useAppliedJobs(); // 👈 usamos el hook
  const [selectedJob, setSelectedJob] = useState<jobOfferData | null>(null);

  if (loading) return <p>Cargando empleos aplicados...</p>;
  if (error) return <p>Error al cargar empleos aplicados.</p>;

  return (
    <DynamicContainer
      main={
        <div className={styles.jobsGrid}>
          {jobs.length === 0 && (
            <p>No has aplicado a ningún empleo todavía.</p>
          )}

          {jobs.map((job) => (
            <div key={job.id} onClick={() => setSelectedJob(job)}>
              <JobCard
                job={job}
                applied={true} // 👈 siempre true porque ya están aplicados
                onApply={() => {}} // 👈 no hace falta aplicar/desaplicar acá
                onClick={() => setSelectedJob(job)}
              />
            </div>
          ))}

          <ModalCard
            isOpen={!!selectedJob}
            onClose={() => setSelectedJob(null)}
          >
            {selectedJob && (
              <div>
                <h2>{selectedJob.title}</h2>
                <h4>{selectedJob.companyName}</h4>
                <p>{selectedJob.description}</p>
              </div>
            )}
          </ModalCard>
        </div>
      }
      side={<UserInfo />}
    />
  );
};

export default Aplicaciones;





// import { useEffect, useState } from "react";
// import axiosInstance from "../api/axiosInstance";
// import JobCard from "../components/JobCard/JobCard";
// import ModalCard from "../components/JobCard/ModalCard";
// import DynamicContainer from "../components/containers/DynamicContainer";
// import UserInfo from "../components/sidebars/UserInfo";
// import styles from "../components/layout/Layout.module.css";
// import type { jobOfferData } from "../types/Types";

// const Aplicaciones = () => {
//   const [jobs, setJobs] = useState<jobOfferData[]>([]);
//   const [selectedJob, setSelectedJob] = useState<jobOfferData | null>(null);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(false);

//   async function fetchAppliedJobs() {
//     setLoading(true);
//     try {
//       const { data } = await axiosInstance.get("/job-apply/me");

//       //  Adaptamos la data a la interface jobOfferData
//       const appliedJobs: jobOfferData[] = data.data.map((job: any) => ({
//         id: job.id,
//         title: job.title,
//         companyName: job.companyName || "Empresa no especificada",
//         description: job.description || "Sin descripción",
//         country: job.companyCountry || "N/A",
//         address: job.companyAddress || "N/A",
//         phoneNumber: job.phoneNumber || "N/A",
//         applied: true, // porque ya viene de aplicados
//       }));

//       setJobs(appliedJobs);
//       setError(false);
//     } catch (err) {
//       console.error("Error al traer empleos aplicados:", err);
//       setError(true);
//     } finally {
//       setLoading(false);
//     }
//   }

//   useEffect(() => {
//     fetchAppliedJobs();
//   }, []);

//   if (loading) return <p>Cargando empleos aplicados...</p>;
//   if (error) return <p>No hay empleos aplicados</p>;

//   return (
//     <DynamicContainer
//       main={
//         <div className={styles.jobsGrid}>
//           {jobs.length === 0 && (
//             <p>No has aplicado a ningún empleo todavía.</p>
//           )}

//           {jobs.map((job) => (
//             <div key={job.id} onClick={() => setSelectedJob(job)}>
//               <JobCard
//                 job={job}
//                 applied={true} // siempre true en esta vista
//                 onApply={() => {}} // 👈 acá no necesitamos aplicar/desaplicar
//                 onClick={() => setSelectedJob(job)}
//               />
//             </div>
//           ))}

//           <ModalCard
//             isOpen={!!selectedJob}
//             onClose={() => setSelectedJob(null)}
//           >
//             {selectedJob && (
//               <div>
//                 <h2>{selectedJob.title}</h2>
//                 <h4>{selectedJob.companyName}</h4>
//                 <p>{selectedJob.description}</p>
//               </div>
//             )}
//           </ModalCard>
//         </div>
//       }
//       side={<UserInfo />}
//     />
//   );
// };

// export default Aplicaciones;
