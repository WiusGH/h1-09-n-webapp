export interface UserData {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  role: "CANDIDATE" | "RECRUITER" | "ADMIN";
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  token?: string;
}

export interface jobOfferData {
  id: string;
  title: string;
  companyName: string;
  description: string;
  country: string;
  address: string;
  phoneNumber: string;
  applied: boolean;
}
