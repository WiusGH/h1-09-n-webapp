// src/hooks/useJobs.ts
import { useState, useEffect } from "react";
import axiosInstance from "../api/axiosInstance";
import type { jobOfferData } from "../types/Types";

export function useJobs() {
  const [jobs, setJobs] = useState<jobOfferData[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  // 🔹 Traer empleos
  async function fetchJobs() {
    setLoading(true);
    try {
      const { data } = await axiosInstance.get("/jobPost");
      const jobsFromApi: jobOfferData[] = data.data.map((job: any, index: number) => ({
        id: job.id || index,
        title: job.title,
        companyName: job.companyName || "Empresa no especificada",
        description: job.description || "Sin descripción",
        country: job.companyCountry || "N/A",
        address: job.companyAddress || "N/A",
        phoneNumber: job.phoneNumber || "N/A",
        applied: job.applied ?? false,
      }));
      setJobs(jobsFromApi);
      setError(false);
    } catch (err) {
      console.error("Error al traer empleos:", err);
      setError(true);
    } finally {
      setLoading(false);
    }
  }

  // 🔹 Aplicar a un empleo
  async function applyJob(jobId: string) {
    try {
      await axiosInstance.post(`/job-apply/${jobId}`);
      setJobs((prev) =>
        prev.map((j) =>
          j.id === jobId ? { ...j, applied: true } : j
        )
      );
    } catch (err) {
      console.error("Error al aplicar:", err);
    }
  }

  // 🔹 Desaplicar un empleo
  async function unapplyJob(jobId: string) {
    try {
      await axiosInstance.delete(`/job-apply/${jobId}`);
      setJobs((prev) =>
        prev.map((j) =>
          j.id === jobId ? { ...j, applied: false } : j
        )
      );
    } catch (err) {
      console.error("Error al desaplicar:", err);
    }
  }

  useEffect(() => {
    fetchJobs();
  }, []);

  return { jobs, loading, error, applyJob, unapplyJob };
}
