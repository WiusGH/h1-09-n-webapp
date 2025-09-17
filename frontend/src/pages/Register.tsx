import DynamicContainer from "../components/containers/DynamicContainer";
import RegisterForm from "../components/forms/RegisterForm";

const Register = () => {
  return <DynamicContainer main={<RegisterForm />} />;
};

export default Register;
