import type { UserData } from "../../types/Types";
import { saveUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface LoginRequestData {
  email: string;
  password: string;
}

interface LoginResponseData {
  token: string;
  // user: userData;
  user: Omit<UserData, "token">;
}

interface CandidateResponseData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  resumeUrl: string;
  skills: string[];
  active: boolean;
  email: string;
}

interface ApiResponse<T> {
  data: T;
  error: string | null;
}
/**
 * Inicia sesión en la aplicación
 * @param email  Correo del usuario
 * @param password - Contraseña del usuario
 * @returns {Promise<userData>} Información completa del usuario
 * @throws {Error} Si ocurre un error al iniciar sesión
 */
export async function login(data: LoginRequestData): Promise<UserData> {
  try {
    const { data: loginResponse } = await axiosInstance.post<
      ApiResponse<LoginResponseData>
    >("/auth/login", data);

    if (loginResponse.error) {
      throw new Error(loginResponse.error);
    }

    const { token, user } = loginResponse.data;

    // API que se llama solo si el usuario es de tipo "CANDIDATE"
    if (user.role === "CANDIDATE") {
      const { data: candidateResponse } = await axiosInstance.get<
        ApiResponse<CandidateResponseData>
        // TODO: "getCandidated" tiene una d demás porque se equivocaron en el backend pero puede que lo corrijan pronto
      >("/candidates/getCandidated", {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (candidateResponse.error) {
        throw new Error(candidateResponse.error);
      }

      const candidate = candidateResponse.data;

      const fullUserData: UserData = {
        ...user,
        ...candidate,
        token,
      };

      saveUserData(fullUserData);
      return fullUserData;
      // TODO: falta mucho para implementar esta API pero quizás no de el tiempo
      // API que solo se llama si el usuario es de tipo "RECRUITER"
    }
    // else if (user.role === "RECRUITER") {
    //   await axiosInstance.get("/recruiters/get-me", {
    //     headers: { Authorization: `Bearer ${token}` },
    //   });
    // }
    saveUserData({ ...user, token });
    return { ...user, token };
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    throw error;
  }
}
