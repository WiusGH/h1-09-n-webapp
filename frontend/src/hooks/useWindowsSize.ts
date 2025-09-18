import { useState, useEffect } from "react";

interface WindowSize {
  width: number;
  height: number;
}

/**
 * Hook que devuelve el tamaño de la ventana actual.
 *
 * Devuelve un objeto con dos propiedades, width y height, que
 * representan el ancho y alto de la ventana actual, respectivamente.
 *
 * El hook utiliza el evento "resize" del objeto window para
 * detectar cambios en el tamaño de la ventana y actualizar
 * el estado según sea necesario.
 *
 * @returns {WindowSize} - Objeto con el tamaño de la ventana actual.
 */

export function useWindowSize(): WindowSize {
  const [windowSize, setWindowSize] = useState<WindowSize>({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  useEffect(() => {
    const handleResize = () => {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return windowSize;
}
