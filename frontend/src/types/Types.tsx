export interface Project {
  title: string;
  url: string;
  image?: string;
}

export interface userData {
  name: string;
  lastName: string;
  email: string;
  role: "CANDIDATE" | "RECRUITER" | "ADMIN";
  active: boolean;
  profileCompleted: boolean;
  createdAt: string;
  token: string;
  title?: string;
  country?: string;
  address?: string;
  phoneNumber?: string;
  skills?: string[];
  resumeUrl?: string;
  companyName?: string;
  companyCountry?: string;
  companyEmail?: string;
  companyAddress?: string;
  // image?: string; // Estos datos son temporales, mera decoraccion en espera de api
  // portfolio?: Project[];
  // biography?: string;
}

export interface completeUserInfoData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  skills: string[];
  resumeUrl: string;
}

export interface recruiterRequestData {
  companyName: string;
  companyCountry: string;
  companyAddress: string;
  companyEmail: string;
  userId: string;
}

interface BaseJobPost {
  title: string;
  description: string;
  skills: string[];
  recruiterName: string;
  companyName: string;
  companyCountry: string;
  companyAddress: string;
  companyEmail: string;
}

export interface createJobPostData extends BaseJobPost {
  maxCandidates: number;
}

export interface jobPostData extends BaseJobPost {
  id: string;
  maxCandidates: number;
  isActive: boolean;
  candidates: number;
  candidatesApplied: number;
  createdAt: string;
}
