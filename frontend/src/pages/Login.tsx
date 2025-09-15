import DynamicContainer from "../components/containers/DynamicContainer";
import LoginForm from "../components/forms/LoginForm";

const Login = () => {
  return <DynamicContainer main={<LoginForm />} />;
};

export default Login;
