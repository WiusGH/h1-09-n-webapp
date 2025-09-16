import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import { ScrollToTop } from "./utils/ScrollToTop";
import { initTheme } from "./utils/theme";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Mensajes from "./pages/Mensajes";
import Empleos from "./pages/Empleos";
import Notificaciones from "./pages/Notificaciones";
import "./index.css";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import NotFound from "./pages/NotFound/NotFound";
import ProtectedRoute from "./auth/ProtectedRoute";
import CreateJobOffer from "./pages/CreateJobOffer";
import CompleteProfile from "./pages/CompleteProfile";
import RequestRecuiterUserType from "./pages/RequestRecuiterUserType";

// Carga del tema
function App() {
  useEffect(() => {
    initTheme();
  }, []);

  // Esto servirá después para saber que tipo de usuario está logueado
  const [userType, setUserType] = useState<string | null>(null);

  // Esto sirve para obtener el tipp de usuario del localStorage cuando el usuario haya iniciado sesión
  useEffect(() => {
    const storedUserType = localStorage.getItem("userType");
    setUserType(storedUserType);
  }, []);

  // Placeholder predefinidos
  let searchPlaceholder = "Buscar...";
  let navigateTo = "search";

  if (userType === "CANDIDATE") {
    searchPlaceholder = "Buscar empleos";
    navigateTo = "empleos";
  } else if (userType === "RECRUITER") {
    searchPlaceholder = "Buscar candidatos";
    navigateTo = "candidatos";
  } else if (userType === "ADMIN") {
    searchPlaceholder = "Buscar usuarios";
    navigateTo = "usuarios";
  }

  return (
    <Router>
      <ScrollToTop />
      <main className="app-container">
        <Header placeholder={searchPlaceholder} navigateTo={navigateTo} />
        <div className="app-main">
          <Routes>
            {/* Rutas públicas */}
            <Route path="/" element={<Home />} />
            <Route path="/empleos" element={<Empleos />} />
            <Route path="/login" element={<Login />} />
            <Route path="/registro" element={<Register />} />
            <Route path="*" element={<NotFound />} />{" "}
            {/* TODO: crear vista 404 */}
            {/* Rutas para usuarios logueados */}
            <Route element={<ProtectedRoute roles={["CANDIDATE"]} />}>
              <Route path="/completar-perfil" element={<CompleteProfile />} />
              <Route
                path="/solicitar-ser-recruiter"
                element={<RequestRecuiterUserType />}
              />
            </Route>
            <Route
              element={
                <ProtectedRoute roles={["CANDIDATE", "RECRUITER", "ADMIN"]} />
              }
            >
              <Route path="/perfil" element={<Profile />} />
              <Route path="/mensajes" element={<Mensajes />} />
              <Route path="/notificaciones" element={<Notificaciones />} />
            </Route>
            {/* Rutas para reclutadores y admins */}
            <Route element={<ProtectedRoute roles={["RECRUITER", "ADMIN"]} />}>
              {/* <Route path="/candidatos" element={<Candidatos />} /> */}
              <Route path="/nueva-oferta" element={<CreateJobOffer />} />
            </Route>
            {/* Rutas solo para admins y admins */}
            <Route element={<ProtectedRoute roles={["ADMIN"]} />}>
              {/* <Route path="/panel-control" element={<ControlPanel />} /> */}
            </Route>
          </Routes>
        </div>
        <Footer />
      </main>
    </Router>
  );
}

export default App;
