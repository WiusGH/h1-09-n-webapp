import { Navigate, Outlet } from "react-router-dom";
import { getUserData } from "../utils/userStorage";

interface ProtectedRouteProps {
  roles?: Array<"CANDIDATE" | "RECRUITER" | "ADMIN">;
}

// Para proteger rutas dependiendo del tipo de usuario
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
