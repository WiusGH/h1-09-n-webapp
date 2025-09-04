import style from "./ProjectCard.module.css";

interface Props {
  image: string;
  title: string;
}

const ProjectCard: React.FC<Props> = ({ image, title }) => {
  return (
    <div className={style.container}>
      <img src={image} alt={title} />
      <h4>{title}</h4>
    </div>
  );
};

export default ProjectCard;
