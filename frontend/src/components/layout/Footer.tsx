import { Link } from "react-router-dom";
import style from "./Layout.module.css";
import { IoLogoWhatsapp } from "react-icons/io";
import { MdEmail } from "react-icons/md";
import { FaFacebook } from "react-icons/fa";
import { FaSquareXTwitter, FaSquareInstagram } from "react-icons/fa6";
import { getUserData } from "../../utils/userStorage";
import { useEffect, useState } from "react";
import type { UserData } from "../../types/Types";

// Enlaces de navegación de acuerdo al tipo de usuario
const navLinks: Record<string, { to: string; label: string }[]> = {
  CANDIDATE: [
    { to: "/", label: "Inicio" },
    { to: "/profile", label: "Perfil" },
    { to: "/empleos", label: "Empleos" },
  ],
  RECRUITER: [
    { to: "/", label: "Inicio" },
    { to: "/profile", label: "Perfil" },
    { to: "/nueva-oferta", label: "Publicar empleo" },
    { to: "/candidatos", label: "Buscar candidatos" },
  ],
  ADMIN: [
    { to: "/", label: "Inicio" },
    { to: "/empleos", label: "Empleos" },
    { to: "/candidatos", label: "Buscar candidatos" },
    { to: "/empleadores", label: "Buscar empleadores" },
  ],
  DEFAULT: [
    { to: "/", label: "Inicio" },
    { to: "/login", label: "Iniciar sesión" },
  ],
};

// Enlaces de contacto
const contactLinks = [
  {
    href: "https://www.whatsapp.com",
    label: "Whatsapp",
    icon: <IoLogoWhatsapp />,
  },
  { href: "https://www.gmail.com", label: "Email", icon: <MdEmail /> },
];

// Enlaces de redes sociales
const socialLinks = [
  { href: "https://www.instagram.com", icon: <FaSquareInstagram /> },
  { href: "https://www.facebook.com", icon: <FaFacebook /> },
  { href: "https://www.twitter.com", icon: <FaSquareXTwitter /> },
];

const Footer = () => {
  const [user, setUser] = useState<UserData | null>(null);

  useEffect(() => {
    const storedUser = getUserData();
    if (storedUser) {
      setUser(storedUser);
    }
  }, []);

  const role = user?.role ?? "DEFAULT";
  const links = navLinks[role] ?? navLinks.DEFAULT;

  return (
    <footer className={style.footer}>
      <div className={style.links}>
        <section>
          <h3>Mapa del sitio</h3>
          <ul>
            {links.map(({ to, label }) => (
              <li key={to}>
                <Link to={to}>{label}</Link>
              </li>
            ))}
          </ul>
        </section>
        <hr />
        <section>
          <h3>Contáctanos</h3>
          {contactLinks.map(({ href, label, icon }) => (
            <a
              key={label}
              href={href}
              target="_blank"
              rel="noopener noreferrer"
            >
              {icon} {label}
            </a>
          ))}
        </section>
        <hr />
        <section>
          <h3>Nuestras redes</h3>
          <div className={style.socials}>
            {socialLinks.map(({ href, icon }, i) => (
              <a key={i} href={href} target="_blank" rel="noopener noreferrer">
                {icon}
              </a>
            ))}
          </div>
        </section>
      </div>
      <p>©2025 - Todos los derechos reservados</p>
    </footer>
  );
};

export default Footer;
