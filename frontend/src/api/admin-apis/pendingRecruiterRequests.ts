import { getUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface ResponseData {
  userId: number;
  companyName: string;
  companyCountry: string;
  companyAddress: string;
  companyEmail: string;
  approved: boolean;
}

interface ApiResponse<T> {
  data: T;
  error: string | null;
}

/**
 * Obtiene una lista de solicitudes de reclutadores pendientes.
 * Requiere usuario de tipo ADMIN.
 * @returns {Promise<ResponseData[]>} Una lista de solicitudes de reclutadores pendientes.
 * @throws {Error} Si no se encuentra la información del usuario logueado o si ocurre un error al obtener las solicitudes pendientes.
 */
export async function pendingRecruiterRequests() {
  const user = getUserData();
  if (!user) throw new Error("Usuario no encontrado o no logueado");
  try {
    const response = await axiosInstance.get<ApiResponse<ResponseData[]>>(
      "/admins/recruiters/pending",
      {
        headers: {
          Authorization: `Bearer ${user.token}`,
        },
      }
    );
    if (response.data.error) {
      throw new Error(response.data.error);
    }
    return response.data.data;
  } catch (error) {
    console.error("Error fetching pending recruiter requests:", error);
    throw error;
  }
}
