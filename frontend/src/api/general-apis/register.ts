import axiosInstance from "../axiosInstance";
import { login } from "./login";

interface RegisterRequestData {
  name: string;
  lastName: string;
  email: string;
  password: string;
}

interface ApiResponse<T> {
  data: T;
  error: string | null;
}

/**
 * Registra un nuevo usuario y lo loguea automáticamente
 * @param name Nombre del usuario
 * @param lastName Apellido del usuario
 * @param email Correo del usuario
 * @param password Contraseña del usuario
 * @returns {Promise<userData>} Información del usuario logueado
 * @throws {Error} Si ocurre un error en el registro o login
 */
export async function register(data: RegisterRequestData): Promise<void> {
  try {
    const { data: response } = await axiosInstance.post<ApiResponse<void>>(
      "/auth/register",
      data
    );

    if (response.error) {
      throw new Error(response.error);
    }

    await login({ email: data.email, password: data.password });
  } catch (error) {
    console.error("Error al registrar:", error);
    throw error;
  }
}
