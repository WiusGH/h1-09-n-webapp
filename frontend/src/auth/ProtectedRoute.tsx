import { Navigate, Outlet } from "react-router-dom";
import { getUserData } from "../utils/userStorage";

interface ProtectedRouteProps {
  roles?: Array<"CANDIDATE" | "RECRUITER" | "ADMIN">;
}

/**
 * Componente que protege una ruta con autenticación.
 * Si el usuario no está logueado, se redirige a la página de inicio de sesión.
 * Si el usuario está logueado pero no tiene el rol incorrecto, se redirige a la página de inicio.
 * Si el usuario está logueado y tiene el rol correcto, se renderiza el contenido de la ruta.
 * @ejemplo <Route element={<ProtectedRoute roles={["CANDIDATE"]} />}> {rutas} </Route>
 * @param {ProtectedRouteProps} props - Props para el componente.
 * @returns {JSX.Element} - JSX element.
 */
const ProtectedRoute = ({ roles }: ProtectedRouteProps) => {
  const user = getUserData();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (roles && !roles.includes(user.role)) {
    return <Navigate to="/" replace />;
  }

  return <Outlet />;
};

export default ProtectedRoute;
