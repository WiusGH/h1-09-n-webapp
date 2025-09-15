import DynamicContainer from "../components/containers/DynamicContainer";
import CompleteProfileForm from "../components/forms/CompleteProfileForm";

const CompleteProfile = () => {
  return <DynamicContainer main={<CompleteProfileForm />} />;
};

export default CompleteProfile;
