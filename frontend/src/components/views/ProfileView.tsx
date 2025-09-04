import ProjectCard from "../../cards/ProjectCard";
import GenericButton from "../buttons/GenericButton";
import SkillTag from "../buttons/SkillTag";
import style from "./ProfileView.module.css";
import { LuPencilLine } from "react-icons/lu";

const ProfileView = () => {
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
  return (
    <div className={style.container}>
      <div className={style.profile}>
        <img src="https://avatar.iran.liara.run/public/29"></img>
        <div className={style.info}>
          <span>
            <h3>Nombre de usuario</h3>
            <a>
              <LuPencilLine />
            </a>
          </span>
          <span>
            <p>Ocupación</p>
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
        <SkillTag skill="html" />
        <SkillTag skill="css" />
        <SkillTag skill="javascript" />
        <SkillTag skill="react" />
      </div>
      <textarea className={style.description}>Descripción...</textarea>
      <div className={style.portfolioContainer}>
        <div className={style.portfolioHeader}>
          <h3>Portafolio:</h3>
          <GenericButton text="Agregar" />
        </div>
      </div>
      <div className={style.projectsContainer}>
        {sampleList.map((item) => (
          <ProjectCard image={item.image} title={item.title} />
        ))}
      </div>
    </div>
  );
};

export default ProfileView;
