import './App.css';
import LoginPage from "./components/LoginPage";
import {useState} from "react";

function App() {

    const [user, setUser] = useState("");

    const onLogin = async (email) => {
        setUser(email);
    }

    return (<div>
        {user === ""
            ? <LoginPage onLogin={onLogin}/>
            : <div>Logged in</div>}
    </div>)
}

export default App;
