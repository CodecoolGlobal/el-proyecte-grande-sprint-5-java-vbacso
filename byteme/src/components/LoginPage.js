import LoginForm from "./LoginForm";

const LoginPage = ({onLogin}) => {

    return (
        <div>
            <LoginForm onSubmitForm={onLogin}/>
        </div>
    );
};

export default LoginPage;