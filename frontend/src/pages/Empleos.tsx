import type React from "react";
import JobCard from "../components/JobCard/JobCard";
import styles from "../components/layout/Layout.module.css"
import { useEffect, useState } from "react";
import ModalCard from "../components/JobCard/ModalCard";

interface Job {
  id: number,
  title: string,
  company: string,
  description: string;
  applied: boolean;
}

const Empleos: React.FC = () => {
  const [jobs, setJobs] = useState<Job[]>([]);
  const [selectedJob, setSelectedJob] = useState<Job | null>(null);


  const handleApply = (id: number) => {
  setJobs((prevJobs) =>
    prevJobs.map((job) =>
      job.id === id ? { ...job, applied: !job.applied  } : job
    )
  );
};


  
   useEffect(() => {
     const mockJobs: Job[] = [
        {
        id: 1,
        title: "Frontend Developer",
        company: "Tech Solutions",
        description: "Buscamos un Frontend con experiencia en React y TypeScript.",
        applied: false
      },
      {
        id: 2,
        title: "Backend Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL.",
          applied: false
      },
      {
        id: 3,
        title: "Fullstack Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL.",
        applied: false
      },
      {
        id: 4,
        title: "Cloud engineer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL.",
        applied: false
      },
      {
        id: 5,
        title: "Backend Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL.",
        applied: false
      },
      {
        id: 6,
        title: "Data analytics",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL.",
        applied: false
      }
    
    ];
    setJobs(mockJobs)
   
  }, [])

  return (
    <div className={styles.jobsGrid}>
      {jobs.map((job) =>(
        <div key={job.id} onClick={()=> setSelectedJob(job)}>
          <JobCard key={job.id} title={job.title} company={job.company} description={job.description} applied={job.applied} onApply={()=> handleApply(job.id)}
            onClick={()=> setSelectedJob(job)}/>
        </div>
      ))}

      <ModalCard isOpen={!!selectedJob} onClose={()=> setSelectedJob(null)}>
        {selectedJob && (
          <div>
            <h2>{selectedJob.title}</h2>
            <h4>{selectedJob.company}</h4>
            <p>{selectedJob.description}</p>
              {selectedJob.applied &&
            (<p style={{ color: "green" }}>✅ Ya aplicaste a este trabajo</p>
          )}
          </div>
        )}

      </ModalCard>

    </div>
  )
}



  
  


export default Empleos;
