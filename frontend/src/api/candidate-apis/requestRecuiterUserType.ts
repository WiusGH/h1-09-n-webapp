import { getUserData } from "../../utils/userStorage";
import axiosInstance from "../axiosInstance";

interface RecruiterUserTypeData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  skills: string[];
  cvurl: string;
  userId?: string;
}

export const requestRecuiterUserType = async (data: RecruiterUserTypeData) => {
  const userData = getUserData();
  if (!userData) throw new Error("Datos de usuario no encontrados");

  data.userId = userData.id;

  try {
    const response = await axiosInstance.post(
      `/candidates/${userData.id}/request-recruiter`,
      data
    );
    return response.data;
  } catch (error) {
    console.error("Error creating job post:", error);
    throw error;
  }
};
