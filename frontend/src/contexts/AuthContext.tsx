import { createContext } from "react";

interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  role: "candidate" | "recruiter" | "admin";
  title: string;
  country: string;
  address: string;
  phoneNumber: string;
  token?: string;
}

interface AuthContextProps {
  user: User | null;
  login: (user: User) => void;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextProps | null>(null);
