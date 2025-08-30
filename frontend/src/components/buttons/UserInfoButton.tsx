import style from "./UserInfoButton.module.css";
import { FaUser } from "react-icons/fa";
import { BiSolidSpreadsheet } from "react-icons/bi";
import { FaBookmark } from "react-icons/fa";
import { FaCheckCircle } from "react-icons/fa";
import { FaGear } from "react-icons/fa6";
import { IoLogOut } from "react-icons/io5";
import { Link } from "react-router-dom";

interface UserInfoButtonProps {
  type: "profile" | "cv" | "bookmarks" | "applications" | "config" | "logout";
}

const options = {
  profile: { text: "Perfil", icon: FaUser, path: "/perfil" },
  cv: { text: "CV", icon: BiSolidSpreadsheet, path: "/cv" },
  bookmarks: { text: "Favoritos", icon: FaBookmark, path: "/favoritos" },
  applications: {
    text: "Aplicaciones",
    icon: FaCheckCircle,
    path: "/aplicaciones",
  },
  config: { text: "Configuración", icon: FaGear, path: "/configuracion" },
  logout: { text: "Cerrar sesión", icon: IoLogOut, path: "/logout" },
};

const UserInfoButton: React.FC<UserInfoButtonProps> = ({ type }) => {
  const option = options[type];

  if (!option) return null;

  const IconComponent = option.icon;

  return (
    <Link to={option.path} className={style.container}>
      <IconComponent />
      <p>{option.text}</p>
    </Link>
  );
};

export default UserInfoButton;
