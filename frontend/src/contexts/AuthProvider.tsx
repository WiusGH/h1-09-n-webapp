import type { ReactNode } from "react";
import { useState, useEffect } from "react";
import { AuthContext } from "./AuthContext";
import { isLoggedIn, clearUserData, getUserData } from "../utils/userStorage";
import type { UserData } from "../types/Types";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<UserData | null>(null);

  useEffect(() => {
    if (isLoggedIn()) {
      setUser(getUserData());
    }
  }, []);

  const login = (userData: UserData) => {
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
