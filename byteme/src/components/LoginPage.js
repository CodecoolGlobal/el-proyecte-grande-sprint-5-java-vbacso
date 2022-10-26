import LoginForm from "./LoginForm";
import logo from '../images/byteMe-logo.png';

const LoginPage = ({onLogin}) => {

    return (
        <div className="login-container">
            <img id="login-logo" src={logo} alt="logo"/>
            <LoginForm onSubmitForm={onLogin}/>
        </div>
    );
};

export default LoginPage;