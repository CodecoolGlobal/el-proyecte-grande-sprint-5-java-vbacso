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
        <form className="w-25 mx-auto" onSubmit={submitForm}>
            <div className="mb-3">
                <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" className="form-control"/>
                <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>
    )
};

export default LoginForm;