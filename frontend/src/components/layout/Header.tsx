import { useWindowSize } from "../../hooks/useWindowsSize";
import DesktopHeader from "./DesktopHeader";
import MobileHeader from "./MobileHeader";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

/**
 * Componente que renderiza un header según el tamaño de la pantalla.
 * Si el ancho de la pantalla es mayor a 768px, se renderiza un header para escritorio,
 * de lo contrario, se renderiza un header para dispositivos móviles.
 *
 * @param {string} placeholder - Texto para mostrar en la barra de búsqueda.
 * @param {string} navigateTo - Ruta a la cual se dirige la barra de búsqueda.
 * @returns {JSX.Element} - Elemento JSX que representa el header.
 *
 * @example
 * <Header placeholder="Buscar..." navigateTo="empleos" />
 */
const Header = ({ placeholder, navigateTo }: HeaderProps) => {
  const windowSize = useWindowSize();
  return (
    <>
      {windowSize.width > 768 ? (
        <DesktopHeader placeholder={placeholder} navigateTo={navigateTo} />
      ) : (
        <MobileHeader placeholder={placeholder} navigateTo={navigateTo} />
      )}
    </>
  );
};

export default Header;
