import {useState} from "react";

const LoginForm = ({onSubmitForm}) => {

    const [email, setEmail] = useState("");

    const submitForm = (event) => {
        event.preventDefault();
        if (!email) {
            alert("No email given");
            return;
        }
        onSubmitForm(email);
        setEmail("");
    }
    return (
        <form className="login-form w-25 mx" onSubmit={submitForm}>
                <div className="mb-3">
                    <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="Enter your email"
                           className="form-control"/>
                    <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
                </div>
                <button type="submit" className="button">Byte.in</button>
            </form>)
};

export default LoginForm;