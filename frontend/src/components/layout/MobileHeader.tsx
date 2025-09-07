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
        >
          <FaBars />
        </button>
        <div
          ref={menuRef}
          className={`${style.mobileMenu} ${menuOpen ? style.open : ""}`}
        >
          <nav onClick={() => setMenuOpen(false)}>
            <NavbarIcon type="home" sidebar />
            <NavbarIcon type="jobs" sidebar />
            <NavbarIcon type="messages" sidebar />
            <NavbarIcon type="notifications" sidebar />
            {isLoggedIn() ? (
              <NavbarIcon type="logout" sidebar />
            ) : (
              <NavbarIcon type="login" sidebar />
            )}
          </nav>
        </div>
        <section className={style.searchBar}>
          <CustomSearchBar placeholder={placeholder} navigateTo={navigateTo} />
        </section>
        <div>
          <ThemeToggle />
        </div>
      </header>
    </>
  );
};

export default MobileHeader;
