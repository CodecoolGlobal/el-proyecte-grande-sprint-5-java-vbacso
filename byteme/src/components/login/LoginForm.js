import {useState} from "react";
import RegistrationButton from "./RegistrationButton";

const LoginForm = ({onSubmitForm}) => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const submitForm = (event) => {
        event.preventDefault();
        if (!email || !password) {
            alert("No email or password given!");
            return;
        }
        onSubmitForm(email, password);
        setEmail("");
        setPassword("");
    }

    return (
        <form className="login-form w-25 mx" onSubmit={submitForm}>
            <div className="mb-3">
                <input id="email-input-login" value={email} onChange={(e) => setEmail(e.target.value)} type="email"
                       placeholder="Enter your email"
                       name="email"
                       className="form-control"/>
                <input id="password-input-login" value={password} onChange={(e) => setPassword(e.target.value)}
                       type="password"
                       placeholder="Enter your password"
                       name="password"
                       className="form-control"/>
                <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
            </div>
            <button type="submit" id="byte-in-button" className="button button-dark">Byte.in</button>
            <RegistrationButton/>
        </form>)
};

export default LoginForm;