import { useEffect, useState } from "react";
import ProjectCard from "../../cards/ProjectCard";
import GenericButton from "../buttons/GenericButton";
import SkillTag from "../buttons/SkillTag";
import style from "./ProfileView.module.css";
import { LuPencilLine } from "react-icons/lu";
import { getUserData } from "../../utils/userStorage";
import type { UserData } from "../../types/Types";

const ProfileView = () => {
  const [user, setUser] = useState<UserData | null>(null);

  useEffect(() => {
    const storedUser = getUserData();
    if (storedUser) {
      setUser(storedUser);
    }
  }, []);

  const sampleList = [
    {
      title: "Proyecto 1",
      image: "https://avatar.iran.liara.run/public/29",
    },
    {
      title: "Proyecto 2",
      image: "https://avatar.iran.liara.run/public/29",
    },
    {
      title: "Proyecto 3",
      image: "https://avatar.iran.liara.run/public/29",
    },
    {
      title: "Proyecto 4",
      image: "https://avatar.iran.liara.run/public/29",
    },
    {
      title: "Proyecto 1",
      image: "https://avatar.iran.liara.run/public/29",
    },
    {
      title: "Proyecto 5",
      image: "https://avatar.iran.liara.run/public/29",
    },
  ];

  if (!user) {
    return (
      <div className={style.container}>
        <h3>No estás logueado</h3>
      </div>
    );
  }

  return (
    <div className={style.container}>
      <div className={style.profile}>
        <img
          src={`https://avatar.iran.liara.run/public/${user.id}`} // temporal avatar fallback
          alt="Foto de perfil"
        />
        <div className={style.info}>
          <span>
            <h3>
              {user.firstName} {user.lastName}
            </h3>
            <a>
              <LuPencilLine />
            </a>
          </span>
          <span>
            <p>{user.title || "Sin ocupación"}</p>
            <a>
              <LuPencilLine />
            </a>
          </span>
        </div>
        <div style={{ flex: 1 }}></div>
        <ul className={style.options}>
          <li>
            <a>Editar cv</a>
          </li>
          <li>
            <a>Editar contacto</a>
          </li>
          <li>
            <a>Editar foto</a>
          </li>
        </ul>
      </div>

      <div className={style.skills}>
        <h3>Skills:</h3>
        {/* TODO: reemplazar con skills reales desde backend */}
        <SkillTag skill="html" />
        <SkillTag skill="css" />
        <SkillTag skill="javascript" />
        <SkillTag skill="react" />
      </div>

      <textarea className={style.description}>
        {user.address || "Descripción..."}
      </textarea>

      <div className={style.portfolioContainer}>
        <div className={style.portfolioHeader}>
          <h3>Portafolio:</h3>
          <GenericButton text="Agregar" />
        </div>
      </div>

      <div className={style.projectsContainer}>
        {sampleList.map((item, idx) => (
          <ProjectCard key={idx} image={item.image} title={item.title} />
        ))}
      </div>
    </div>
  );
};

export default ProfileView;
