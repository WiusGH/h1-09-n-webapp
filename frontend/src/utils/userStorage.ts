import { jwtDecode } from "jwt-decode";

// Este es un modelo de datos para el usuario
export interface UserData {
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
      const now = Date.now() / 1000; // current time in seconds
      if (decoded.exp < now) {
        // Token expired → clear and return null
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
