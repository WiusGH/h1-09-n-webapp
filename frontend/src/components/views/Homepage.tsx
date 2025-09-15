import { Link } from "react-router-dom";
import { isLoggedIn, isProfileComplete } from "../../utils/userStorage";
import style from "./Homepage.module.css";
import { FaExternalLinkAlt } from "react-icons/fa";

const Homepage = () => {
  return (
    <section className={style.container}>
      <div className={`${style.section} ${style.offers}`}>
        {/* Mockup de momento pero lo ideal es que obtengo 3 empleos al azar de la base de datos según las habilidades del usuario */}
        {isLoggedIn() && isProfileComplete() ? (
          <>
            <h3>Ofertas para tí</h3>
            <div className={style.offer}>
              <p>Empresa digital - Desarrollador/a Frontend</p>
            </div>
            <div className={style.offer}>
              <p>Empresa Innovación - Desarrollador/a backend</p>
            </div>
            <div className={style.offer}>
              <p>Empresa MegaTech - Desarrollador/a Fullstack</p>
            </div>
          </>
        ) : (
          <Link to={!isLoggedIn() ? "/login" : "/completar-perfil"}>
            {!isLoggedIn()
              ? "Inicia sesión para ver las ofertas recomendadas"
              : "Completa tu perfil para ver las ofertas recomendadas"}
          </Link>
        )}
      </div>
      {/* Mockup para agregar contenido al homepage y que no necesitará uso de API para el MVP */}
      <div className={`${style.section} ${style.tips}`}>
        <h3>Tips para aumentar empleabilidad</h3>
        <ul>
          <li>Mantén tu CV siempre actualizado.</li>
          <li>Conecta con personas que encajen con tu perfil.</li>
          <li>Aprovecha los cursos y descuentos de Alura.</li>
          <li>
            Pon en práctica tus conocimientos con las simualciones laborales de
            No Country.
          </li>
        </ul>
      </div>
      <div className={`${style.section} ${style.links}`}>
        <h3>Enlaces de interés</h3>
        <div className={style.link}>
          <h4>
            Simulaciones laborales <FaExternalLinkAlt />
          </h4>
          <a>Trabaja en un entorno laboral simulado con personas reales.</a>
        </div>
        <div className={style.link}>
          <h4>
            Aprende las buenas prácticas <FaExternalLinkAlt />
          </h4>
          <a>
            Aprende las buenas prácticas de programación para aprender a
            trabajar mejor en equipo.
          </a>
        </div>
        <div className={style.link}>
          <h4>
            Descuentos en cursos <FaExternalLinkAlt />
          </h4>
          <a>Acceede a los descuentos de cursos de Alura.</a>
        </div>
      </div>
    </section>
  );
};

export default Homepage;
