import axiosInstance from "../axiosInstance";
import { getUserData } from "../../utils/userStorage";
import type { RequestRecruiterRoleData } from "../../types/Types";
import type { UserData } from "../../types/Types";

interface ApiResponse<T> {
  data: T;
  error: string | null;
}

/**
 * Envía una solicitud para cambiar el rol de un usuario a RECRUITER
 * @param companyName Nombre de la compañía
 * @param companyCountry País de la compañía
 * @param companyAddress Dirección de la compañía
 * @param companyEmail Email de la compañía
 * @returns {Promise<userData>} Datos de usuario actualizados
 * @throws {Error} Si no hay token o la API devuelve un error
 */
export async function requestRecruiterRole(
  requestData: RequestRecruiterRoleData
): Promise<UserData> {
  const user = getUserData();
  if (!user) throw new Error("Usuario no encontrado o no logueado");

  try {
    const { data: response } = await axiosInstance.post<ApiResponse<UserData>>(
      "/candidates/request-recruiter",
      requestData,
      {
        headers: {
          Authorization: `Bearer ${user.token}`,
        },
      }
    );

    if (response.error) {
      throw new Error(response.error);
    }

    return { ...response.data, token: user.token };
  } catch (error) {
    console.error("Error solicitando rol de reclutador:", error);
    throw error;
  }
}
