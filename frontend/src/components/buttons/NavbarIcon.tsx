import React from "react";
import style from "./NavbarIcon.module.css";
import { Link } from "react-router-dom";
import { FaHouse, FaBriefcase, FaBell } from "react-icons/fa6";
import { IoChatbubble } from "react-icons/io5";

const HouseIcon = FaHouse;
const BriefcaseIcon = FaBriefcase;
const ChatIcon = IoChatbubble;
const BellIcon = FaBell;

interface NavbarIconProps {
  type: "home" | "jobs" | "messages" | "notifications";
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
};

const NavbarIcon: React.FC<NavbarIconProps> = ({ type }) => {
  const option = options[type];

  if (!option) return null;

  const IconComponent = option.icon;

  return (
    <Link to={option.path} className={style.menuItem}>
      {/* La linea de abajo es para evitar el error de TS */}
      {/* @ts-ignore */}
      <IconComponent />
      <p>{option.text}</p>
    </Link>
  );
};

export default NavbarIcon;
