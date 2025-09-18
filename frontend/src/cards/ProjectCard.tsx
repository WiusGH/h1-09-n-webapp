import style from "./ProjectCard.module.css";

interface Props {
  image: string;
  title: string;
}

/**
 * Componente que renderiza una tarjeta para mostrar un proyecto.
 *
 * @param image - URL de la imágen del proyecto.
 * @param title - Título del proyecto.
 * @returns {JSX.Element} - Elemento JSX que representa la tarjeta del proyecto.
 */
const ProjectCard: React.FC<Props> = ({ image, title }) => {
  return (
    <div className={style.container}>
      <img src={image} alt={title} />
      <h4>{title}</h4>
    </div>
  );
};

export default ProjectCard;
