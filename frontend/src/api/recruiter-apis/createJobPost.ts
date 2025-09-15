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

export const createJobPost = async (jobPost: JobPostData) => {
  const userData = getUserData();
  if (!userData) throw new Error("Datos de usuario no encontrados");

  jobPost.recruiterName = `${userData.firstName} ${userData.lastName}`;
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
