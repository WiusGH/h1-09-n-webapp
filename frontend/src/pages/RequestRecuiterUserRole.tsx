import DynamicContainer from "../components/containers/DynamicContainer";
import RequestRecruiterRoleForm from "../components/forms/RequestRecruiterRoleForm";
import UserInfo from "../components/sidebars/UserInfo";

const RequestRecuiterRole = () => {
  return (
    <DynamicContainer main={<RequestRecruiterRoleForm />} side={<UserInfo />} />
  );
};

export default RequestRecuiterRole;
