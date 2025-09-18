import React from "react";
import ProjectCard from "../../cards/ProjectCard";
import GenericButton from "../buttons/GenericButton";
import SkillTag from "../buttons/SkillTag";
import style from "./ProfileView.module.css";
import { LuPencilLine } from "react-icons/lu";
import type { UserData } from "../../types/Types";
import { Link } from "react-router-dom";

interface ProfileViewProps {
  user: UserData | null;
  onEdit: (section: string) => void;
}

/**
 * Componente que renderiza una vista de perfil para el usuario logueado.
 *
 * @prop user - Los datos del usuario que se están mostrando.
 * @prop onEdit - Función que se llama cuando se hace clic en el botón de editar.
 * @returns {JSX.Element} - Elemento JSX que representa la vista de perfil del usuario.
 */
const ProfileView: React.FC<ProfileViewProps> = ({ user, onEdit }) => {
  if (!user) {
    return (
      <div className={style.container}>
        <h3>No estás logueado</h3>
      </div>
    );
  }

  const formatRole = (role: string) => {
    return role.charAt(0).toUpperCase() + role.slice(1).toLowerCase();
  };

  return (
    <div className={style.container}>
      <div className={style.profile}>
        <div className={style.imageContainer}>
          <img
            src={user.image || `https://avatar.iran.liara.run/public/29`}
            alt="Foto de perfil"
          />
          <a onClick={() => onEdit("image")} className={style.editImageIcon}>
            <LuPencilLine />
          </a>
        </div>
        <div className={style.info}>
          <div className={style.header}>
            <h3>
              {user.name} {user.lastName}
            </h3>
          </div>
          <p className={style.role}>{formatRole(user.role)}</p>
          <p>{user.title || "Sin ocupación"}</p>
        </div>
        <div style={{ flex: 1 }}></div>
        <ul className={style.options}>
          <li>
            <a onClick={() => onEdit("personal")}>Editar Perfil</a>
          </li>
          <li>
            <a onClick={() => onEdit("contact")}>Editar Contacto</a>
          </li>
          <li>
            <a onClick={() => onEdit("cv")}>Editar CV</a>
          </li>
        </ul>
      </div>

      <div className={style.skills}>
        <h3>Skills:</h3>
        {(user.skills || []).map((skill, idx) => (
          <SkillTag key={idx} skill={skill} />
        ))}
        <a onClick={() => onEdit("skills")}>
          <LuPencilLine />
        </a>
      </div>

      <div className={style.bioContainer}>
        <h3>Biografía:</h3>
        <textarea
          className={style.description}
          value={user.biography || "Descripción..."}
          readOnly
        ></textarea>
      </div>

      <div className={style.portfolioHeader}>
        <h3>Portafolio:</h3>
        <GenericButton text="Agregar" onClick={() => onEdit("portfolio")} />
      </div>

      <div className={style.projectsContainer}>
        {(user.portfolio || []).map((item, idx) => (
          <ProjectCard
            key={idx}
            image={item.image || "https://avatar.iran.liara.run/public/29"}
            title={item.title}
          />
        ))}
      </div>
      {user.profileCompleted && (
        <Link to="/solicitar-ser-recruiter">
          <GenericButton text="Ser Recruiter" />
        </Link>
      )}
    </div>
  );
};

export default ProfileView;
