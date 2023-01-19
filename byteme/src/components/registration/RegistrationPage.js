import logo from "../../images/byteMe-logo.png";
import RegistrationForm from "./RegistrationForm";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

const RegistrationPage = ({loggedInUser, onRegistration}) => {

    const navigate = useNavigate();

    useEffect(() => {
        if (loggedInUser) navigate("/")

    }, [loggedInUser]);

    return (
        <div>
            <div className="login-container">
                <img id="login-logo" src={logo} alt="logo"/>
                <RegistrationForm onSubmitForm={onRegistration}/>
            </div>
            );
        </div>
    );
};

export default RegistrationPage;