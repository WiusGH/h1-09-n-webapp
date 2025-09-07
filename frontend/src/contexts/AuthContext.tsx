import { createContext } from "react";
import type { UserData } from "../types/Types";

interface AuthContextProps {
  user: UserData | null;
  login: (user: UserData) => void;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextProps | null>(null);
