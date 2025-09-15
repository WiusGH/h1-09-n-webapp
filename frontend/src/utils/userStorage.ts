import { jwtDecode } from "jwt-decode";
import type { UserData } from "../types/Types";

// Para obtener la fecha de vencimiento del token
interface JwtPayload {
  exp: number;
  iat: number;
}

const USER_KEY = "userData";

// Para guardar datos en localStorage
export function saveUserData(user: UserData) {
  localStorage.setItem(USER_KEY, JSON.stringify(user));
}

// Para obtener datos del usuario del localStorage (null si no está logueado) y también revisar si el toekn está expirado
export function getUserData(): UserData | null {
  const stored = localStorage.getItem(USER_KEY);
  if (!stored) return null;

  const parsed: UserData = JSON.parse(stored);

  if (parsed?.token) {
    try {
      const decoded = jwtDecode<JwtPayload>(parsed.token);
      const now = Date.now() / 1000;
      if (decoded.exp < now) {
        clearUserData();
        return null;
      }
    } catch (err) {
      console.error("Invalid token", err);
      clearUserData();
      return null;
    }
  }

  return parsed;
}

// Para eliminar datos del usuario al cerrar sesión
export function clearUserData() {
  localStorage.removeItem(USER_KEY);
}

// Para revisar si el usuario esta logueado y revisar si el token aún es válido
export function isLoggedIn(): boolean {
  return !!getUserData();
}

export function isProfileComplete(): boolean {
  const userData = getUserData();
  return !!userData?.country && !!userData?.address && !!userData?.phoneNumber;
}
