export interface Project {
  title: string;
  url: string;
  image?: string;
}

export interface UserData {
  id: string;
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
  // Estos datos son temporales, mera decoraccion en espera de api
  image?: string;
  portfolio?: Project[];
  biography?: string;
}

export interface CompleteUserInfoData {
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  skills: string[];
  resumeUrl: string;
}

export interface RecruiterRoleData {
  companyName: string;
  companyCountry: string;
  companyAddress: string;
  companyEmail: string;
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

export interface CreateJobPostData extends BaseJobPost {
  maxCandidates: number;
}

export interface JobPostData extends BaseJobPost {
  id: string;
  maxCandidates: number;
  isActive: boolean;
  candidates: number;
  candidatesApplied: number;
  createdAt: string;
  applied: boolean;
}
