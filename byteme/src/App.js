import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import MainPage from "./components/MainPage";


function App() {

    const [loggedInUser, setloggedInUser] = useState();

    useEffect(() => {
        const loggedInUser = localStorage.getItem("loggedInUser")
        if (loggedInUser) {
            setloggedInUser(JSON.parse(loggedInUser));
        } else {
            setloggedInUser(null)
        }
    }, [])


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
            setloggedInUser(userDetails);
            localStorage.setItem("loggedInUser", JSON.stringify(userDetails))
        } else {
            alert("Invalid email!")
        }
    }
    const onLogout = () => {
        localStorage.setItem("loggedInUser", "");
        setloggedInUser(null);
    };
    return (
        loggedInUser === undefined ? <div className="main-container">"loading..."</div> :
            loggedInUser === null ? <LoginPage onLogin={onLogin}/> : <MainPage loggedInUser={loggedInUser} onLogout={onLogout}/>
    );
}

export default App;
