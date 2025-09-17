// hooks/useAppliedJobs.ts
import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosInstance";
import type { JobPostData } from "../types/Types";

export function useAppliedJobs() {
  const [jobs, setJobs] = useState<JobPostData[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetchAppliedJobs() {
      setLoading(true);
      try {
        const { data } = await axiosInstance.get("/job-apply/me");
        const appliedJobs: JobPostData[] = data.data.map(
          (job: JobPostData) => ({
            id: job.id,
            title: job.title,
            companyName: job.companyName,
            description: job.description,
            country: job.companyCountry,
            address: job.companyAddress,
            applied: true,
          })
        );

        setJobs(appliedJobs);
        setError(false);
      } catch (err) {
        console.error("Error al traer empleos aplicados:", err);
        setError(true);
      } finally {
        setLoading(false);
      }
    }

    fetchAppliedJobs();
  }, []);

  return { jobs, loading, error };
}
