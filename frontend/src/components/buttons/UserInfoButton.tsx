import style from "./UserInfoButton.module.css";
import { FaUser } from "react-icons/fa";
import { BiSolidSpreadsheet } from "react-icons/bi";
import { FaBookmark } from "react-icons/fa";
import { FaCheckCircle } from "react-icons/fa";
import { FaGear } from "react-icons/fa6";
import { IoLogOut } from "react-icons/io5";
import { Link } from "react-router-dom";
import { clearUserData } from "../../utils/userStorage";
import { TfiPanel } from "react-icons/tfi";
import { SiGoogleforms } from "react-icons/si";

interface UserInfoButtonProps {
  type:
    | "profile"
    | "cv"
    | "bookmarks"
    | "applications"
    | "config"
    | "logout"
    | "control-panel"
    | "create-job-offer";
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
  logout: { text: "Cerrar sesión", icon: IoLogOut, path: "/" },
  "control-panel": {
    text: "Panel de control",
    icon: TfiPanel,
    path: "/panel-de-control",
  },
  "create-job-offer": {
    text: "Crear oferta de empleo",
    icon: SiGoogleforms,
    path: "/crear-oferta-de-empleo",
  },
};

const UserInfoButton: React.FC<UserInfoButtonProps> = ({ type }) => {
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
      className={style.container}
      onClick={() => type === "logout" && handleLogout()}
    >
      <IconComponent />
      <p>{option.text}</p>
    </Link>
  );
};

export default UserInfoButton;
