import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";

const Mensajes = () => {
  return <DynamicContainer main={<h1>Mensajes</h1>} side={UserInfo()} />;
};

export default Mensajes;
