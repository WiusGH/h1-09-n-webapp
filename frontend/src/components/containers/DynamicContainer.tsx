import React from "react";
import style from "./DynamicContainer.module.css";
import { useWindowSize } from "../../hooks/useWindowsSize";

interface DynamicContainerProps {
  main: React.ReactNode;
  side?: React.ReactNode;
}

/**
 * Renderiza un contenedor dinamico que puede tener dos columnas
 * Si se pasa la propiedad 'side', se renderiza una columna adicional
 * con el contenido de la propiedad 'side'
 * De lo contrario, se renderiza un contenedor con una sola columna
 * con el contenido de la propiedad 'main'
 * @param main - Contenido de la primera columna
 * @param main - (Opcional) Contenido de la segunda columna
 * @returns {JSX.Element} Contenedor dinamico
 */
function DynamicContainer({ main, side }: DynamicContainerProps) {
  const isMobile = useWindowSize().width < 768;
  return (
    <div
      className={`${style.container} ${side && !isMobile ? style.twoCol : ""}`}
    >
      {isMobile ? (
        <>
          {side && <aside className={style.side}>{side}</aside>}
          <div className={style.main}>{main}</div>
        </>
      ) : (
        <>
          <div className={style.main}>{main}</div>
          {side && <aside className={style.side}>{side}</aside>}
        </>
      )}
    </div>
  );
}

export default DynamicContainer;
