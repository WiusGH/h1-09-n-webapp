import { jwtDecode } from "jwt-decode";
import type { userData } from "../types/Types";

// Para obtener la fecha de vencimiento del token
interface JwtPayload {
  exp: number;
  iat: number;
}

const USER_KEY = "userData";

/**
 * Guarda la información del usuario en localStorage.
 *
 * @param {userData} user - Información del usuario a guardar.
 */
export function saveUserData(user: userData) {
  localStorage.setItem(USER_KEY, JSON.stringify(user));
}

/**
 * Obtiene la información del usuario guardada en localStorage.
 *
 * @returns {userData | null} La información del usuario si existe, null en caso contrario.
 *
 * @throws {Error} Si el token es inválido o ha expirado.
 */
export function getUserData(): userData | null {
  const stored = localStorage.getItem(USER_KEY);
  if (!stored) return null;

  let parsed: userData;
  try {
    parsed = JSON.parse(stored);
  } catch {
    clearUserData();
    return null;
  }

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

/**
 * Elimina los datos del usuario del localStorage.
 * Esto se utiliza cuando se requiere eliminar la sesión del usuario.
 */
export function clearUserData() {
  localStorage.removeItem(USER_KEY);
}

/**
 * Comprueba si existe un usuario logueado.
 *
 * @returns {boolean} - true si el usuario está logueado, false en caso contrario
 */
export function isLoggedIn(): boolean {
  return !!getUserData();
}

/**
 * Comprueba si el usuario actualmente logueado está activo.
 *
 * @returns {boolean} - true si el usuario está activo, false en caso contrario
 */
export function isActive(): boolean {
  const user = getUserData();
  return !!user?.active;
}

/**
 * Comprueba si el perfil del usuario actualmente logueado está completado.
 *
 * @returns {boolean} - true si el Perfil del usuario está completo, false en caso contrario
 */
export function isProfileComplete(): boolean {
  const userData = getUserData();
  return userData?.profileCompleted ?? false;
}
