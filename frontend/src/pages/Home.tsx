import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import Homepage from "../components/views/Homepage";

const Home = () => {
  return (
    <div>
      <DynamicContainer main={<Homepage />} side={<UserInfo />} />
    </div>
  );
};

export default Home;
