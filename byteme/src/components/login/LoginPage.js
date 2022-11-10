import LoginForm from "./LoginForm";
import logo from '../../images/byteMe-logo.png';
import {redirect, useNavigate} from "react-router-dom";
import {useEffect} from "react";

const LoginPage = ({setUser}) => {
    const navigate = useNavigate();
    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
    useEffect(() => {
        if(loggedInUser)navigate("/")

    },[loggedInUser]);

    const onLogin = async (email) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email})
        })
        if (resp.ok) {
            const userDetails = await resp.json();
            setUser(userDetails);
            localStorage.setItem("loggedInUser", JSON.stringify(userDetails))
            navigate("/");
        } else {
            alert("Invalid email!")
        }
    }

    return (
        <div className="login-container">
            <img id="login-logo" src={logo} alt="logo"/>
            <LoginForm onSubmitForm={onLogin}/>
        </div>
    );
};

export default LoginPage;