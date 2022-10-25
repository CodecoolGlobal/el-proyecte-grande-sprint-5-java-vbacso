import './App.css';
import LoginPage from "./components/LoginPage";
import {useState} from "react";
import MainPage from "./components/main_page/MainPage";

function App() {

    const [user, setUser] = useState("");

    const onLogin = async (email) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email})
        })
        if (resp.ok) {
            setUser(await resp.json())
        } else {
            alert("Invalid email!")
        }
    }

    return (<div>
        {user === ""
            ? <LoginPage onLogin={onLogin}/>
            : <div>
                <MainPage/>
                {user.name} Logged in<br/>
                {JSON.stringify(user)}
            </div>}
    </div>)
}

export default App;
