import { Link } from "react-router-dom";
import style from "./Layout.module.css";
import { IoLogoWhatsapp } from "react-icons/io";
import { MdEmail } from "react-icons/md";

import { FaFacebook } from "react-icons/fa";
import { FaSquareXTwitter } from "react-icons/fa6";
import { FaSquareInstagram } from "react-icons/fa6";

const Footer = () => {
  return (
    <footer className={style.footer}>
      <div className={style.links}>
        <section>
          <h3>Mapa del sitio</h3>
          <ul>
            <li>
              <Link to={"/"}>Inicio</Link>
            </li>
            <li>
              <Link to={"/empleos"}>Empleos</Link>
            </li>
            <li>
              <Link to={"/candidatos"}>Candidatos</Link>
            </li>
          </ul>
        </section>
        <hr />
        <section>
          <h3>Contáctanos</h3>
          <a
            href="https://www.whatsapp.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            <IoLogoWhatsapp />
            Whatsapp
          </a>
          <a
            href="https://www.gmail.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            <MdEmail />
            Email
          </a>
        </section>
        <hr />
        <section>
          <h3>Nuestras redes</h3>
          <a
            href="https://www.facebook.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaFacebook />
            Facebook
          </a>
          <a
            href="https://www.instagram.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaSquareInstagram />
            Instagram
          </a>
          <a
            href="https://www.twitter.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            <span>
              <FaSquareXTwitter />
            </span>
            <span>Twitter</span>
          </a>
        </section>
      </div>
      <p>©2025 - Todos los derechos reservados</p>
    </footer>
  );
};

export default Footer;
