import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import ControlPanelView from "../components/views/ControlPanelView";

const ControlPanel = () => {
  return <DynamicContainer main={<ControlPanelView />} side={<UserInfo />} />;
};

export default ControlPanel;
