export interface Project {
  title: string;
  url: string;
  image?: string;
}

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
  image?: string; // Estos datos son temporales, mera decoraccion en espera de api
  skills?: string[]; 
  portfolio?: Project[]; 
  cvUrl?: string; 
}

// Existing jobOfferData interface
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
}

