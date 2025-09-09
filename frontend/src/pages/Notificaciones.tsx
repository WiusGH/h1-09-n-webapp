import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";

const Notificaciones = () => {
  return <DynamicContainer main={<h1>Notificaciones</h1>} side={UserInfo()} />;
};

export default Notificaciones;
