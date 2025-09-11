import {
  SiJavascript,
  SiHtml5,
  SiCss3,
  SiReact,
  SiTypescript,
  SiPython, 
  SiAngular,  
  SiNodedotjs, 
  SiSass,     
  SiGit,      
  SiGithub,   
  SiDocker,   
  SiKubernetes, 
  SiGooglecloud, 
  SiMongodb, 
  SiPostgresql, 
  SiMysql, 
  SiPhp, 
  SiGo, 
  SiRust, 
  SiSwift, 
  SiKotlin, 
  SiR, 
  SiRubyonrails, 
  SiFlutter, 
  SiDart, 
  SiAndroid, 
  SiIos, 
  SiWordpress, 
  SiShopify, 
  SiFigma, 
  SiAdobexd, 
  SiSketch, 
  SiJira, 
  SiConfluence, 
} from "react-icons/si";
import { MdClose } from "react-icons/md";
import style from "./SkillTag.module.css";
import type { JSX } from "react";
import React from "react";

interface SkillTagProps {
  skill: string;
  onRemove?: () => void;
}

const iconMap: Record<string, JSX.Element> = {
  javascript: <SiJavascript />,
  html: <SiHtml5 />,
  css: <SiCss3 />,
  react: <SiReact />,
  typescript: <SiTypescript />,
  python: <SiPython />, 
  angular: <SiAngular />,  
  node: <SiNodedotjs />, 
  sass: <SiSass />,     
  git: <SiGit />,      
  github: <SiGithub />,   
  docker: <SiDocker />,   
  kubernetes: <SiKubernetes />, 
  gcp: <SiGooglecloud />, 
  mongodb: <SiMongodb />, 
  postgresql: <SiPostgresql />, 
  mysql: <SiMysql />, 
  php: <SiPhp />, 
  go: <SiGo />, 
  rust: <SiRust />, 
  swift: <SiSwift />, 
  kotlin: <SiKotlin />, 
  r: <SiR />, 
  ruby: <SiRubyonrails />, 
  flutter: <SiFlutter />, 
  dart: <SiDart />, 
  android: <SiAndroid />, 
  ios: <SiIos />, 
  wordpress: <SiWordpress />, 
  shopify: <SiShopify />, 
  figma: <SiFigma />, 
  adobexd: <SiAdobexd />, 
  sketch: <SiSketch />, 
  jira: <SiJira />, 
  confluence: <SiConfluence />, 
  // Se le pueden meter mas iconos
};

const capitalizeFirstLetter = (string: string) => {
  return string.charAt(0).toUpperCase() + string.slice(1);
};

const SkillTag: React.FC<SkillTagProps> = ({ skill, onRemove }) => {
  const skillName =
    (skill === "html" || skill === "css")
      ? skill.toUpperCase()
      : capitalizeFirstLetter(skill);
      

  const icon = iconMap[skill.toLowerCase()] || <span>❓</span>; 

  return (
    <div className={style.container}>
      <p>{skillName}</p>
      <div className={style.icon}>{icon}</div>
      {onRemove && (
        <button onClick={onRemove} className={style.removeButton}>
          <MdClose />
        </button>
      )}
    </div>
  );
};

export default SkillTag;