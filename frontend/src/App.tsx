// App.tsx
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

function App() {
  useEffect(() => {
    initTheme();
  }, []);

  const [userType, setUserType] = useState<string | null>(null);

  useEffect(() => {
    const storedUserType = localStorage.getItem("userType");
    setUserType(storedUserType);
  }, []);

  let searchPlaceholder = "Buscar...";
  let navigateTo = "search";

  if (userType === "candidate") {
    searchPlaceholder = "Buscar empleos";
    navigateTo = "empleos";
  } else if (userType === "recruiter") {
    searchPlaceholder = "Buscar candidatos";
    navigateTo = "candidatos";
  } else if (userType === "admin") {
    searchPlaceholder = "Buscar usuarios";
    navigateTo = "usuarios";
  }

  return (
    <Router>
      <ScrollToTop />
      <div className="app-container">
        <Header placeholder={searchPlaceholder} navigateTo={navigateTo} />
        <main className="app-main">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/mensajes" element={<Mensajes />} />
            <Route path="/empleos" element={<Empleos />} />
            <Route path="/notifiaciones" element={<Notificaciones />} />
            <Route path="*" element={<h1>404 - Not Found</h1>} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
