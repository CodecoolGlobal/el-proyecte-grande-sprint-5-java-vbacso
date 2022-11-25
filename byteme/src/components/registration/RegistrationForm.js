import {useState} from "react";
import {useNavigate} from "react-router-dom";

const RegistrationForm = ({onSubmitForm}) => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    const navigate = useNavigate();

    const submitForm = (event) => {
        event.preventDefault();
        if (!email || !password || !name || !age) {
            alert("Missing details! Please fill out all the data fields.");
            return;
        }
        onSubmitForm(email, password, name, age);
        navigate("/login");
    }

    return (
        <form className="login-form w-25 mx" onSubmit={submitForm}>
            <div className="mb-3">
                <input id="name-input-registration" value={name} onChange={(e) => setName(e.target.value)}
                       type="text"
                       placeholder="Enter your name"
                       className="form-control"
                       required/>
                <input id="email-input-registration" value={email} onChange={(e) => setEmail(e.target.value)}
                       type="email"
                       placeholder="Enter your email"
                       className="form-control"
                       required/>
                <input id="age-input-registration" value={age} onChange={(e) => setAge(e.target.value)}
                       type="number"
                       placeholder="Enter your age"
                       className="form-control"
                       required/>
                <input id="password-input-registration" value={password} onChange={(e) => setPassword(e.target.value)}
                       type="password"
                       placeholder="Enter your password"
                       className="form-control"
                       required/>
                <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
            </div>
            <button type="submit" id="registration-button" className="button button-dark">Create New Byte</button>
        </form>
    );
};

export default RegistrationForm;