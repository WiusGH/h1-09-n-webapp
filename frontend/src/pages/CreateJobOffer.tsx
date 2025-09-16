import DynamicContainer from "../components/containers/DynamicContainer";
import NewJobForm from "../components/forms/NewJobForm";
import UserInfo from "../components/sidebars/UserInfo";

const CreateJobOffer = () => {
  return <DynamicContainer main={<NewJobForm />} side={<UserInfo />} />;
};

export default CreateJobOffer;
