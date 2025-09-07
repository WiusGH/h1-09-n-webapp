import DynamicContainer from "../components/containers/DynamicContainer";
import ProfileView from "../components/views/ProfileView";

const Profile = () => {
  return (
    <section>
      <DynamicContainer main={<ProfileView />} />
    </section>
  );
};

export default Profile;
