import DynamicContainer from "../components/containers/DynamicContainer";
import RecruiterTypeForm from "../components/forms/RecruiterTypeForm";

const RequestRecuiterUserType = () => {
  return <DynamicContainer main={<RecruiterTypeForm />} />;
};

export default RequestRecuiterUserType;
