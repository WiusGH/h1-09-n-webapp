// src/pages/Aplicaciones.tsx
import { useState } from "react";
import JobCard from "../components/JobCard/JobCard";
import ModalCard from "../components/JobCard/ModalCard";
import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import styles from "../components/layout/Layout.module.css";
import type { JobPostData } from "../types/Types";
import { useAppliedJobs } from "../hooks/useAppliedJobs"; //  importamos el hook

const Aplicaciones = () => {
  const { jobs, loading, error } = useAppliedJobs(); //  usamos el hook
  const [selectedJob, setSelectedJob] = useState<JobPostData | null>(null);

  if (loading) return <p>Cargando empleos aplicados...</p>;
  if (error) return <p>Error al cargar empleos aplicados.</p>;

  return (
    <DynamicContainer
      main={
        <div className={styles.jobsGrid}>
          {jobs.length === 0 && <p>No has aplicado a ningún empleo todavía.</p>}

          {jobs.map((job) => (
            <div key={job.id} onClick={() => setSelectedJob(job)}>
              <JobCard
                job={job}
                applied={true} //  siempre true porque ya están aplicados
                onApply={() => {}} //  no hace falta aplicar/desaplicar acá
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
