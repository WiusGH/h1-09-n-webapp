import axios from "axios";
import { getUserData } from "../utils/userStorage";

// URL base de la API
const API_BASE_URL = "https://h1-09-n-webapp.onrender.com/api";

// Configuración de Axios
const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true,
});

// Para interceptar solicitudes y agregar el token
axiosInstance.interceptors.request.use((config) => {
  const user = getUserData();
  if (user?.token) {
    config.headers.Authorization = `Bearer ${user.token}`;
  }
  return config;
});

export default axiosInstance;
