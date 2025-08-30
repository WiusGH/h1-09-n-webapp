import type React from "react";
import JobCard from "../components/JobCard/JobCard";
import styles from "../components/layout/Layout.module.css"
import { useEffect, useState } from "react";

interface Job {
  id: number,
  title: string,
  company: string,
  description: string 
}

const Empleos: React.FC = () => {
  const [jobs, setJobs] = useState<Job[]>([])

  useEffect(()=> {
    const mockJobs: Job[] = [
        {
        id: 1,
        title: "Frontend Developer",
        company: "Tech Solutions",
        description: "Buscamos un Frontend con experiencia en React y TypeScript."
      },
      {
        id: 2,
        title: "Backend Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL."
      },
      {
        id: 3,
        title: "Fullstack Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL."
      },
      {
        id: 4,
        title: "Cloud engineer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL."
      },
      {
        id: 5,
        title: "Backend Developer",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL."
      },
      {
        id: 6,
        title: "Data analytics",
        company: "Cloud Services",
        description: "Se requiere experiencia en Node.js, Express y SQL."
      }
    
    ];
    setJobs(mockJobs)
  }, [])

  return (
    <div className={styles.jobsGrid}>
      {jobs.map((job) =>(
        <JobCard key={job.id} title={job.title} company={job.company} description={job.description}/>
      ))}

    </div>
  )


};
  
  


export default Empleos;
