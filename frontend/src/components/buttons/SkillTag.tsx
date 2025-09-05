import {
  SiJavascript,
  SiHtml5,
  SiCss3,
  SiReact,
  SiTypescript,
} from "react-icons/si";
import style from "./SkillTag.module.css";
import type { JSX } from "react";

interface SkillTagProps {
  skill: string;
}

const iconMap: Record<string, JSX.Element> = {
  javascript: <SiJavascript />,
  html: <SiHtml5 />,
  css: <SiCss3 />,
  react: <SiReact />,
  typescript: <SiTypescript />,
};

const capitalizeFirstLetter = (string: string) => {
  return string.charAt(0).toUpperCase() + string.slice(1);
};

const SkillTag: React.FC<SkillTagProps> = ({ skill }) => {
  const skillName =
    skill === "html" || skill === "css"
      ? skill.toUpperCase()
      : capitalizeFirstLetter(skill);
  const icon = iconMap[skill] || <span>❓</span>;

  return (
    <div className={style.container}>
      <p>{skillName}</p>
      {icon}
    </div>
  );
};

export default SkillTag;
