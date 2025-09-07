import type { ReactNode } from "react";
import { useState, useEffect } from "react";
import { AuthContext } from "./AuthContext";
import { isLoggedIn, clearUserData, getUserData } from "../utils/userStorage";

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

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    if (isLoggedIn()) {
      setUser(getUserData());
    }
  }, []);

  const login = (userData: User) => {
    setUser(userData);
  };

  const logout = () => {
    clearUserData();
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
