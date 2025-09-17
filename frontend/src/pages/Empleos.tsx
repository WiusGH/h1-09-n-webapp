// src/pages/Empleos.tsx
import { useState } from "react";
import { useJobs } from "../hooks/useJobs";
import JobCard from "../components/JobCard/JobCard";
import ModalCard from "../components/JobCard/ModalCard";
import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import styles from "../components/layout/Layout.module.css";
import NotFound from "./NotFound/NotFound";
import type { JobPostData } from "../types/Types";

const Empleos = () => {
  const { jobs, loading, error, applyJob, unapplyJob } = useJobs();
  const [selectedJob, setSelectedJob] = useState<JobPostData | null>(null);

  if (loading) return <p>Cargando empleos...</p>;
  if (error) return <NotFound />;

  return (
    <DynamicContainer
      main={
        <div className={styles.jobsGrid}>
          {jobs.map((job) => (
            <div key={job.id} onClick={() => setSelectedJob(job)}>
              <JobCard
                job={job}
                applied={job.applied}
                onApply={(e) => {
                  e.stopPropagation();
                  return job.applied ? unapplyJob(job.id) : applyJob(job.id);
                }}
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
                {selectedJob.applied && (
                  <p style={{ color: "green" }}>
                    ✅ Ya aplicaste a este trabajo
                  </p>
                )}
              </div>
            )}
          </ModalCard>
        </div>
      }
      side={<UserInfo />}
    />
  );
};

export default Empleos;
