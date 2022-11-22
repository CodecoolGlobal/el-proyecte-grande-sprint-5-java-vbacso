import LoginForm from "./LoginForm";
import logo from '../../images/byteMe-logo.png';
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";

const LoginPage = ({onLogin}) => {
    const navigate = useNavigate();
    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
    useEffect(() => {
        if(loggedInUser)navigate("/")

    },[loggedInUser]);

    return (
        <div className="login-container">
            <img id="login-logo" src={logo} alt="logo"/>
            <LoginForm onSubmitForm={onLogin}/>
        </div>
    );
};

export default LoginPage;