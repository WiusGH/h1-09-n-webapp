// hooks/useAppliedJobs.ts
import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosInstance";
import type { jobOfferData } from "../types/Types";

export function useAppliedJobs() {
  const [jobs, setJobs] = useState<jobOfferData[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetchAppliedJobs() {
      setLoading(true);
      try {
        const { data } = await axiosInstance.get("/job-apply/me");
        const appliedJobs: jobOfferData[] = data.data.map((job: any) => ({
          id: job.id,
          title: job.title,
          companyName: job.companyName,
          description: job.description,
          country: job.companyCountry,
          address: job.companyAddress,
          phoneNumber: job.phoneNumber,
          applied: true,
        }));

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
