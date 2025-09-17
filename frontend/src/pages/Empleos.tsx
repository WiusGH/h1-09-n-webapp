import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosInstance";
import JobCard from "../components/JobCard/JobCard";
import ModalCard from "../components/JobCard/ModalCard";
import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import styles from "../components/layout/Layout.module.css";
import NotFound from "./NotFound/NotFound";
import type { JobPostData } from "../types/Types";

const Empleos = () => {
  const [jobs, setJobs] = useState<JobPostData[]>([]);
  const [selectedJob, setSelectedJob] = useState<JobPostData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  async function handleFetchJobs() {
    setLoading(true);
    try {
      const { data } = await axiosInstance.get("/jobPost");
      console.log("Respuesta API:", data);

      const jobsFromApi: JobPostData[] = data.data.map(
        (job: JobPostData, index: number) => ({
          id: job.id || index, // si no viene id, usamos el índice como fallback
          title: job.title,
          company: job.companyName || "Empresa no especificada",
          description: job.description || "Sin descripción",
          applied: job.applied ?? false,
        })
      );

      setJobs(jobsFromApi);
      setError(false);
    } catch (err) {
      console.error("Error al traer empleos:", err);
      setError(true);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    handleFetchJobs();
  }, []);

  if (loading) {
    return <p>Cargando empleos...</p>;
  }

  if (error) {
    return <NotFound />;
  }

  return (
    <DynamicContainer
      main={
        <div className={styles.jobsGrid}>
          {jobs.map((job) => (
            <div key={job.id} onClick={() => setSelectedJob(job)}>
              <JobCard
                title={job.title}
                company={job.companyName}
                description={job.description}
                applied={job.applied}
                onApply={() =>
                  setJobs((prev) =>
                    prev.map((j) =>
                      j.id === job.id ? { ...j, applied: !j.applied } : j
                    )
                  )
                }
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

// const mockJobs: Job[] = [
//       {
//         id: 1,
//         title: "Frontend Developer",
//         company: "Tech Solutions",
//         description:
//           "Buscamos un Frontend con experiencia en React y TypeScript.",
//         applied: false,
//       },
//       {
//         id: 2,
//         title: "Backend Developer",
//         company: "Cloud Services",
//         description: "Se requiere experiencia en Node.js, Express y SQL.",
//         applied: false,
//       },
//       {
//         id: 3,
//         title: "Fullstack Developer",
//         company: "Cloud Services",
//         description: "Se requiere experiencia en Node.js, Express y SQL.",
//         applied: false,
//       },
//       {
//         id: 4,
//         title: "Cloud engineer",
//         company: "Cloud Services",
//         description: "Se requiere experiencia en Node.js, Express y SQL.",
//         applied: false,
//       },
//       {
//         id: 5,
//         title: "Backend Developer",
//         company: "Cloud Services",
//         description: "Se requiere experiencia en Node.js, Express y SQL.",
//         applied: false,
//       },
//       {
//         id: 6,
//         title: "Data analytics",
//         company: "Cloud Services",
//         description: "Se requiere experiencia en Node.js, Express y SQL.",
//         applied: false,
//       },
//     ];
//     setJobs(mockJobs);
