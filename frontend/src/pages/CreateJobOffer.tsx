import DynamicContainer from "../components/containers/DynamicContainer";
import UserInfo from "../components/sidebars/UserInfo";
import JobOfferCreator from "../components/views/JobOfferCreator";

const CreateJobOffer = () => {
  return <DynamicContainer main={<JobOfferCreator />} side={<UserInfo />} />;
};

export default CreateJobOffer;
