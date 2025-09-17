import React from "react";
import style from "./NavbarIcon.module.css";
import { Link } from "react-router-dom";
import { FaHouse, FaBriefcase, FaBell } from "react-icons/fa6";
import { IoChatbubble } from "react-icons/io5";
import { IoLogIn } from "react-icons/io5";
import { IoLogOut } from "react-icons/io5";
import { clearUserData } from "../../utils/userStorage";

const HouseIcon = FaHouse;
const BriefcaseIcon = FaBriefcase;
const ChatIcon = IoChatbubble;
const BellIcon = FaBell;

interface NavbarIconProps {
  type: "home" | "jobs" | "messages" | "notifications" | "login" | "logout";
  sidebar?: boolean;
}

const options = {
  home: { text: "Inicio", icon: HouseIcon, path: "/" },
  jobs: { text: "Empleos", icon: BriefcaseIcon, path: "/empleos" },
  messages: { text: "Mensajes", icon: ChatIcon, path: "/mensajes" },
  notifications: {
    text: "Notificaciones",
    icon: BellIcon,
    path: "/notificaciones",
  },
  login: { text: "Iniciar sesión", icon: IoLogIn, path: "/login" },
  logout: { text: "Cerrar sesión", icon: IoLogOut, path: "/" },
};

/**
 * Componente que renderiza un botón de navegación con ícono y texto.
 *
 * @param {string} props.type - Tipo de botón de navegación.
 * @param {boolean} props.sidebar - (Opcional) Si el componente se encuentra en una barra lateral.
 *
 * @returns {JSX.Element} - Elemento JSX que representa el botón de navegación.
 *
 * @example
 * <NavbarIcon type="Inicio" sidebar={true} />
 */
const NavbarIcon: React.FC<NavbarIconProps> = ({ type, sidebar }) => {
  const option = options[type];

  if (!option) return null;

  const IconComponent = option.icon;

  const handleLogout = () => {
    clearUserData();
    window.location.reload();
  };

  return (
    <Link
      to={option.path}
      onClick={() => type === "logout" && handleLogout()}
      className={`${style.menuItem} ${sidebar ? style.sidebarMode : ""}`}
    >
      <IconComponent />
      <p>{option.text}</p>
    </Link>
  );
};

export default NavbarIcon;
