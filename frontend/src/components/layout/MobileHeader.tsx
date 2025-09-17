import { useState, useEffect, useRef } from "react";
import style from "./Layout.module.css";
import { useHideOnScroll } from "../../hooks/useHideOnScroll";
import CustomSearchBar from "../inputs/CustomSearchBar";
import NavbarIcon from "../buttons/NavbarIcon";
import ThemeToggle from "../buttons/ThemeToggle";
import { FaBars } from "react-icons/fa";
import { isLoggedIn } from "../../utils/userStorage";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

/**
 * Componente que renderiza un header para dispositivos móviles.
 *
 * @param {string} placeholder - Texto para mostrar en la barra de búsqueda.
 * @param {string} navigateTo - Ruta a la cual se dirige la barra de búsqueda.
 * @returns {JSX.Element} - Elemento JSX que representa el header.
 *
 * @example
 * <MobileHeader placeholder="Buscar..." navigateTo="empleos" />
 */
const MobileHeader: React.FC<HeaderProps> = ({ placeholder, navigateTo }) => {
  const show = useHideOnScroll();
  const [menuOpen, setMenuOpen] = useState(false);
  const menuRef = useRef<HTMLDivElement>(null);

  // Para cerrar el menú al hacer scroll
  useEffect(() => {
    const handleScroll = () => setMenuOpen(false);
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  // Para cerrar el menú cuando el usuario clickee afuera del mismo
  useEffect(() => {
    const handleClickOutside = (e: MouseEvent) => {
      if (menuRef.current && !menuRef.current.contains(e.target as Node)) {
        setMenuOpen(false);
      }
    };
    if (menuOpen) {
      document.addEventListener("mousedown", handleClickOutside);
    }
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, [menuOpen]);

  return (
    <>
      {menuOpen && (
        <div className={style.overlay} onClick={() => setMenuOpen(false)} />
      )}
      <header
        className={`${style.header} ${show ? style.visible : style.hidden}`}
      >
        {" "}
        <button
          className={style.hamburger}
          onClick={() => setMenuOpen((prev) => !prev)}
          aria-label="Abrir o cerrar menú de navegación"
          aria-expanded={menuOpen}
          aria-controls="mobile-menu"
        >
          <FaBars />
        </button>
        <div
          ref={menuRef}
          className={`${style.mobileMenu} ${menuOpen ? style.open : ""}`}
        >
          <nav
            id="mobile-menu"
            role="navigation"
            aria-label="Navegación"
            aria-hidden={!menuOpen}
            onClick={() => setMenuOpen(false)}
          >
            <NavbarIcon type="home" sidebar />
            <NavbarIcon type="jobs" sidebar />
            {isLoggedIn() ? (
              <>
                <NavbarIcon type="messages" sidebar />
                <NavbarIcon type="notifications" sidebar />
                <NavbarIcon type="logout" sidebar />
              </>
            ) : (
              <NavbarIcon type="login" sidebar />
            )}
          </nav>
        </div>
        <section className={style.searchBar}>
          <CustomSearchBar placeholder={placeholder} navigateTo={navigateTo} />
        </section>
        <div aria-label="Cambiar tema">
          <ThemeToggle />
        </div>
      </header>
    </>
  );
};

export default MobileHeader;
