import axiosInstance from "../axiosInstance";
//  Example response
// {
//   "data": [
//     {
//       "userId": 0,
//       "companyName": "string",
//       "companyCountry": "string",
//       "companyAddress": "string",
//       "companyEmail": "string",
//       "approved": true
//     }
//   ],
//   "error": "string"
// }

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
  try {
    const response = await axiosInstance.get<ApiResponse<ResponseData[]>>(
      "/admins/recruiters/pending",
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
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
