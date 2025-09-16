import { getUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface completeProfileData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  skills: string[];
  resumeUrl: string;
  userId?: string;
}

/**
 * API para completar el perfil de un usuario
 * @param {completeProfileData} data: Información a completar del perfil del candidato
 * @param {string} data.title: Título del perfil
 * @param {string} data.country: País del perfil
 * @param {string} data.address: Direccion del perfil
 * @param {string} data.phoneNumber: Telefono del perfil
 * @param {string[]} data.skills: (Lista de strings) Habilidades del perfil
 * @param {string} data.resumeUrl: URL del curriculum del perfil
 * @returns {Promise<completeProfileData>} Información del perfil completado
 * @throws {Error} Si no se encuentra la información del usuario logueado
 * @throws {Error} Si ocurre un error al completar el perfil
 */
export async function completeProfile(
  data: completeProfileData
): Promise<completeProfileData> {
  const userData = getUserData();
  if (!userData) throw new Error("Datos de usuario no encontrados");
  data.userId = userData.id;
  try {
    const { data: response } = await axiosInstance.post(
      `/candidates/${userData.id}/request-recruiter`,
      data
    );
    return response.data;
  } catch (error) {
    console.error("Error creating job post:", error);
    throw error;
  }
}

export default completeProfile;
