import DynamicContainer from "../components/containers/DynamicContainer";
import CompleteProfileForm from "../components/forms/CompleteProfileForm";
import UserInfo from "../components/sidebars/UserInfo";

const CompleteProfile = () => {
  return (
    <DynamicContainer main={<CompleteProfileForm />} side={<UserInfo />} />
  );
};

export default CompleteProfile;
