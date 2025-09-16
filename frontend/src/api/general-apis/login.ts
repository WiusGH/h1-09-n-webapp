import type { userData } from "../../types/Types";
import { saveUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface LoginRequestData {
  email: string;
  password: string;
}

interface LoginResponseData {
  token: string;
  user: userData;
}

interface ApiResponse<T> {
  data: T;
  error: string | null;
}
/**
 * Inicia sesión en la aplicación
 * @param {string} data.email  Correo del usuario
 * @param {string} data.password - Contraseña del usuario
 * @returns {Promise<LoginResponseData>} Información de inicio de sesión
 * @throws {Error} Si ocurre un error al iniciar sesión
 */
export async function login(
  data: LoginRequestData
): Promise<LoginResponseData> {
  try {
    const { data: response } = await axiosInstance.post<
      ApiResponse<LoginResponseData>
    >("/auth/login", data);

    if (response.error) {
      throw new Error(response.error);
    }

    saveUserData({ ...response.data.user, token: response.data.token });
    return response.data;
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    throw error;
  }
}
