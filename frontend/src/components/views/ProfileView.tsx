import React from "react";
import ProjectCard from "../../cards/ProjectCard";
import GenericButton from "../buttons/GenericButton";
import SkillTag from "../buttons/SkillTag";
import style from "./ProfileView.module.css";
import { LuPencilLine } from "react-icons/lu";
import type { UserData } from "../../types/Types";

interface ProfileViewProps {
  user: UserData | null;
  onEdit: (section: string) => void;
}

const ProfileView: React.FC<ProfileViewProps> = ({ user, onEdit }) => {
  if (!user) {
    return (
      <div className={style.container}>
        <h3>No estás logueado</h3>
      </div>
    );
  }

  // Función para capitalizar el rol
  const formatRole = (role: string) => {
    return role.charAt(0).toUpperCase() + role.slice(1).toLowerCase();
  };

  return (
    <div className={style.container}>
      <div className={style.profile}>
        <img
          src={user.image || `https://avatar.iran.liara.run/public/${user.id}`}
          alt="Foto de perfil"
        />
        <div className={style.info}>
          <span>
            <h3>
              {user.firstName} {user.lastName}
            </h3>
            <a onClick={() => onEdit("personal")}>
              <LuPencilLine />
            </a>
          </span>
          {/* Mostramos el rol del usuario aquí */}
          <p className={style.role}>{formatRole(user.role)}</p>
          <span>
            <p>{user.title || "Sin ocupación"}</p>
            <a onClick={() => onEdit("personal")}>
              <LuPencilLine />
            </a>
          </span>
        </div>
        <div style={{ flex: 1 }}></div>
        <ul className={style.options}>
          <li>
            <a onClick={() => onEdit("contact")}>Editar contacto</a>
          </li>
          <li>
            <a onClick={() => onEdit("personal")}>Editar foto</a>
          </li>
          <li>
            <a onClick={() => onEdit("cv")}>Editar cv</a>
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

      <textarea className={style.description} value={user.address || "Descripción..."} readOnly></textarea>
      
      <div className={style.portfolioHeader}>
        <h3>Portafolio:</h3>
        <GenericButton text="Agregar" onClick={() => onEdit("portfolio")} />
      </div>

      <div className={style.projectsContainer}>
        {(user.portfolio || []).map((item, idx) => (
          <ProjectCard key={idx} image={item.image || "https://avatar.iran.liara.run/public/29"} title={item.title} />
        ))}
      </div>
    </div>
  );
};

export default ProfileView;