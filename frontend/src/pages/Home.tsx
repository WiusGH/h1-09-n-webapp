import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import Homepage from "../components/views/Homepage";
import { isLoggedIn } from "../utils/userStorage";

const Home = () => {
  return (
    <div>
      <DynamicContainer
        main={<Homepage />}
        side={isLoggedIn() && <UserInfo />}
      />
    </div>
  );
};

export default Home;
