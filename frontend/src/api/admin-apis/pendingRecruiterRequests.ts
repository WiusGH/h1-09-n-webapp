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
