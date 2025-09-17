import style from "./Layout.module.css";
import { useHideOnScroll } from "../../hooks/useHideOnScroll";
import NavbarIcon from "../buttons/NavbarIcon";
import ThemeToggle from "../buttons/ThemeToggle";
import CustomSearchBar from "../inputs/CustomSearchBar";
import { isLoggedIn } from "../../utils/userStorage";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

/**
 * Componente que renderiza un header para dispositivos de escritorio.
 *
 * @param {string} placeholder - Texto para mostrar en la barra de búsqueda.
 * @param {string} navigateTo - Ruta a la cual se dirige la barra de búsqueda.
 * @returns {JSX.Element} - Elemento JSX que representa el header.
 *
 * @example
 * <DesktopHeader placeholder="Buscar..." navigateTo="empleos" />
 */
const DesktopHeader: React.FC<HeaderProps> = ({ placeholder, navigateTo }) => {
  const show = useHideOnScroll();
  return (
    <header
      className={`${style.header} ${show ? style.visible : style.hidden}`}
    >
      <div className={style.sideDivs}></div>
      <nav>
        <NavbarIcon type="home" />
      </nav>
      <section className={style.searchBar}>
        <CustomSearchBar placeholder={placeholder} navigateTo={navigateTo} />
      </section>
      <nav>
        <NavbarIcon type="jobs" />
        {isLoggedIn() ? (
          <>
            <NavbarIcon type="messages" />
            <NavbarIcon type="notifications" />
            <NavbarIcon type="logout" />
          </>
        ) : (
          <NavbarIcon type="login" />
        )}
      </nav>
      <div className={style.sideDivs}>
        <ThemeToggle />
      </div>
    </header>
  );
};

export default DesktopHeader;
