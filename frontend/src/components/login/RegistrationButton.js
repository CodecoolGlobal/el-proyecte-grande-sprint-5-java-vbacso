import {useNavigate} from "react-router-dom";

const RegistrationButton = () => {
    const navigate = useNavigate();

    function onClickRegistrationButton(e) {
        e.preventDefault();
        navigate("/registration")
    }

    return (
        <div>
            <div id="not-register-yet-text" className="form-text">Don't have a Byte yet? Create one!</div>
            <button type="submit" id="register-button" className="button button-dark" data-clicked="not-clicked"
                    onClick={onClickRegistrationButton}>Create New Byte
            </button>
        </div>
    );
};

export default RegistrationButton;