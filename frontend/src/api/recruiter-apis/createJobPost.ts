import { getUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface JobPostData {
  title: string;
  description: string;
  maxCandidates: number;
  skills: string[];
  recruiterName?: string;
  companyName?: string;
  companyCountry?: string;
  companyEmail?: string;
}

/**
 * Crea un nuevo anuncio de trabajo
 * @param title: Título del anuncio de trabajo
 * @param description: Descripción del anuncio de trabajo
 * @param maxCandidates: Número máximo de candidatos para el anuncio de trabajo
 * @param skills: (Lista de strings) Habilidades requeridas para el anuncio de trabajo
 * @returns Información del anuncio de Trabajo creado
 * @throws Si no se encuentra la información del usuario logueado
 * @throws {Si ocurre un error al crear el anuncio de Trabajo
 */
export const createJobPost = async (jobPost: JobPostData) => {
  const userData = getUserData();
  if (!userData) throw new Error("Datos de usuario no encontrados");

  jobPost.recruiterName = `${userData.name} ${userData.lastName}`;
  jobPost.companyName = userData.companyName;
  jobPost.companyCountry = userData.companyCountry;
  jobPost.companyEmail = userData.companyEmail;

  try {
    console.log("Creating job post:", jobPost);
    const response = await axiosInstance.post("/recruiters/createJob", jobPost);
    return response.data;
  } catch (error) {
    console.error("Error creating job post:", error);
    throw error;
  }
};
