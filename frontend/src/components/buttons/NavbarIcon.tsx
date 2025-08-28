import React from "react";
import style from "./NavbarIcon.module.css";
import { Link } from "react-router-dom";
import { FaHouse, FaBriefcase, FaBell } from "react-icons/fa6";
import { IoChatbubble } from "react-icons/io5";
import { IoLogIn } from "react-icons/io5";
import { IoLogOut } from "react-icons/io5";

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
  logout: { text: "Cerrar sesión", icon: IoLogOut, path: "/logout" },
};

const NavbarIcon: React.FC<NavbarIconProps> = ({ type, sidebar }) => {
  const option = options[type];

  if (!option) return null;

  const IconComponent = option.icon;

  return (
    <Link
      to={option.path}
      className={`${style.menuItem} ${sidebar ? style.sidebarMode : ""}`}
    >
      <IconComponent />
      <p>{option.text}</p>
    </Link>
  );
};

export default NavbarIcon;
