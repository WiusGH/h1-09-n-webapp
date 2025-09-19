import { getUserData, saveUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface CompleteProfileData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  skills: string[];
  resumeUrl: string;
}

interface ApiResponse<T> {
  data: T;
  error: string | null;
}

/**
 * API para completar el perfil de un usuario
 * @param title Título del perfil
 * @param country País del perfil
 * @param address Direccion del perfil
 * @param phoneNumber Telefono del perfil
 * @param skills (Lista de strings) Habilidades del perfil
 * @param resumeUrl URL del curriculum del perfil
 * @returns {Promise<completeProfileData>} Información del perfil completado
 * @throws {Error} Si no se encuentra la información del usuario logueado
 * @throws {Error} Si ocurre un error al completar el perfil
 */
export async function completeProfile(
  data: CompleteProfileData
): Promise<CompleteProfileData> {
  const userData = getUserData();
  if (!userData) throw new Error("Usuario no encontrado o no logueado");
  try {
    // Temporal solo para que la API funcione
    data.skills = ["React", "Docker"];
    const { data: response } = await axiosInstance.patch<
      ApiResponse<CompleteProfileData>
    >("/candidates", data, {
      headers: {
        Authorization: `Bearer ${userData.token}`,
      },
    });
    // Esta API solo cambia el "profileCompleted" a true y no requiere cuerpo
    await axiosInstance.post("/candidates", {
      headers: {
        Authorization: `Bearer ${userData.token}`,
      },
    });
    if (response.error) {
      throw new Error(response.error);
    }

    const updatedProfile = response.data;

    const updatedUserData = {
      ...userData,
      ...updatedProfile,
      profileCompleted: true,
    };

    saveUserData(updatedUserData);

    return updatedProfile;
  } catch (error) {
    console.error("Error completando el perfil:", error);
    throw error;
  }
}

export default completeProfile;
