import { useEffect } from "react";
import { useLocation } from "react-router-dom";

// Función para ir al inicio de la pantalla
export const ScrollToTop = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
};
